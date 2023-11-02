import React from 'react';

export default function HairPage() {
  return (
    <div className="stet__service-page service-page">
      <section className="service-page__welcome welcome">
        <div className="welcome__banner welcome__banner--hair"></div>
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
            <li className="works__img-container">
              <img
                src="./img/hair__works/works__hair-1.jpg"
                alt=""
                className="works__img"
              />
            </li>
            <li className="works__img-container">
              <img
                src="./img/hair__works/works__hair-2.jpg"
                alt=""
                className="works__img"
              />
            </li>
            <li className="works__img-container">
              <img
                src="./img/hair__works/works__hair-1.jpg"
                alt=""
                className="works__img"
              />
            </li>
            <li className="works__img-container">
              <img
                src="./img/hair__works/works__hair-2.jpg"
                alt=""
                className="works__img"
              />
            </li>
            <li className="works__img-container">
              <img
                src="./img/hair__works/works__hair-1.jpg"
                alt=""
                className="works__img"
              />
            </li>
            <li className="works__img-container">
              <img
                src="./img/hair__works/works__hair-2.jpg"
                alt=""
                className="works__img"
              />
            </li>
          </ul>
        </div>
      </section>

      <section className="service-page__masters masters">
        <div className="masters__conatiner _container">
          <h2 className="masters__title title">Meet our masters</h2>
          <div className="masters__cards">
            <div className="masters__master-card">
              <div className="masters__img-container">
                <img
                  src="./img/hair__masters/master-1.jpg"
                  alt=""
                  className="masters__img"
                />
              </div>
              <h3 className="masters__fullname">Elizabet Stown</h3>
              <p className="masters__qualification">Master</p>
            </div>

            <div className="masters__master-card">
              <div className="masters__img-container">
                <img
                  src="./img/hair__masters/master-1.jpg"
                  alt=""
                  className="masters__img"
                />
              </div>
              <h3 className="masters__fullname">Elizabet Stown</h3>
              <p className="masters__qualification">Master</p>
            </div>

            <div className="masters__master-card">
              <div className="masters__img-container">
                <img
                  src="./img/hair__masters/master-1.jpg"
                  alt=""
                  className="masters__img"
                />
              </div>
              <h3 className="masters__fullname">Elizabet Stown</h3>
              <p className="masters__qualification">Master</p>
            </div>
          </div>
        </div>
      </section>

      <section className="service-page__prices prices">
        <div className="prices__container _container">
          <h2 className="prices__title title">Prices</h2>

          <ul className="prices__list">
            <li className="prices__item">
              <div className="prices__item-title">Some procedure</div>
              <div className="prices__price">$100</div>
            </li>
            <li className="prices__item">
              <div className="prices__item-title">Some procedure</div>
              <div className="prices__price">$100</div>
            </li>
            <li className="prices__item">
              <div className="prices__item-title">Some procedure</div>
              <div className="prices__price">$100</div>
            </li>
            <li className="prices__item">
              <div className="prices__item-title">Some procedure</div>
              <div className="prices__price">$100</div>
            </li>
          </ul>
        </div>
      </section>
    </div>
  );
}
