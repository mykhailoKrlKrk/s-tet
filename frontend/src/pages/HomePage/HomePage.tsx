import React from 'react';
import { Link } from 'react-router-dom';
import { goTop } from '../../working-files/functions/goTop';

import './HomePage.scss';

export default function HomePage() {
  return (
    <div className="stet__homepage homepage">
      <section className="homepage__welcome welcome">
        <div className="welcome__left">
          <h1 className="welcome__title">s-tet</h1>
          <p className="welcome__subtitle">
            World of beauty, pleasure, and transformation.
          </p>
          <button className="welcome__booking">
            Book now
            <span className="first"></span>
            <span className="second"></span>
            <span className="third"></span>
            <span className="fourth"></span>
          </button>
        </div>
        <div className="welcome__banner welcome__banner--home"></div>
      </section>

      <section className="homepage__services services" id="services">
        <div className="services__container _container">
          <h2 className="services__title title">Select your transformation</h2>
          <div className="services__service-cards">
            <Link
              to="hair"
              className="services__service-card services__service-card--hair"
              onClick={goTop}
            >
              <span>Hair</span>
            </Link>
            <Link
              to="nails"
              className="services__service-card services__service-card--nails"
              onClick={goTop}
            >
              <span>Nails</span>
            </Link>
            <Link
              to="makeup"
              className="services__service-card services__service-card--makeup"
              onClick={goTop}
            >
              <span>Make up</span>
            </Link>
            <Link
              to="men"
              className="services__service-card services__service-card--men"
              onClick={goTop}
            >
              <span>Men</span>
            </Link>
          </div>
        </div>
      </section>

      <section id="benefits" className="homepage__benefits benefits">
        <div className="benefits__container _container">
          <h2 className="benefits__title title">Feel our benefits</h2>
          <div className="benefits__content">
            <div className="benefits__img-container">
              <img
                src="./img/benefits/benefit-1.jpg"
                alt=""
                className="benefits__img"
              />
            </div>
            <div className="benefits__info">
              <h3 className="benefits__benefit-title">Care in 2/4/6/8 hands</h3>
              <p className="benefits__benefit-text">
                At S-tet, we believe in individualized service, offering the
                choice of single or multiservice treatments. Whether you`re
                focused on hair, nails, face, lashes, or lips, our skilled
                professionals provide expert care. Opt for a comprehensive
                session, and a team of specialists will pamper you. Our services
                save you precious time – imagine having your hair, nails, and
                makeup perfected in just an hour, ready for a business meeting
                or a romantic evening.
              </p>
            </div>
          </div>
          <div className="benefits__buttons">
            <button className="benefits__button benefits__button--active"></button>
            <button className="benefits__button"></button>
            <button className="benefits__button"></button>
          </div>
        </div>
      </section>

      <section className="homepage__attributes attributes">
        <div className="attributes__container _container">
          <h2 className="attributes__title title">Our unique attributes</h2>
          <div className="attributes__cards">
            <div className="attributes__card">
              <div className="attributes__img-container">
                <img
                  src="./img/attributes/equipment.jpg"
                  alt=""
                  className="attributes__img"
                />
              </div>
              <h3 className="attributes__card-title">Equipment</h3>
              <button className="attributes__card-button">Read more</button>
              <p className="attributes__card-text">
                We utilize state-of-the-art equipment, such as Dyson hair
                dryers, known for their hair-friendly styling that preserves
                natural shine and health.
              </p>
            </div>
            <div className="attributes__card">
              <div className="attributes__img-container">
                <img
                  src="./img/attributes/sterility.jpg"
                  alt=""
                  className="attributes__img"
                />
              </div>
              <h3 className="attributes__card-title">Safety</h3>
              <button className="attributes__card-button">Read more</button>
              <p className="attributes__card-text">
                We prioritize your safety and utilize the YESON medical
                sterilization autoclave, ensuring our customers can have
                complete peace of mind.
              </p>
            </div>
            <div className="attributes__card">
              <div className="attributes__img-container">
                <img
                  src="./img/attributes/products.jpg"
                  alt=""
                  className="attributes__img"
                />
              </div>
              <h3 className="attributes__card-title">Products</h3>
              <button className="attributes__card-button">Read more</button>
              <p className="attributes__card-text">
                We continuously track the latest beauty products, selecting only
                those cosmetics that deliver tangible results and deserve your
                attention. You can trust us to guide you in making the right
                choices for your home care routine.
              </p>
            </div>
            <div className="attributes__card">
              <div className="attributes__img-container">
                <img
                  src="./img/attributes/individuality.png"
                  alt=""
                  className="attributes__img"
                />
              </div>
              <h3 className="attributes__card-title">Individuality</h3>
              <button className="attributes__card-button">Read more</button>
              <p className="attributes__card-text">
                Our objective extends beyond mere service. We strive to offer
                tailored guidance, taking into consideration your individual
                characteristics and style preferences. You can place full
                confidence in our specialists for any transformative change you
                desire.
              </p>
            </div>
          </div>
        </div>
      </section>

      <section id="contacts" className="homepage__contacts contacts">
        <div className="contacts__container _container">
          <h2 className="contacts__title title">Contact us</h2>
          <div className="contacts__contant">
            <div className="contacts__map-container"></div>
            <div className="contacts__info">
              <h3 className="contacts__info-title">Phone</h3>
              <a
                href="tel:654321987"
                className="contacts__details contacts__details--phone"
              >
                654 321 987
              </a>
              <h3 className="contacts__info-title">Adress</h3>
              <p className="contacts__details contacts__details--adress">
                2905 West Drive, Buffalo Grove
              </p>
              <h3 className="contacts__info-title">Working hours</h3>
              <p className="contacts__details">
                Monday - Friday: 10:00 - 19:00
                <br />
                Sutarday - Sunday: 12:00 - 18:00
              </p>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
