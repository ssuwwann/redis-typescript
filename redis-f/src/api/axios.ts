import axios from 'axios';

const URL = import.meta.env.VITE_BASE_URL;
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

      try {
        const response = await publicApi.post('/auth/refresh');
        alert('리프레시 요청>> ' + response);

        const accessToken = response.headers['authorization'];
        localStorage.setItem('accessToken', accessToken);

        return privateApi(originalRequest);
      } catch (error) {
        alert('새로 요청 토느 에러');
        console.log('새로 요청 토큰 에러', error);

        return Promise.reject(error);
      }
    }
    return Promise.reject(error);
  });
