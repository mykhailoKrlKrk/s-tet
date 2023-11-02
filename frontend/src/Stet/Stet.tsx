import { Routes, Route, Navigate } from 'react-router-dom';

import Header from '../components/Header/Header';

import HairPage from '../pages/HairPage/HairPage';
import HomePage from '../pages/HomePage/HomePage';
import MakeupPage from '../pages/MakeupPage/MakeupPage';
import MenPage from '../pages/MenPage/MenPage';
import NailsPage from '../pages/NailsPage/NailsPage';
import BookingPage from '../pages/BookingPage/BookingPage';
import NotFoundPage from '../pages/NotFoundPage/NotFoundPage';

import Footer from '../components/Footer/Footer';
import './Stet.scss';
// import { useState } from 'react';

function Stet() {
  return (
    <div className="stet">
      <Header />

      <main className="stet__main">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/home" element={<Navigate to="/" replace />} />

          <Route path="hair" element={<HairPage />} />
          <Route path="nails" element={<NailsPage />} />
          <Route path="makeup" element={<MakeupPage />} />
          <Route path="men" element={<MenPage />} />
          <Route path="booking" element={<BookingPage />} />

          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </main>

      <Footer />
    </div>
  );
}

export default Stet;
