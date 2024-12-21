import { privateApi, publicApi } from './axios.ts';
import { AxiosResponse } from 'axios';
import { ProductDetailData, ProductListData } from '../type/product.ts';

export const saveProduct = async (formData: FormData): Promise<AxiosResponse> => {
  const response = await privateApi.post('/products', formData);
  return response;
};

export const getProducts = async (): Promise<ProductListData[]> => {
  const response = await publicApi.get<ProductListData[]>('/products');
  return response.data;
};

export const getThumbnail = async (productId: number): Promise<Blob> => {
  const response = await publicApi.get(`/products/images/thumbnail/${productId}`, {
    responseType: 'blob',
  });
  return response.data;
};

export const getProduct = async (id: string | undefined): Promise<ProductDetailData> => {
  const response = await publicApi.get<ProductDetailData>(`/products/${id}`);
  return response.data;
};

export const getImages = async (imageId: number): Promise<Blob> => {
  const response = await publicApi.get(`/products/images/${imageId}`, {
    responseType: 'blob',
  });

  return response.data;
};