import { reset } from 'styled-reset';
import { lazy, Suspense } from 'react';
import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import { AuthProvider } from './utils/AuthContext.tsx';
import ProtectedRoute from './utils/ProtectedRoute.tsx';

const MainLayout = lazy(() => import('./Layout/MainLayout.tsx'));
const LoginPage = lazy(() => import('./pages/user/LoginPage.tsx'));
const HomePage = lazy(() => import('./pages/Home.tsx'));
const JoinPage = lazy(() => import('./pages/user/JoinPage.tsx'));
const ProductDetailPage = lazy(() => import('./pages/product/ProductDetailPage.tsx'));
const ProductRegisterPage = lazy(() => import('./pages/product/ProductRegisterPage.tsx'));

const GlobalStyle = createGlobalStyle`
    ${reset}
    * {
        margin: 0;
        box-sizing: border-box;
    }
`;

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <PageRoutes />
      </AuthProvider>
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

          <Route path="/products/register" element={
            <Suspense fallback={<h3>Loading ...</h3>}>
              <ProtectedRoute requiredRole="SE">
                <ProductRegisterPage />
              </ProtectedRoute>
            </Suspense>
          } />

          <Route path="/products/:id" element={
            <Suspense fallback={<h3>Loading ...</h3>}>
              <ProductDetailPage />
            </Suspense>
          } />
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
