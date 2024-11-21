import { privateApi, publicApi } from './axios.ts';

interface JoinRequestData {
  email: string;
  password: string;
  username: string;
  address: string;
}

interface LoginRequestData {
  email: string;
  password: string;
}

export const join = async (data: JoinRequestData): Promise<any> => {
  const response = await publicApi.post(`${import.meta.env.VITE_BASE_URL}/users`, data);
  return response.data;
};

export const login = async (data: LoginRequestData): Promise<any> => {
  const response = await publicApi.post(`${import.meta.env.VITE_BASE_URL}/login`, data);
  return response;
};

export const getRole = async (accessToken: string): Promise<any> => {
  const response = await privateApi.post(`${import.meta.env.VITE_BASE_URL}/users/roles`, null, {
    headers: { 'Authorization': accessToken }
  });
  return response.data;
};