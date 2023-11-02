import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.scss';
import Stet from './Stet/Stet';
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);
root.render(
  <BrowserRouter>
    <Stet />
  </BrowserRouter>,
);
