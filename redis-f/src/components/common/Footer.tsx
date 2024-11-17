import styled from 'styled-components';

const FooterContainer = styled.footer`
    height: 150px;
    border-top: 1px solid black;
    padding: 0 20px;
    display: flex;
    align-items: center;
`;

const Footer = () => {
  return (
    <FooterContainer>
      <h3>푸터다 아입니까</h3>
    </FooterContainer>
  );
};

export default Footer;