import { publicApi } from './axios.js';

export const join = async (data) => {
  const response = await publicApi.post(`${import.meta.env.VITE_BASE_URL}/users`, data);
  return response.data;
};

export const login = async (data) => {
  const response = await publicApi.post(`${import.meta.env.VITE_BASE_URL}/login`, data);
  return response.data;
};