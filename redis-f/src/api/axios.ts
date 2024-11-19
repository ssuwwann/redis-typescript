import axios from 'axios';

export const publicApi = axios.create({
  withCredentials: true
});

export const privateApi = axios.create({
  withCredentials: true
});

privateApi.interceptors.response.use();
