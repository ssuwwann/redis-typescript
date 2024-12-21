import axios from 'axios';

const URL = import.meta.env.VITE_BASE_URL;
let isRefreshing: boolean = false;

export const publicApi = axios.create({
  baseURL: URL,
  withCredentials: true,
});

export const privateApi = axios.create({
  baseURL: URL,
  withCredentials: true,
});

privateApi.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) config.headers.Authorization = accessToken;

    return config;
  });

privateApi.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      if (isRefreshing) return;

      try {
        isRefreshing = true;
        const response = await publicApi.post('/auth/refresh');

        const accessToken = response.headers['authorization'];
        localStorage.setItem('accessToken', accessToken);

        return privateApi(originalRequest);
      } catch (error) {
        alert('새로 요청 토큰 에러');
        console.log('새로 요청 토큰 에러', error);

        return Promise.reject(error);
      } finally {
        isRefreshing = false;
      }
    }
    return Promise.reject(error);
  });
