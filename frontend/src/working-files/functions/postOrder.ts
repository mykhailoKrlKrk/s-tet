import { client } from '../api/fetchClient';
import { Order } from '../types/Order';

export const postOrder = (order: Order) => {
  return client.post<Order>('orders', order);
};
