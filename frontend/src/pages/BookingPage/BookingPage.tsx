import React, { useState, useMemo } from 'react';
import cn from 'classnames';

import './BookingPage.scss';
import { Subservice } from '../../working-files/types/Subservice';

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

const subServices = [
  {
    id: 1,
    name: 'Some service 1',
    masterPrice: 100,
    headMasterPrice: 150,
  },
  {
    id: 2,
    name: 'Some service 2',
    masterPrice: 100,
    headMasterPrice: 150,
  },
  {
    id: 3,
    name: 'Some service 3',
    masterPrice: 100,
    headMasterPrice: 150,
  },
  {
    id: 4,
    name: 'Some service 4',
    masterPrice: 100,
    headMasterPrice: 150,
  },
];

const masters = [
  {
    id: 1,
    name: 'Elizabet',
    lastName: 'Stown',
    qualification: 'Head Master',
    coverImage: '',
  },
  {
    id: 2,
    name: 'Elizabet',
    lastName: 'Stown',
    qualification: 'Master',
    coverImage: '',
  },
  {
    id: 3,
    name: 'Elizabet',
    lastName: 'Stown',
    qualification: 'Master',
    coverImage: '',
  },
];

export default function BookingPage() {
  const [currService, setCurrService] = useState('');
  const [isHeadMaster, setIsHeadMaster] = useState(false);
  const [currMaster, setCurrMaster] = useState<number | null>(null);
  const [selectedSubservices, setSelectedSubservices] = useState<Subservice[]>(
    [],
  );
  const [clientName, setClientName] = useState('');
  const [clientPhone, setClientPhone] = useState('');
  const [clientComment, setClientComment] = useState('');

  const handleServiceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCurrService(e.currentTarget.value);
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
        ? master.qualification === 'Head Master'
        : master.qualification === 'Master';
    });

    if (newArray.length === 1) {
      setCurrMaster(newArray[0].id);
    }

    return newArray;
  }, [isHeadMaster]);

  const removeSubservice = (id: number) => {
    setSelectedSubservices((currSubservices) => {
      return currSubservices.filter(
        (currSubservice) => currSubservice.id !== id,
      );
    });
  };

  const handleClientNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setClientName(e.currentTarget.value);
  };
  const handleClientPhoneChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setClientPhone(e.currentTarget.value);
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

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const order = {
      clientName: '',
      phoneNumber: '',
      orderTotal: total,
      masterId: currMaster,
      servicesId: selectedSubservices.map((serv) => serv.id),
      comment: '',
    };

    setCurrService('');
    setIsHeadMaster(false);
    setCurrMaster(null);
    setSelectedSubservices([]);
    setClientName('');
    setClientPhone('');
    setClientComment('');
  };

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

          {!!currService && (
            <>
              <fieldset className="booking__prices">
                <legend className="booking__title">
                  What do you want to do?
                </legend>

                <label className="booking__headmaster-label">
                  Head master
                  <input
                    type="checkbox"
                    checked={isHeadMaster}
                    onChange={handleIsHeadMasterSelected}
                  />
                </label>

                <div className="booking__prices-container prices">
                  <ul className="prices__list">
                    {subServices.map((subService) => (
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
                        src={`./img/hair__masters/master-${master.id}.jpg`}
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
          <input
            type="text"
            className="booking__personal"
            placeholder="Enter your fullname*"
            value={clientName}
            onChange={handleClientNameChange}
            // required
          />
          <input
            type="tel"
            className="booking__personal"
            placeholder="Enter your phone number*"
            pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
            // required
            value={clientPhone}
            onChange={handleClientPhoneChange}
          />
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
            disabled={selectedSubservices.length === 0}
          >
            Apply
          </button>
        </div>
      </form>
    </div>
  );
}
