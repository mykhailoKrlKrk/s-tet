import { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import classNames from 'classnames';
import { goTop } from '../../working-files/functions/goTop';

import './Header.scss';
import DropdownMenu from '../DropdownMenu/DropdownMenu';

export default function Header() {
  const [isMenu, setMenu] = useState(false);
  const location = useLocation();
  const [isDropdownVisible, setDropdownVisible] = useState(false);

  const handleMouseEnter = () => {
    setDropdownVisible(true);
  };

  const handleMouseLeave = () => {
    setDropdownVisible(false);
  };

  const handleMenuClick = () => {
    setMenu(!isMenu);

    isMenu
      ? (document.body.style.overflow = 'auto')
      : (document.body.style.overflow = 'hidden');
  };

  useEffect(() => {
    if (isMenu) {
      handleMenuClick();
    }

    if (isDropdownVisible) {
      setDropdownVisible(false);
    }
  }, [location.hash, location.pathname]);

  return (
    <header
      className={classNames('stet__header', 'header', {
        'header--isMenu': isMenu,
      })}
    >
      <div className="header__container _container">
        <Link to="/" className="logo" onClick={goTop}>
          <img
            src="./img/logo/logo-no-background.svg"
            alt="s-tet"
            className="logo__img"
          />
        </Link>

        <nav className="header__navbar">
          {location.pathname === '/' ? (
            <ul className="header__navbar-list">
              <li className="header__navbar-item">
                <Link
                  to="/home"
                  className="header__navbar-link"
                  onClick={goTop}
                >
                  Home
                </Link>
              </li>
              <li className="header__navbar-item">
                <HashLink
                  to="#services"
                  className="header__navbar-link header__navbar-link--services"
                  onMouseEnter={handleMouseEnter}
                  onMouseLeave={handleMouseLeave}
                  onClick={handleMouseLeave}
                >
                  Services
                </HashLink>
                {isDropdownVisible && (
                  <DropdownMenu
                    handleMouseEnter={handleMouseEnter}
                    handleMouseLeave={handleMouseLeave}
                  />
                )}
              </li>
              <li className="header__navbar-item">
                <HashLink to="#benefits" className="header__navbar-link">
                  About
                </HashLink>
              </li>
              <li className="header__navbar-item">
                <HashLink to="#contacts" className="header__navbar-link">
                  Contacts
                </HashLink>
              </li>
            </ul>
          ) : (
            <ul className="header__navbar-list">
              <li className="header__navbar-item">
                <Link
                  to="/home"
                  className="header__navbar-link"
                  onClick={goTop}
                >
                  Home
                </Link>
              </li>
              <li className="header__navbar-item">
                <Link to="/hair" className="header__navbar-link">
                  Hair
                </Link>
              </li>
              <li className="header__navbar-item">
                <Link to="/nails" className="header__navbar-link">
                  Nails
                </Link>
              </li>
              <li className="header__navbar-item">
                <Link to="/makeup" className="header__navbar-link">
                  Make up
                </Link>
              </li>
              <li className="header__navbar-item">
                <Link to="/men" className="header__navbar-link">
                  Men
                </Link>
              </li>
            </ul>
          )}
        </nav>

        <div className="header__actions">
          {location.pathname !== '/booking' && (
            <Link to="booking" className="header__booking-button">
              Book
            </Link>
          )}
          <button className="header__menu-button" onClick={handleMenuClick}>
            {isMenu ? 'Close' : 'Menu'}
          </button>
        </div>

        <nav className="header__topmenu">
          <ul className="header__topmenu-list">
            <li className="header__topmenu-item">
              <p className="header__topmenu-link">Services</p>
              <ul className="header__topmenu-sublist">
                <li className="header__topmenu-sublink">
                  <Link to="hair" className="header__topmenu-link">
                    Hair
                  </Link>
                </li>
                <li className="header__topmenu-sublink">
                  <Link to="nails" className="header__topmenu-link">
                    Nails
                  </Link>
                </li>
                <li className="header__topmenu-sublink">
                  <Link to="makeup" className="header__topmenu-link">
                    Make up
                  </Link>
                </li>
                <li className="header__topmenu-sublink">
                  <Link to="men" className="header__topmenu-link">
                    Men
                  </Link>
                </li>
              </ul>
            </li>
            <li className="header__topmenu-item">
              <a href="#benefits" className="header__topmenu-link">
                About
              </a>
            </li>
            <li className="header__topmenu-item">
              <a href="#contacts" className="header__topmenu-link">
                Contacts
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
}
