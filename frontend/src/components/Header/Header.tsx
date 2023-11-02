import React from 'react';
import { Link } from 'react-router-dom';
import { goTop } from '../../working-files/functions/goTop';

import './Header.scss';

export default function Header() {
  return (
    <header className="stet__header header">
      <div className="header__container _container">
        <Link to="/" className="logo" onClick={goTop}>
          <img
            src="./img/logo/logo-no-background.svg"
            alt="s-tet"
            className="logo__img"
          />
        </Link>

        <nav className="header__navbar">
          <ul className="header__navbar-list">
            <li className="header__navbar-item">
              <Link to="/home" className="header__navbar-link" onClick={goTop}>
                Home
              </Link>
            </li>
            <li className="header__navbar-item">
              <a href="#services" className="header__navbar-link">
                Services
              </a>
            </li>
            <li className="header__navbar-item">
              <a href="#benefits" className="header__navbar-link">
                About
              </a>
            </li>
            <li className="header__navbar-item">
              <a href="#contacts" className="header__navbar-link">
                Contacts
              </a>
            </li>
          </ul>
        </nav>

        <div className="header__actions">
          <button className="header__menu-button">Menu</button>
          <Link to="booking" className="header__booking-button">
            Book
          </Link>
        </div>
      </div>
    </header>
  );
}
