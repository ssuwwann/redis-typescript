import { createContext, ReactNode, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';

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

  const setAuthState = (username: string, role: Role) => {
    setUsername(username);
    setRole(role);
  };

  const clearAuthState = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('username');
    setUsername(null);
    setRole(null);
    navigate('/');
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