import styled from 'styled-components';

const HeaderContainer = styled.header`
    height: 75px;
    border-bottom: 1px solid black;
    padding: 20px;
    display: flex;
    align-content: center;
    justify-content: space-between;
`;

const ButtonGroup = styled.div`
    display: flex;
    gap: 10px; // 버튼 사이 간격
    height: 35px;
`;

const StyledButton = styled.button<{ $signup?: boolean; }>`
    padding: 8px 16px;
    border: 1px solid #007bff;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;

    background: ${props => props.$signup ? '#007bff' : 'white'};
    color: ${props => props.$signup ? 'white' : '#007bff'};

    &:hover {
        background: ${props => props.$signup ? '#0056b3' : '#f8f9fa'};
    }
`;

const Header = () => {
  return (
    <HeaderContainer>
      <h3>헤더다 아입니까</h3>
      <ButtonGroup>
        <StyledButton $signup>회원가입</StyledButton>
        <StyledButton>로그인</StyledButton>
      </ButtonGroup>
    </HeaderContainer>
  );
};

export default Header;