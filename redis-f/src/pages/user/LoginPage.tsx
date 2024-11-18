import LoginForm from '../../components/user/LoginForm.tsx';
import { ChangeEvent, FormEvent, useState } from 'react';

interface LoginFormData {
  email: string;
  password: string;
}

const initLoginForm: LoginFormData = {
  email: '',
  password: ''
};

const LoginPage = () => {
  const [formData, setFormData] = useState<LoginFormData>(initLoginForm);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    console.log('login form data', formData);
  };

  return (
    <>
      <LoginForm
        formData={formData}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
      />
    </>
  );
};

export default LoginPage;