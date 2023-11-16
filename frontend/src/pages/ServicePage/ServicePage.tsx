/* eslint-disable react/no-unescaped-entities */
import React, { useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { getComments } from '../../working-files/functions/getComments';
import { getMasters } from '../../working-files/functions/getMasters';
import { getSubservices } from '../../working-files/functions/getSubservices';

import './ServicePage.scss';

const masters = [
  {
    id: 1,
    img: './img/hair__masters/master-1.jpg',
    fullname: 'Elizabet Stown',
    lvl: 'Head master',
  },
  {
    id: 2,
    img: './img/hair__masters/master-2.jpg',
    fullname: 'Elizabet Stown',
    lvl: 'Head master',
  },
  {
    id: 3,
    img: './img/hair__masters/master-3.jpg',
    fullname: 'Elizabet Stown',
    lvl: 'Head master',
  },
  {
    id: 4,
    img: './img/hair__masters/master-4.jpg',
    fullname: 'Elizabet Stown',
    lvl: 'Head master',
  },
  {
    id: 5,
    img: './img/hair__masters/master-5.jpg',
    fullname: 'Elizabet Stown',
    lvl: 'Head master',
  },
];

const prices = [
  {
    id: 1,
    name: 'Some procedure',
    price: 100,
  },
  {
    id: 2,
    name: 'Some procedure',
    price: 100,
  },
  {
    id: 3,
    name: 'Some procedure',
    price: 100,
  },
  {
    id: 4,
    name: 'Some procedure',
    price: 100,
  },
];

const comments = [
  {
    id: 1,
    body: "I had a wonderful experience with the women's hair master, Hanna, at this salon. She listened to my preferences, gave expert advice, and transformed my hair into a work of art. I left feeling like a new person!",
    author: 'Annet',
  },
  {
    id: 2,
    body: "Hanna is incredibly talented. I went in with a vague idea, and she turned it into a stunning hairstyle. The salon's ambiance is delightful, and I'll definitely be returning for more.",
    author: 'Jesica',
  },
  {
    id: 3,
    body: "I've finally found my go-to salon for women's hair services. The hair master here is a magician. I appreciate the attention to detail, and the salon's comfortable environment adds to the overall experience.",
    author: 'Robihn',
  },
];

export default function ServicePage() {
  const location = useLocation();
  const category = location.pathname.split('').slice(1).join('');

  const works = [
    './img/hair__works/works__hair-1.jpg',
    './img/hair__works/works__hair-2.jpg',
    './img/hair__works/works__hair-3.jpg',
    './img/hair__works/works__hair-4.jpg',
    './img/hair__works/works__hair-5.jpg',
    './img/hair__works/works__hair-6.jpg',
  ];

  const getData = async () => {
    Promise.all([getMasters(category)])
      .then((results) => {
        console.log(results);
      })
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <div className="stet__service-page service-page">
      <section className="service-page__welcome welcome">
        <div className="welcome__banner">
          <img
            src="./img/banners/banner__hair.jpg"
            className="welcome__banner-img"
            alt="Beauty"
          />
        </div>
        <div className="welcome__right">
          <h1 className="welcome__title">Hair</h1>
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
                  <img src={master.img} alt="" className="masters__img" />
                </div>
                <h3 className="masters__fullname">{master.fullname}</h3>
                <p className="masters__qualification">{master.lvl}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="service-page__prices prices">
        <div className="prices__container _container">
          <h2 className="prices__title title">Prices</h2>

          <ul className="prices__list">
            {prices.map((procedure) => (
              <li key={procedure.id} className="prices__item">
                <div className="prices__item-title">{procedure.name}</div>
                <div className="prices__price">${procedure.price}</div>
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
                <p className="comments__text">{comment.body}</p>
                <p className="comments__author">- {comment.author}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <div className="service-page__booking-container">
        <Link to="booking" className="booking-button">
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
