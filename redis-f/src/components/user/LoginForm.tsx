import { ChangeEvent, FC, FormEvent } from 'react';
import {
  FormContainer,
  FormTitle,
  FormWrapper,
  Input,
  InputGroup,
  Label,
  StyledButton
} from '../../assets/css/CommonUserStyle.tsx';

interface LoginFormProps {
  formData: {
    email: string,
    password: string
  };

  handleChange: (e: ChangeEvent<HTMLInputElement>) => void;
  handleSubmit: (e: FormEvent<HTMLFormElement>) => void;
}

const LoginForm: FC<LoginFormProps> = ({ formData, handleChange, handleSubmit }) => {
  return (
    <FormContainer>
      <FormWrapper onSubmit={handleSubmit}>
        <FormTitle>로그인</FormTitle>

        <InputGroup>
          <Label htmlFor="email">이메일</Label>
          <Input
            id="email"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="이메일을 입력하세요"
            required
          />
        </InputGroup>

        <InputGroup>
          <Label htmlFor="password">비밀번호</Label>
          <Input
            id="password"
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="비밀번호를 입력하세요"
            required
          />
        </InputGroup>

        <StyledButton $color="$blue" style={{ width: '100%' }}>로그인</StyledButton>
      </FormWrapper>
    </FormContainer>
  );
};

export default LoginForm;