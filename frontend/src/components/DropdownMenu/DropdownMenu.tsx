import { Link } from 'react-router-dom';
import './DropdownMenu.scss';

type Props = {
  handleMouseEnter: () => void;
  handleMouseLeave: () => void;
};

export default function DropdownMenu({
  handleMouseEnter,
  handleMouseLeave,
}: Props) {
  return (
    <div
      className="dropdown-menu"
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
    >
      <ul className="dropdown-menu__list">
        <li className="dropdown-menu__item">
          <Link to="hair" className="dropdown-menu__link">
            Hair
          </Link>
        </li>
        <li className="dropdown-menu__item">
          <Link to="nails" className="dropdown-menu__link">
            Nails
          </Link>
        </li>
        <li className="dropdown-menu__item">
          <Link to="makeup" className="dropdown-menu__link">
            Make up
          </Link>
        </li>
        <li className="dropdown-menu__item">
          <Link to="men" className="dropdown-menu__link">
            Men
          </Link>
        </li>
      </ul>
    </div>
  );
}
