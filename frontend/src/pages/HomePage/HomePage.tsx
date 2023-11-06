import classNames from 'classnames';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { goTop } from '../../working-files/functions/goTop';

import './HomePage.scss';

const banners = [
  './img/banners/banner__home-1.jpg',
  './img/banners/banner__home-2.jpg',
  './img/banners/banner__home-3.jpg',
];

const benefits = [
  {
    id: 0,
    title: 'Care in 2/4/6/8 hands',
    img: './img/benefits/benefit-1.jpg',
    text: "At S-tet, we believe in individualized service, offering the choice of single or multiservice treatments. Whether you're focused on hair, nails, face, lashes, or lips, our skilled professionals provide expert care. Opt for a comprehensive session, and a team of specialists will pamper you. Our express services save you precious time – imagine having your hair, nails, and makeup perfected in just an hour, ready for a business meeting or a romantic evening.",
  },
  {
    id: 1,
    title: 'Convenient location',
    img: './img/benefits/benefit-2.jpg',
    text: 'Our beauty salon is strategically located in the city center for easy accessibility. Situated near major transportation hubs, it ensures a hassle-free commute, and our prime location offers ample parking options.',
  },
  {
    id: 2,
    title: 'Adapting to modern times',
    img: './img/benefits/benefit-3.jpg',
    text: 'Our hairstylists, nail technicians, and makeup artists are not only well-trained with practical experience, but they also keep abreast of the latest beauty trends and offerings from renowned brands. Our beauty experts consistently update their knowledge, attending workshops and training sessions, including international ones. This ensures that our specialists are equipped to help you achieve a stylish, distinctive, and on-trend look.',
  },
];

const attributes = [
  {
    id: 0,
    title: 'Equipment',
    img: './img/attributes/equipment.jpg',
    text: 'We utilize state-of-the-art equipment, such as Dyson hair dryers, known for their hair-friendly styling that preserves natural shine and health.',
  },
  {
    id: 1,
    title: 'Safety',
    img: './img/attributes/sterility.jpg',
    text: 'We prioritize your safety and utilize the YESON medical sterilization autoclave, ensuring our customers can have complete peace of mind.',
  },
  {
    id: 2,
    title: 'Products',
    img: './img/attributes/products.jpg',
    text: 'We continuously track the latest beauty products, selecting only those cosmetics that deliver tangible results and deserve your attention. You can trust us to guide you in making the right choices for your home care routine.',
  },
  {
    id: 3,
    title: 'Individuality',
    img: './img/attributes/individuality.png',
    text: 'Our objective extends beyond mere service. We strive to offer tailored guidance, taking into consideration your individual characteristics and style preferences. You can place full confidence in our specialists for any transformative change you desire.',
  },
];

export default function HomePage() {
  const [bannerIdx, setBannerIdx] = useState(0);
  const [benefitIdx, setBenefitIdx] = useState(0);
  const [initPos, setInitPos] = useState(100);
  const [attributeId, setAttributeId] = useState<number | null>(null);

  const changeBanner = () => {
    bannerIdx === banners.length - 1
      ? setBannerIdx(0)
      : setBannerIdx(bannerIdx + 1);
  };

  const changeBenefit = (id: number) => {
    setBenefitIdx(id);
    setInitPos(0);
    setTimeout(() => setInitPos(100), 1000);
  };

  const showAttribute = (id: number) => {
    id === attributeId ? setAttributeId(null) : setAttributeId(id);
  };

  useEffect(() => {
    const intervalId = setInterval(() => {
      changeBanner();
    }, 6000);

    return () => {
      window.clearInterval(intervalId);
    };
  });

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
        <div className="welcome__banner">
          {banners.map((banner) => (
            <img
              key={banner}
              src={banner}
              className="welcome__banner-img"
              alt="Beauty"
              style={{
                opacity: `${bannerIdx === banners.indexOf(banner) ? 1 : 0}`,
              }}
            />
          ))}
        </div>
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
          <div className="benefits__contents-container">
            {benefits.map((benefit) => (
              <div
                key={benefit.id}
                className="benefits__content"
                style={{
                  left: `${benefitIdx === benefit.id ? 0 : initPos}%`,
                  zIndex: `${benefitIdx === benefits.indexOf(benefit) ? 5 : 0}`,
                }}
              >
                <div className="benefits__img-container">
                  <img src={benefit.img} alt="" className="benefits__img" />
                </div>
                <div className="benefits__info">
                  <h3 className="benefits__benefit-title">{benefit.title}</h3>
                  <p className="benefits__benefit-text">{benefit.text}</p>
                </div>
              </div>
            ))}
          </div>
          <div className="benefits__buttons">
            {benefits.map((benefit) => (
              <button
                key={benefit.id}
                className={classNames('benefits__button', {
                  'benefits__button--active': benefit.id === benefitIdx,
                })}
                onClick={() => changeBenefit(benefit.id)}
              />
            ))}
          </div>
        </div>
      </section>

      <section className="homepage__attributes attributes">
        <div className="attributes__container _container">
          <h2 className="attributes__title title">Our unique attributes</h2>
          <div className="attributes__cards">
            {attributes.map((attribute) => (
              <div
                key={attribute.id}
                className="attributes__card"
                onClick={() => showAttribute(attribute.id)}
              >
                <div
                  className={classNames('attributes__card-inner', {
                    'attributes__card-inner--rotated':
                      attribute.id === attributeId,
                  })}
                >
                  <div className="attributes__card-front">
                    <div className="attributes__img-container">
                      <img
                        src={attribute.img}
                        alt={attribute.title}
                        className="attributes__img"
                      />
                    </div>
                    <h3 className="attributes__card-title">
                      {attribute.title}
                    </h3>
                  </div>
                  <p className="attributes__card-back">{attribute.text}</p>
                </div>
              </div>
            ))}
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
