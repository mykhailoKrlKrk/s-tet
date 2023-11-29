import { Link } from 'react-router-dom';
import './NotFoundPage.scss';

export default function NotFoundPage() {
  return (
    <div className="notfound-page">
      <div className="notfound-page__container _container">
        <div className="notfound-page__text">Page is not found</div>

        <Link to="/" className="notfound-page__button">
          Back Home
        </Link>
      </div>
    </div>
  );
}
