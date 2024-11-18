import JoinForm from '../../components/user/JoinForm.tsx';
import { ChangeEvent, FormEvent, useState } from 'react';

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

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    console.log('form submitted', formData);
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