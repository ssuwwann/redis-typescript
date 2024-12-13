import { ReactNode, useEffect } from 'react';
import { useAuth } from './AuthContext.tsx';
import { useNavigate } from 'react-router-dom';
import { getRole } from '../api/user.ts';

type Role = 'SE' | 'BU' | 'SP' | null;

interface ProductRouteProps {
  children: ReactNode;
  requiredRole: Exclude<Role, null>;
}

const ProtectedRoute = ({ children, requiredRole = 'BU' }: ProductRouteProps) => {
  const { role, username } = useAuth();
  const navigate = useNavigate();

  if (role === null) navigate(-1);

  useEffect(() => {
    validateAccess();
  }, [username, role, requiredRole, navigate, location.pathname]);

  const validateAccess = async () => {
    try {
      const currentRole = await getRole();
      if (!username) {
        navigate('/login', {
          replace: true,
          state: { from: location.pathname }
        });
        return;
      }

      if (currentRole !== requiredRole) {
        navigate(-1);
        return;
      }

    } catch (error) {
      console.log('권한 확인 중 오류 발생', error);
      navigate('/', {
        replace: true,
        state: { from: location.pathname }
      });
    }
  };

  return children;
};

export default ProtectedRoute;