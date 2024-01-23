/* eslint-disable react/no-unescaped-entities */
import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import Loader from '../../components/Loader/Loader';
import { getComments } from '../../working-files/functions/getComments';
import { getMasters } from '../../working-files/functions/getMasters';
import { getSubservices } from '../../working-files/functions/getSubservices';
import { Comment } from '../../working-files/types/Comment';
import { Master } from '../../working-files/types/Master';
import { Subservice } from '../../working-files/types/Subservice';

import './ServicePage.scss';

export default function ServicePage() {
  const location = useLocation();
  const category = location.pathname.split('').slice(1).join('');
  const [masters, setMasters] = useState<Master[]>([]);
  const [subservices, setSubservices] = useState<Subservice[]>([]);
  const [comments, setComments] = useState<Comment[]>([]);
  const [isSwitchHeadmaster, setSwitchHeadmaster] = useState(false);
  const [isLoading, setLoading] = useState(true);
  const [isError, setError] = useState(false);

  const works = [
    `./img/${category}__works/works__${category}-1.jpg`,
    `./img/${category}__works/works__${category}-2.jpg`,
    `./img/${category}__works/works__${category}-3.jpg`,
    `./img/${category}__works/works__${category}-4.jpg`,
    `./img/${category}__works/works__${category}-5.jpg`,
    `./img/${category}__works/works__${category}-6.jpg`,
  ];

  const getData = async () => {
    setLoading(true);
    Promise.all([
      getMasters('/' + category),
      getSubservices('/' + category),
      getComments('/' + category),
    ])
      .then(
        ([mastersFromServer, subservicesFromServer, commentsFromServer]) => {
          setMasters(mastersFromServer);
          setSubservices(subservicesFromServer);
          setComments(commentsFromServer);
        },
      )
      .catch(() => setError(true))
      .finally(() => {
        setLoading(false);
      });
  };

  useEffect(() => {
    getData();
  }, []);

  if (isLoading) {
    return (
      <div className="stet__loader-container">
        <Loader />
      </div>
    );
  }

  if (isError) {
    return (
      <div className="stet__loader-container">
        Something went wrong. Try reload the page or back later.
      </div>
    );
  }

  return (
    <div className="stet__service-page service-page">
      <section className="service-page__welcome welcome">
        <div className="welcome__banner">
          <img
            src={`./img/banners/banner__${category}.jpg`}
            className="welcome__banner-img"
            alt="Beauty"
          />
        </div>
        <div className="welcome__right">
          <h1 className="welcome__title welcome__title--toCapitalize">
            {category === 'makeup' ? 'Make up' : category}
          </h1>
          <p className="welcome__subtitle">
            Lorem ipsum dolor sit amet consectetur adipisicing elit.
          </p>
        </div>
      </section>

      <section className="service-page__works works">
        <div className="works__container _container">
          <h2 className="works__title title">Our works</h2>

          <ul className="works__photos">
            {works.map((work) => (
              <li key={work} className="works__img-container">
                <img src={work} alt="Our work" className="works__img" />
              </li>
            ))}
          </ul>
        </div>
      </section>

      <section className="service-page__masters masters">
        <div className="masters__conatiner _container">
          <h2 className="masters__title title">Meet our masters</h2>
          <div className="masters__cards">
            {masters.map((master) => (
              <div key={master.id} className="masters__master-card">
                <div className="masters__img-container">
                  <img
                    src={master.coverImage}
                    alt=""
                    className="masters__img"
                  />
                </div>
                <h3 className="masters__fullname">{master.fullName}</h3>
                <p className="masters__qualification">{master.qualification}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="service-page__prices prices">
        <div className="prices__container _container">
          <h2 className="prices__title title">Prices</h2>

          <div className="prices__price-switcher">
            <p onClick={() => setSwitchHeadmaster(false)}>Master</p>
            <div className="price-switcher">
              <input
                type="checkbox"
                id="cbx-3"
                checked={isSwitchHeadmaster}
                onChange={() => setSwitchHeadmaster(!isSwitchHeadmaster)}
              />
              <label htmlFor="cbx-3" className="price-switcher__toggle">
                <span></span>
              </label>
            </div>
            <p onClick={() => setSwitchHeadmaster(true)}>Headmaster</p>
          </div>

          <ul className="prices__list">
            {subservices.map((procedure) => (
              <li key={procedure.id} className="prices__item">
                <div className="prices__item-title">{procedure.name}</div>
                <div className="prices__price">
                  ${' '}
                  {isSwitchHeadmaster
                    ? procedure.headMasterPrice
                    : procedure.masterPrice}
                </div>
              </li>
            ))}
          </ul>
        </div>
      </section>

      <section className="service-page__comments comments">
        <div className="comments__container _container">
          <h2 className="comments__title title">What talk about us</h2>

          <div className="comments__cards">
            {comments.map((comment) => (
              <div key={comment.id} className="comments__card">
                <p className="comments__text">{comment.description}</p>
                <p className="comments__author">- {comment.fullName}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <div className="service-page__booking-container">
        <Link to="../booking" className="booking-button">
          Book now
          <span className="first"></span>
          <span className="second"></span>
          <span className="third"></span>
          <span className="fourth"></span>
        </Link>
      </div>
    </div>
  );
}
