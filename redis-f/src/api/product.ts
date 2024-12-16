import { privateApi, publicApi } from './axios.ts';
import { AxiosResponse } from 'axios';
import { ProductListData } from '../type/product.ts';

export const saveProduct = async (formData: FormData): Promise<AxiosResponse> => {
  const response = await privateApi.post('/products', formData);
  return response;
};

export const getProducts = async (): Promise<ProductListData[]> => {
  const response = await publicApi.get<ProductListData[]>('/products');
  return response.data;
};

export const getThumbnail = async (): Promise<AxiosResponse> => {

};