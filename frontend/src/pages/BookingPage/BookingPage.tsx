import React from 'react';

import './BookingPage.scss';

export default function BookingPage() {
  return (
    <div className="stet__booking booking">
      <form action="" className="booking__form _container">
        <section className="booking__services">
          <h2 className="booking__title title">Select your service</h2>
          <div className="booking__services-container">
            <label className="booking__service booking__service--hair booking__service--active">
              <input type="radio" name="service" value="hair" />
            </label>
            <label className="booking__service booking__service--nails">
              <input type="radio" name="service" value="nails" />
            </label>
            <label className="booking__service booking__service--makeup">
              <input type="radio" name="service" value="makeup" />
            </label>
            <label className="booking__service booking__service--men">
              <input type="radio" name="service" value="men" />
            </label>
          </div>
        </section>

        <section className="booking__masters">
          <h2 className="booking__title title">Select your master</h2>
          <div className="booking__master-container">
            <label className="booking__master">
              <input type="radio" name="service" value="hair" />
              <img
                src="./img/hair__masters/master-1.jpg"
                alt=""
                className="booking__master-img"
              />
              <h3 className="booking__master-fullname">Elizabet Stown</h3>
              <p className="booking__master-qualification">Master</p>
            </label>
            <label className="booking__master booking__master--active">
              <input type="radio" name="service" value="hair" />
              <img
                src="./img/hair__masters/master-1.jpg"
                alt=""
                className="booking__master-img"
              />
              <h3 className="booking__master-fullname">Elizabet Stown</h3>
              <p className="booking__master-qualification">Master</p>
            </label>
            <label className="booking__master">
              <input type="radio" name="service" value="hair" />
              <img
                src="./img/hair__masters/master-1.jpg"
                alt=""
                className="booking__master-img"
              />
              <h3 className="booking__master-fullname">Elizabet Stown</h3>
              <p className="booking__master-qualification">Master</p>
            </label>
          </div>
        </section>

        <section className="booking__prices">
          <h2 className="booking__title title">What do you want to do?</h2>
          <div className="booking__prices-container prices">
            <ul className="prices__list">
              <li className="prices__item">
                <div className="prices__item-title">Some procedure</div>
                <div className="prices__price">$ 100</div>
                <input type="checkbox" className="prices__checkbox" />
              </li>
              <li className="prices__item">
                <div className="prices__item-title">Some procedure</div>
                <div className="prices__price">$ 100</div>
                <input type="checkbox" className="prices__checkbox" />
              </li>
              <li className="prices__item">
                <div className="prices__item-title">Some procedure</div>
                <div className="prices__price">$ 100</div>
                <input type="checkbox" className="prices__checkbox" />
              </li>
              <li className="prices__item">
                <div className="prices__item-title">Some procedure</div>
                <div className="prices__price">$ 100</div>
                <input type="checkbox" className="prices__checkbox" />
              </li>
            </ul>
          </div>
          <p className="booking__prices-total">
            Total <span>$ 1000</span>
          </p>
        </section>

        <input
          type="text"
          className="booking__personal"
          placeholder="Enter your fullname*"
          required
        />

        <input
          type="tel"
          className="booking__personal"
          placeholder="Enter your phone number*"
          required
        />

        <textarea
          className="booking__personal booking__personal--textarea"
          placeholder="Add comment"
        />

        <button type="submit" className="booking__submit">
          Apply
        </button>
      </form>
    </div>
  );
}
