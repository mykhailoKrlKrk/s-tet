import { useEffect } from 'react';
import { Routes, Route, Navigate, useLocation } from 'react-router-dom';

import Header from '../components/Header/Header';

import HomePage from '../pages/HomePage/HomePage';
import ServicePage from '../pages/ServicePage/ServicePage';
import BookingPage from '../pages/BookingPage/BookingPage';
import NotFoundPage from '../pages/NotFoundPage/NotFoundPage';

import Footer from '../components/Footer/Footer';
import './Stet.scss';
import { goTop } from '../working-files/functions/goTop';

// "homepage": "https://mykhailokrlkrk.github.io/s-tet/",

function Stet() {
  const location = useLocation();

  useEffect(() => {
    goTop();
  }, [location.pathname]);

  return (
    <div className="stet">
      <Header />

      <main className="stet__main">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/home" element={<Navigate to="/" replace />} />

          <Route path="hair" element={<ServicePage key={'hair'} />} />
          <Route path="nails" element={<ServicePage key={'nails'} />} />
          <Route path="makeup" element={<ServicePage key={'makeup'} />} />
          <Route path="men" element={<ServicePage key={'men'} />} />
          <Route path="booking" element={<BookingPage />} />

          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </main>

      <Footer />
    </div>
  );
}

export default Stet;
