import React from 'react';
import './TopMenu.scss';

export default function TopMenu() {
  return (
    <nav className="top-menu">
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
    </nav>
  );
}
