import { ChangeEvent, FC, FormEvent, useState } from 'react';
import {
  FormContainer,
  FormTitle,
  FormWrapper,
  Input,
  InputGroup,
  Label,
  StyledButton
} from '../../assets/css/CommonUserStyle.tsx';
import {
  ModalContent,
  ModalHeader,
  ModalOverlay
} from '../../assets/css/CommonModalStyle.tsx';
import DaumPostcodeEmbed from 'react-daum-postcode';

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
  const [isAddressModalOpen, setIsAddressModalOpen] = useState(false);

  const handleComplete = (data: any) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }

    const event = {
      target: {
        name: 'address',
        value: fullAddress
      }
    } as ChangeEvent<HTMLInputElement>;

    handleChange(event);
    setIsAddressModalOpen(false);
  };

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
            readOnly
            required
            onClick={() => setIsAddressModalOpen(true)}
          />
        </InputGroup>

        <StyledButton $color="$blue" style={{ width: '100%' }}>가입하기</StyledButton>
      </FormWrapper>

      {isAddressModalOpen && (
        <ModalOverlay onClick={() => setIsAddressModalOpen(false)}>
          <ModalContent onClick={(e) => e.stopPropagation()}>
            <ModalHeader>
              <DaumPostcodeEmbed onComplete={handleComplete} />
            </ModalHeader>
          </ModalContent>
        </ModalOverlay>
      )}
    </FormContainer>
  );
};

export default JoinForm;