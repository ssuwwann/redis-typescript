import JoinForm from '../../components/user/JoinForm.tsx';
import { ChangeEvent, FormEvent, useState } from 'react';
import { join } from '../../api/user.ts';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const PageContainer = styled.div`
    background: #f5f5f5;
`;

interface JoinFormData {
  email: string;
  password: string;
  username: string;
  address: string;
}

const initJoinForm: JoinFormData = {
  email: '',
  password: '',
  username: '',
  address: ''
};


const JoinPage = () => {
  const [formData, setFormData] = useState<JoinFormData>(initJoinForm);

  const navigate = useNavigate();

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const result = await join(formData);

      if (result) navigate('/');
    } catch (error) {
      console.log('회원가입 에러: ', error);
    }
  };

  return (
    <PageContainer>
      <JoinForm
        formData={formData}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
      />
    </PageContainer>
  );
};

export default JoinPage;