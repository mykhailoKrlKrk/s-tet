import { Order } from '../types/Order';

const BASE_URL = 'http://ec2-34-192-33-253.compute-1.amazonaws.com/api/';

type RequestMethod = 'GET' | 'POST';

function request<T>(
  url: string,
  method: RequestMethod = 'GET',
  data: Order | null = null,
): Promise<T> {
  const options: RequestInit = {
    method,
  };

  if (data) {
    options.body = JSON.stringify(data);
    options.headers = {
      'Content-Type': 'application/json; charset=UTF-8',
    };
  }

  return fetch(BASE_URL + url, options).then((res) => res.json());
}

export const client = {
  get: <T>(url: string) => request<T>(url),
  post: <T>(url: string, data: Order | null) => request<T>(url, 'POST', data),
};
