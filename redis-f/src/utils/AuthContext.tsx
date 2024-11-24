import { createContext, ReactNode, useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getRole, logout } from '../api/user.ts';

type Role = 'BU' | 'SE' | 'SP' | null;

interface AuthContextType {
  username: string | null;
  role: Role;
  setAuthState: (username: string, role: Role) => void;
  clearAuthState: () => void;
}

const AuthContext = createContext<AuthContextType | null>(null);

const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [username, setUsername] = useState<string | null>(null);
  const [role, setRole] = useState<Role>(null);

  const navigate = useNavigate();

  const getUserRole = async (): Promise<any> => {
    const accessToken = localStorage.getItem('accessToken');

    if (!accessToken) throw new Error('No access token found');

    try {
      const result = await getRole(accessToken);
      setRole(result);
    } catch (error) {
      console.error(error);
      localStorage.removeItem('accessToken');
      localStorage.removeItem('username');
    }
  };

  useEffect(() => {
    getUserRole();
    setUsername(localStorage.getItem('username'));
  }, []);

  const setAuthState = (username: string, role: Role) => {
    localStorage.setItem('username', username);
    setUsername(username);
    setRole(role);
  };

  const clearAuthState = async () => {
    try {
      await logout();
      localStorage.removeItem('accessToken');
      localStorage.removeItem('username');
      setUsername(null);
      setRole(null);
      navigate('/');
    } catch (error) {
      console.error('로그아웃 에러:', error);
    }
  };

  return (
    <AuthContext.Provider value={{ username, role, setAuthState, clearAuthState }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);

  if (!context) throw new Error('useAuth must be used within an AuthProvider');

  return context;
};

export { AuthContext, AuthProvider };