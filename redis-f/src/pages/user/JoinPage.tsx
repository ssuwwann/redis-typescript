import JoinForm from '../../components/user/JoinForm.tsx';
import { ChangeEvent, FormEvent, useState } from 'react';
import { join } from '../../api/user.ts';

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

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    console.log('form submitted', formData);

    const result = await join(formData);
    console.log('회원가입 결과', result);
  };

  return (
    <>
      <JoinForm
        formData={formData}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
      />
    </>
  );
};

export default JoinPage;