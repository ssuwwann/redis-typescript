import { reset } from 'styled-reset';
import { lazy, Suspense } from 'react';
import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';

const MainLayout = lazy(() => import('./Layout/MainLayout.tsx'));
const LoginPage = lazy(() => import('./pages/user/LoginPage.tsx'));
const HomePage = lazy(() => import('./pages/Home.tsx'));
const JoinPage = lazy(() => import('./pages/user/JoinPage.tsx'));

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
            <Suspense>
              <HomePage />
            </Suspense>}
          />
        </Route>
        
        <Route path="/login" element={
          <Suspense fallback={<h3>Loading ...</h3>}>
            <LoginPage />
          </Suspense>
        } />

        <Route path="/join" element={
          <Suspense fallback={<h3>Loading ...</h3>}>
            <JoinPage />
          </Suspense>
        } />
      </Routes>
    </>
  );
}

export default App;
