import { ChangeEvent, FC, FormEvent } from 'react';
import {
  FormContainer,
  FormTitle,
  FormWrapper,
  Input,
  InputGroup,
  Label,
  StyledButton
} from '../../assets/css/CommonStyle.tsx';

interface JoinFormProps {
  formData: {
    email: string;
    password: string;
    username: string;
    address: string;
  };

  handleChange: (e: ChangeEvent<HTMLInputElement>) => void;
  handleSubmit: (e: FormEvent<HTMLFormElement>) => void;
}

const JoinForm: FC<JoinFormProps> = ({ formData, handleChange, handleSubmit }) => {
  return (
    <FormContainer>
      <FormWrapper onSubmit={handleSubmit}>
        <FormTitle>회원가입</FormTitle>

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

        <InputGroup>
          <Label htmlFor="username">이름</Label>
          <Input
            id="username"
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            placeholder="이름을 입력하세요"
            required
          />
        </InputGroup>

        <InputGroup>
          <Label htmlFor="address">주소</Label>
          <Input
            id="address"
            type="text"
            name="address"
            value={formData.address}
            onChange={handleChange}
            placeholder="주소를 입력하세요"
            required
          />
        </InputGroup>

        <StyledButton $color="$blue" style={{ width: '100%' }}>가입하기</StyledButton>
      </FormWrapper>
    </FormContainer>
  );
};

export default JoinForm;