import React, { useState, useMemo, useEffect } from 'react';
import cn from 'classnames';
import { PhoneInput } from 'react-international-phone';
import 'react-international-phone/style.css';

import './BookingPage.scss';
import { Subservice } from '../../working-files/types/Subservice';
import { Master } from '../../working-files/types/Master';
import { getMasters } from '../../working-files/functions/getMasters';
import { getSubservices } from '../../working-files/functions/getSubservices';
import { postOrder } from '../../working-files/functions/postOrder';
import Loader from '../../components/Loader/Loader';

export default function BookingPage() {
  const services = [
    {
      value: 'hair',
      title: 'Hair',
    },
    {
      value: 'nails',
      title: 'Nails',
    },
    {
      value: 'makeup',
      title: 'Make up',
    },
    {
      value: 'men',
      title: 'Men',
    },
  ];

  const [currService, setCurrService] = useState('');
  const [isHeadMaster, setIsHeadMaster] = useState(false);
  const [currMaster, setCurrMaster] = useState<number | null>(null);
  const [masters, setMasters] = useState<Master[]>([]);
  const [subservices, setSubservices] = useState<Subservice[]>([]);
  const [selectedSubservices, setSelectedSubservices] = useState<Subservice[]>(
    [],
  );
  const [clientName, setClientName] = useState('');
  const [clientPhone, setClientPhone] = useState('');
  const [clientComment, setClientComment] = useState('');
  const [phone, setPhone] = useState('');

  const [isClientNameErr, setClientNameErr] = useState(false);
  const [isClientPhoneErr, setClientPhoneErr] = useState(false);

  const [isLoading, setLoading] = useState(true);

  const getData = async () => {
    setLoading(true);
    Promise.all([
      getMasters('/' + currService),
      getSubservices('/' + currService),
    ])
      .then(([mastersFromServer, subservicesFromServer]) => {
        setMasters(mastersFromServer);
        setSubservices(subservicesFromServer);
      })
      .catch((err) => console.log(err))
      .finally(() => {
        setLoading(false);
      });
  };

  const handleServiceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCurrService(e.currentTarget.value);
    setSelectedSubservices([]);
  };

  const handleIsHeadMasterSelected = () => {
    setIsHeadMaster(!isHeadMaster);
    setCurrMaster(null);
  };

  const handleSelectSubserviceChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    subservice: Subservice,
  ) => {
    setSelectedSubservices((currSubservices) => {
      const isChecked = currSubservices.some(
        (currSubservice) => currSubservice.id === subservice.id,
      );

      if (isChecked) {
        return currSubservices.filter(
          (currSubservice) => currSubservice.id !== subservice.id,
        );
      }

      return [...currSubservices, subservice];
    });
  };

  const handleMasterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCurrMaster(+e.currentTarget.value);
  };

  const filteredMasters = useMemo(() => {
    const newArray = masters.filter((master) => {
      return isHeadMaster
        ? master.qualification === 'HEADMASTER'
        : master.qualification === 'MASTER';
    });

    if (newArray.length === 1) {
      setCurrMaster(newArray[0].id);
    }

    return newArray;
  }, [isHeadMaster, currService, masters.length]);

  const removeSubservice = (id: number) => {
    setSelectedSubservices((currSubservices) => {
      return currSubservices.filter(
        (currSubservice) => currSubservice.id !== id,
      );
    });
  };

  const handleClientNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setClientName(e.currentTarget.value);
    setClientNameErr(false);
  };
  const handleClientPhoneChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setClientPhone(e.currentTarget.value);
    setClientPhoneErr(false);
  };
  const handleClientCommentChange = (
    e: React.ChangeEvent<HTMLTextAreaElement>,
  ) => {
    setClientComment(e.currentTarget.value);
  };

  const total = useMemo(() => {
    let sum = 0;
    selectedSubservices.forEach((service) => {
      if (isHeadMaster) {
        sum += service.headMasterPrice;
      } else {
        sum += service.masterPrice;
      }
    });

    return sum;
  }, [selectedSubservices.length, isHeadMaster]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const phonePattern = /^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/;
    const namePattern = /^[a-zA-Zа-яА-я]+ [a-zA-Zа-яА-я]+$/;
    const isError =
      !phonePattern.test(clientPhone.trim()) ||
      !namePattern.test(clientName.trim());

    setClientPhoneErr(!phonePattern.test(clientPhone.trim()));
    setClientNameErr(!namePattern.test(clientName.trim()));

    if (isError) {
      return;
    }

    const order = {
      clientName,
      phoneNumber: clientPhone,
      orderTotal: total,
      masterId: currMaster || 0,
      servicesId: selectedSubservices.map((serv) => serv.id),
      comment: clientComment,
    };

    try {
      const response = await postOrder(order);

      console.log(response);
    } catch (error) {
      console.log(error);
    }

    setCurrService('');
    setIsHeadMaster(false);
    setCurrMaster(null);
    setSelectedSubservices([]);
    setClientName('');
    setClientPhone('');
    setClientComment('');
  };

  useEffect(() => {
    if (currService === '') {
      return;
    }

    getData();
  }, [currService]);

  return (
    <div className="stet__booking booking">
      <form className="booking__form _container" onSubmit={handleSubmit}>
        <div className="booking__left-column">
          <fieldset className="booking__services">
            <legend className="booking__title">Select service</legend>
            <div className="booking__services-container">
              {services.map((service) => (
                <label
                  key={service.value}
                  className={cn('booking__service', {
                    'booking__service--active': currService === service.value,
                  })}
                >
                  {service.title}
                  <input
                    type="radio"
                    name="service"
                    value={service.value}
                    onChange={handleServiceChange}
                  />
                </label>
              ))}
            </div>
          </fieldset>

          {!!currService && isLoading && (
            <div className="booking__loader-container">
              <Loader />
            </div>
          )}

          {!!currService && !isLoading && (
            <>
              <fieldset className="booking__prices">
                <legend className="booking__title">
                  What do you want to do?
                </legend>

                <div className="booking__price-switcher">
                  <label htmlFor="cbx-3">Head Master</label>
                  <div className="price-switcher">
                    <input
                      type="checkbox"
                      id="cbx-3"
                      checked={isHeadMaster}
                      onChange={handleIsHeadMasterSelected}
                    />
                    <label htmlFor="cbx-3" className="price-switcher__toggle">
                      <span></span>
                    </label>
                  </div>
                </div>

                <div className="booking__prices-container prices">
                  <ul className="prices__list">
                    {subservices.map((subService) => (
                      <li key={subService.id} className="prices__item">
                        <div className="prices__item-title">
                          {subService.name}
                        </div>
                        <div className="prices__price">
                          ${' '}
                          {isHeadMaster
                            ? subService.headMasterPrice
                            : subService.masterPrice}
                        </div>
                        <input
                          type="checkbox"
                          className="prices__checkbox"
                          checked={selectedSubservices.some(
                            (currSubservice) =>
                              currSubservice.id === subService.id,
                          )}
                          onChange={(e) =>
                            handleSelectSubserviceChange(e, subService)
                          }
                        />
                      </li>
                    ))}
                  </ul>
                </div>
              </fieldset>

              <fieldset className="booking__masters">
                <legend className="booking__title">Select your master</legend>
                <div className="booking__master-container">
                  {filteredMasters.map((master) => (
                    <label
                      key={master.id}
                      className={cn('booking__master', {
                        'booking__master--active': currMaster === master.id,
                      })}
                    >
                      <input
                        type="radio"
                        name="master"
                        value={master.id}
                        onChange={handleMasterChange}
                      />
                      <img
                        src={`./img/${currService}__masters/master-${
                          masters.findIndex((m) => m.id === master.id) + 1
                        }.jpg`}
                        alt=""
                        className="booking__master-img"
                      />
                      <h3 className="booking__master-fullname">
                        {master.name + ' ' + master.lastName}
                      </h3>
                      <p className="booking__master-qualification">
                        {master.qualification}
                      </p>
                    </label>
                  ))}
                  {filteredMasters.length > 1 && (
                    <label
                      className={cn('booking__master', {
                        'booking__master--active': currMaster === null,
                      })}
                    >
                      <input
                        type="radio"
                        name="master"
                        onChange={() => setCurrMaster(null)}
                      />
                      <img
                        src="./img/master.jpg"
                        alt=""
                        className="booking__master-img"
                      />
                      <h3 className="booking__master-fullname">
                        {isHeadMaster ? 'free Head Master' : 'free Master'}
                      </h3>
                    </label>
                  )}
                </div>
              </fieldset>
            </>
          )}
        </div>

        <div className="booking__right-column">
          <div className="booking__personal-container">
            {isClientNameErr && (
              <p className="booking__notification">
                Your full name must contain two words
              </p>
            )}
            <input
              type="text"
              className="booking__personal"
              placeholder="Enter your fullname*"
              value={clientName}
              onChange={handleClientNameChange}
            />
          </div>
          <div className="booking__personal-container">
            {isClientPhoneErr && (
              <p className="booking__notification">
                Your phone number must contain a relevant number of digits
              </p>
            )}
            {/* <input
              type="tel"
              className="booking__personal"
              placeholder="Enter your phone number*"
              value={clientPhone}
              onChange={handleClientPhoneChange}
            /> */}
            {/* <PhoneInput
              defaultCountry="ua"
              value={phone}
              onChange={(phone) => setPhone(phone)}
            /> */}
          </div>
          <textarea
            className="booking__personal booking__personal--textarea"
            placeholder="Add comment"
            value={clientComment}
            onChange={handleClientCommentChange}
          />

          <h3 className="booking__title booking__title--mb">Your order</h3>
          {selectedSubservices.length === 0 ? (
            <div className="booking__order-message">Order list is empty</div>
          ) : (
            <ul className="booking__order-list">
              {selectedSubservices.map((subservice) => (
                <li key={subservice.id} className="booking__order-item">
                  {subservice.name}
                  <button
                    className="booking__remove-item"
                    onClick={() => removeSubservice(subservice.id)}
                  />
                </li>
              ))}
            </ul>
          )}

          <p className="booking__prices-total">
            Total <span>$ {total}</span>
          </p>

          <button
            type="submit"
            className="booking__submit"
            disabled={
              selectedSubservices.length === 0 || !clientName || !clientPhone
            }
          >
            Apply
          </button>
        </div>
      </form>
    </div>
  );
}
