import styled from 'styled-components';
import { Link } from 'react-router-dom';

type ButtonColorType = '$blue' | '$gray' | '$red' | undefined;

interface StyledComponentProps {
  $color?: ButtonColorType;
}

const colorMap = {
  $blue: {
    background: '#007bff',
    hoverBackground: '#0056b3',
    border: '#007bff',
    text: 'white'
  },
  $gray: {
    background: 'white',
    hoverBackground: '#f8f9fa',
    border: '#6c757d',
    text: '#6c757d'
  },
  $red: {
    background: '#dc3545',
    hoverBackground: '#c82333',
    border: '#dc3545',
    text: 'white'
  }
};

const getColorStyle = (color: ButtonColorType) => {
  const colorStyle = color ? colorMap[color] : colorMap.$gray;

  return `
    background:${colorStyle.background};
    color:${colorStyle.text};
    border-color:${colorStyle.border};
    
    $:hover {
      background:${colorStyle.hoverBackground};
    }
  `;
};

export const StyledLink = styled(Link)<StyledComponentProps>`
    padding: 8px 16px;
    border: 1px solid;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
    text-decoration: none;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    transition: all 0.2s ease-in-out;
    ${(props) => getColorStyle(props.$color)}
`;

export const StyledButton = styled.button<StyledComponentProps>`
    padding: 8px 16px;
    border: 1px solid;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    transition: all 0.2s ease-in-out;
    ${(props) => getColorStyle(props.$color)}
`;


/* 공통 form + input */
export const FormContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: calc(100vh - 75px); // 헤더 높이 제외
    padding: 20px;
`;

export const FormWrapper = styled.form`
    width: 100%;
    max-width: 400px;
    padding: 40px;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

export const FormTitle = styled.h2`
    text-align: center;
    margin-bottom: 30px;
    color: #333;
`;

export const InputGroup = styled.div`
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
`;

export const Label = styled.label`
    margin-bottom: 8px;
    font-size: 14px;
    color: #555;
`;

export const Input = styled.input`
    padding: 10px 12px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 14px;

    &:focus {
        outline: none;
        border-color: #007bff;
        box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.1);
    }

    &::placeholder {
        color: #aaa;
    }
`;

export const ErrorMessage = styled.span`
    color: #dc3545;
    font-size: 12px;
    margin-top: 4px;
`;