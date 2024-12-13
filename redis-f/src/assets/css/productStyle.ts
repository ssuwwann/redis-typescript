import styled from 'styled-components';

export const PageContainer = styled.div`
    max-width: 500px;
    width: 100%;
    margin: 0 auto;
    padding: 24px;
`;

export const Section = styled.section`
    background: #fff;
    border: 1px solid #e0e0e0;
    border-radius: 12px;
    padding: 24px;
    margin-bottom: 24px;
    width: 100%;

    h3 {
        margin: 0 0 24px 0;
        font-size: 18px;
        font-weight: 600;
        color: #333;
    }
`;

export const InputGroup = styled.div`
    margin-bottom: 24px;
    width: 100%;

    &:last-child {
        margin-bottom: 0;
    }

    &.horizontal {
        display: flex;
        align-items: center;
        gap: 16px;
    }

    &.price-group {
        display: flex;
        align-items: center;
        gap: 12px;

        input {
            max-width: 160px;
        }

        .unit {
            color: #666;
            font-size: 14px;
        }
    }
`;

export const Label = styled.label`
    display: block;
    margin-bottom: 8px;
    font-size: 14px;
    font-weight: 500;
    color: #333;

    &.checkbox-label {
        display: inline-flex;
        align-items: center;
        padding: 8px 16px;
        gap: 8px;
        cursor: pointer;
        user-select: none;
        border: 1px solid #e0e0e0;
        border-radius: 8px;
        background: #fff;
        transition: all 0.2s ease;

        &:hover {
            background: #f8f9fa;
        }

        input[type="checkbox"] {
            width: 18px;
            height: 18px;
            margin: 0;
            cursor: pointer;
            accent-color: #007bff;
        }

        &.checked {
            background: #f0f7ff;
            border-color: #007bff;
            color: #007bff;
        }
    }
`;

export const Input = styled.input`
    width: 100%;
    height: 44px;
    padding: 0 16px;
    border: 1px solid #e0e0e0;
    border-radius: 8px;
    font-size: 14px;
    color: #333;
    background: #fff;
    transition: all 0.2s ease;

    &::placeholder {
        color: #aaa;
    }

    &:hover {
        border-color: #bbb;
    }

    &:focus {
        outline: none;
        border-color: #007bff;
        box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.1);
    }

    &[type="number"] {
        text-align: right;
        padding-right: 12px;
    }
`;

export const Textarea = styled.textarea`
    width: 100%;
    min-height: 120px;
    padding: 16px;
    border: 1px solid #e0e0e0;
    border-radius: 8px;
    font-size: 14px;
    color: #333;
    resize: vertical;
    transition: all 0.2s ease;

    &::placeholder {
        color: #aaa;
    }

    &:hover {
        border-color: #bbb;
    }

    &:focus {
        outline: none;
        border-color: #007bff;
        box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.1);
    }
`;

export const Grid = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
    gap: 12px;
    margin-bottom: 16px;
`;

export const Button = styled.button<{ $primary?: boolean }>`
    width: 100%;
    height: 44px;
    padding: 0 16px;
    background: ${props => props.$primary ? '#007bff' : '#fff'};
    color: ${props => props.$primary ? '#fff' : '#333'};
    border: 1px solid ${props => props.$primary ? '#007bff' : '#e0e0e0'};
    border-radius: 8px;
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.2s ease;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;

    &:hover {
        background: ${props => props.$primary ? '#0056b3' : '#f8f9fa'};
        border-color: ${props => props.$primary ? '#0056b3' : '#bbb'};
    }
`;

export const CheckboxGroup = styled.div`
    display: flex;
    gap: 12px;
    margin-top: 24px;
`;

export const PriceInput = styled(Input)`
    &::-webkit-outer-spin-button,
    &::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    -moz-appearance: textfield;
`;

export const CalculatedPrice = styled.div`
    margin-top: 8px;
    color: #007bff;
    font-size: 14px;
    font-weight: 500;
`;