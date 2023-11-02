import './Footer.scss';

export default function Footer() {
  return (
    <footer className="stet__footer footer">
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
              <a href="#about" className="footer__navbar-link">
                About
              </a>
            </li>
            <li className="footer__navbar-item">
              <a href="#contacts" className="footer__navbar-link">
                Contacts
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
  );
}
