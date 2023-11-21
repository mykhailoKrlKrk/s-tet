import { client } from '../api/fetchClient';
import { Comment } from '../types/Comment';

export const getComments = (category: string) => {
  return client.get<Comment[]>('comments' + category);
};
