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
  const response = await publicApi.post('/users', data);
  return response.data;
};

export const login = async (data: LoginRequestData): Promise<any> => {
  const response = await publicApi.post('/login', data);
  return response;
};

export const logout = async (): Promise<any> => {
  const response = await privateApi.post('/logout');
  return response;
};

export const getRole = async (): Promise<any> => {
  const response = await privateApi.post('/users/roles');
  return response.data;
};

export const applySeller = async (): Promise<any> => {
  const response = await privateApi.get('/users/seller');
  return response.data;
};