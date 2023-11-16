import { client } from '../api/fetchClient';
import { Subservice } from '../types/Subservice';

export const getSubservices = (category: string) => {
  return client.get<Subservice[]>(`subservices/${category}`);
};
