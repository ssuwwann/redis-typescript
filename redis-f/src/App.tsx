import { reset } from 'styled-reset';
import { lazy, Suspense } from 'react';
import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';

const LoginPage = lazy(() => import('./pages/LoginPage.tsx'));
const HomePage = lazy(() => import('./pages/Home.tsx'));
const MainLayout = lazy(() => import('./Layout/MainLayout.tsx'));

const GlobalStyle = createGlobalStyle`
    ${reset}
    html, body {
        margin: 0;
        box-sizing: border-box;
    }
`;

function App() {
  return (
    <BrowserRouter>
      <PageRoutes />
    </BrowserRouter>
  );
}

function PageRoutes() {
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route path="/" element={
          <Suspense fallback={<h3>Loading ... </h3>}>
            <MainLayout>
              <Outlet />
            </MainLayout>
          </Suspense>
        }>
          <Route index element={
            <Suspense><HomePage /></Suspense>
          } />

          <Route path="/login" element={
            <Suspense fallback={<h3>Loading ... </h3>}>
              <LoginPage />
            </Suspense>
          } />
        </Route>
      </Routes>
    </>
  );
}

export default App;
