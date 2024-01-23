import { Link } from 'react-router-dom';
import './Footer.scss';

export default function Footer() {
  return (
    <footer className="stet__footer footer">
      <div className="footer__container _container">
        <a href="/" className="logo footer__logo">
          <img
            src="./img/logo/logo-white.svg"
            alt="s-tet"
            className="logo__img"
          />
        </a>

        <nav className="footer__navbar">
          <ul className="footer__navbar-list">
            <li className="footer__navbar-item">
              <Link to="/" className="footer__navbar-link">
                Home
              </Link>
            </li>
            <li className="footer__navbar-item">
              <Link to="hair" className="footer__navbar-link">
                Hair
              </Link>
            </li>
            <li className="footer__navbar-item">
              <Link to="nails" className="footer__navbar-link">
                Nails
              </Link>
            </li>
            <li className="footer__navbar-item">
              <Link to="makeup" className="footer__navbar-link">
                Make up
              </Link>
            </li>
            <li className="footer__navbar-item">
              <Link to="men" className="footer__navbar-link">
                Men
              </Link>
            </li>
            <li className="footer__navbar-item">
              <Link to="booking" className="footer__navbar-link">
                Booking
              </Link>
            </li>
          </ul>
        </nav>

        <div className="footer__socials">
          <a
            href="https://www.facebook.com/"
            className="footer__social-link footer__social-link--fb"
            target="_blank"
            rel="noreferrer"
          ></a>
          <a
            href="https://www.instagram.com/"
            className="footer__social-link footer__social-link--inst"
            target="_blank"
            rel="noreferrer"
          ></a>
          <a
            href="https://web.telegram.org/"
            className="footer__social-link footer__social-link--tg"
            target="_blank"
            rel="noreferrer"
          ></a>
        </div>
      </div>

      <p className="footer__copyright">All right reserved, 2023</p>
    </footer>
  );
}
