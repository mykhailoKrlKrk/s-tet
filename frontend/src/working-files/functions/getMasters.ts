import { client } from '../api/fetchClient';
import { Master } from '../types/Master';

export const getMasters = (category: string) => {
  return client.get<Master[]>('masters' + category);
};
