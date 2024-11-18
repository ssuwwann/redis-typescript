import { ReactNode } from 'react';
import Header from '../components/common/Header.tsx';
import Footer from '../components/common/Footer.tsx';
import styled from 'styled-components';

const MainContainer = styled.main`
    position: relative;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
`;

const MainWrapper = styled.div`
    flex: 1;
    padding-bottom: 150px;
    max-width: 1000px;
    margin: 0 auto;
`;

const MainLayout = ({ children }: { children: ReactNode }) => {
  return (
    <>
      <Header />
      <MainContainer>
        <MainWrapper>
          {children}
        </MainWrapper>
        <Footer />
      </MainContainer>
    </>
  );
};

export default MainLayout;