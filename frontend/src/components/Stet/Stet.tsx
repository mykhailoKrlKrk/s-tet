import './Stet.scss';
// import { useState } from 'react';

function App() {
  return (
    <div className="stet">
      <header className="stet__header header">
        <div className="header__container _container">
          <a href="/" className="logo">
            <img
              src="./img/logo/logo-no-background.svg"
              alt="s-tet"
              className="logo__img"
            />
          </a>

          <nav className="header__navbar">
            <ul className="header__navbar-list">
              <li className="header__navbar-item">
                <a href="#services" className="header__navbar-link">
                  Services
                </a>
              </li>
              <li className="header__navbar-item">
                <a href="" className="header__navbar-link">
                  Item 2
                </a>
              </li>
              <li className="header__navbar-item">
                <a href="" className="header__navbar-link">
                  Some Item 3
                </a>
              </li>
              <li className="header__navbar-item">
                <a href="" className="header__navbar-link">
                  Other Item 4
                </a>
              </li>
            </ul>
          </nav>

          <div className="header__actions">
            <button className="header__menu-button">Menu</button>
            <button className="header__booking-button">Book</button>
          </div>
        </div>
      </header>

      {/* <nav className="top-menu">
        <div className="top-menu__container _container">
          <ul className="top-menu__navbar-list">
            <li className="top-menu__navbar-item">
              <a href="" className="top-menu__navbar-link">
                Services
              </a>
            </li>
            <li className="top-menu__navbar-item">
              <a href="" className="top-menu__navbar-link">
                Item 2
              </a>
            </li>
            <li className="top-menu__navbar-item">
              <a href="" className="top-menu__navbar-link">
                Some Item 3
              </a>
            </li>
            <li className="top-menu__navbar-item">
              <a href="" className="top-menu__navbar-link">
                Other Item 4
              </a>
            </li>
          </ul>
        </div>
      </nav> */}

      <main className="stet__main main">
        <section className="main__welcome welcome">
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

        <section className="main__services services" id="services">
          <div className="services__container _container">
            <h2 className="services__title title">
              Select your transformation
            </h2>

            <div className="services__service-cards">
              <a
                href=""
                className="services__service-card services__service-card--hair"
              >
                <span>Hair</span>
              </a>
              <a
                href=""
                className="services__service-card services__service-card--nails"
              >
                <span>Nails</span>
              </a>
              <a
                href=""
                className="services__service-card services__service-card--makeup"
              >
                <span>Make up</span>
              </a>
              <a
                href=""
                className="services__service-card services__service-card--men"
              >
                <span>Men</span>
              </a>
            </div>
          </div>
        </section>

        <section className="main__benefits benefits">
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
                <h3 className="benefits__benefit-title">
                  Care in 2/4/6/8 hands
                </h3>
                <p className="benefits__benefit-text">
                  At S-tet, we believe in individualized service, offering the
                  choice of single or multiservice treatments. Whether you`re
                  focused on hair, nails, face, lashes, or lips, our skilled
                  professionals provide expert care. Opt for a comprehensive
                  session, and a team of specialists will pamper you. Our
                  services save you precious time – imagine having your hair,
                  nails, and makeup perfected in just an hour, ready for a
                  business meeting or a romantic evening.
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

        <section className="main__attributes attributes">
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
                  We continuously track the latest beauty products, selecting
                  only those cosmetics that deliver tangible results and deserve
                  your attention. You can trust us to guide you in making the
                  right choices for your home care routine.
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
                  confidence in our specialists for any transformative change
                  you desire.
                </p>
              </div>
            </div>
          </div>
        </section>

        <section className="main__contacts contacts">
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
      </main>

      {/* <div className="stet__service-page service-page">
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
                  src="./img/hair/works__hair-1.jpg"
                  alt=""
                  className="works__img"
                />
              </li>
              <li className="works__img-container">
                <img
                  src="./img/hair/works__hair-2.jpg"
                  alt=""
                  className="works__img"
                />
              </li>
              <li className="works__img-container">
                <img
                  src="./img/hair/works__hair-1.jpg"
                  alt=""
                  className="works__img"
                />
              </li>
              <li className="works__img-container">
                <img
                  src="./img/hair/works__hair-2.jpg"
                  alt=""
                  className="works__img"
                />
              </li>
              <li className="works__img-container">
                <img
                  src="./img/hair/works__hair-1.jpg"
                  alt=""
                  className="works__img"
                />
              </li>
              <li className="works__img-container">
                <img
                  src="./img/hair/works__hair-2.jpg"
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
                    src="./img/masters__hair/master-1.jpg"
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
                    src="./img/masters__hair/master-1.jpg"
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
                    src="./img/masters__hair/master-1.jpg"
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
      </div> */}

      <footer className="main__footer footer">
        <div className="footer__container _container">
          <a href="/" className="logo">
            <img
              src="./img/logo/logo-white.svg"
              alt="s-tet"
              className="logo__img"
            />
          </a>

          <nav className="footer__navbar">
            <ul className="footer__navbar-list">
              <li className="footer__navbar-item">
                <a href="#services" className="footer__navbar-link">
                  Services
                </a>
              </li>
              <li className="footer__navbar-item">
                <a href="" className="footer__navbar-link">
                  Item 2
                </a>
              </li>
              <li className="footer__navbar-item">
                <a href="" className="footer__navbar-link">
                  Some Item 3
                </a>
              </li>
              <li className="footer__navbar-item">
                <a href="" className="footer__navbar-link">
                  Other Item 4
                </a>
              </li>
            </ul>
          </nav>

          <div className="footer__socials">
            <a
              href="https://www.facebook.com/"
              className="footer__social-link footer__social-link--fb"
            ></a>
            <a
              href="https://www.instagram.com/"
              className="footer__social-link footer__social-link--inst"
            ></a>
            <a
              href=""
              className="footer__social-link footer__social-link--tg"
            ></a>
          </div>
        </div>

        <p className="footer__copyright">All right reserved, 2023</p>
      </footer>
    </div>
  );
}

export default App;
