import { privateApi } from './axios.ts';

export const saveProduct = async (formData: FormData) => {
  const response = await privateApi.post('/products', formData);
  return response;
};