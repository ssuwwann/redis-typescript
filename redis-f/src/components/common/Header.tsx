import styled from 'styled-components';
import { StyledButton, StyledLink } from '../../assets/css/CommonUserStyle.tsx';
import { useAuth } from '../../utils/AuthContext.tsx';

const HeaderContainer = styled.header`
    height: 75px;
    border-bottom: 1px solid black;
    padding: 20px;
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
`;

const ButtonGroup = styled.div`
    display: flex;
    gap: 10px; // 버튼 사이 간격
    height: 35px;
`;

const Header = () => {
  const { username, clearAuthState } = useAuth();

  console.log('header에서 ', username);
  return (
    <HeaderContainer>
      <ButtonGroup>
        {username ? (
          <>
            <span>{username}님</span>
            <StyledButton onClick={clearAuthState}>로그아웃</StyledButton>
          </>
        ) : (
          <>
            <StyledLink to="/join" $color="$blue">회원가입</StyledLink>
            <StyledLink to="/login">로그인</StyledLink>
          </>
        )}

      </ButtonGroup>
    </HeaderContainer>
  );
};

export default Header;
