import LoginForm from '../../components/user/LoginForm.tsx';
import { ChangeEvent, FormEvent, useState } from 'react';
import { getRole, login } from '../../api/user.ts';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../utils/AuthContext.tsx';

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
  const { setAuthState } = useAuth();

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
      const result = await login(formData);
      const accessToken = result.headers['authorization'];

      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('username', result.username); // username 저장 추가
      const role = await getRole();
      setAuthState(result.data, role);
      navigate('/');
    } catch (error) {
      console.error('로그인 실패:', error);
    }
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