import { ChangeEvent } from 'react';
import {
  CalculatedPrice, CheckboxGroup,
  Input,
  InputGroup,
  Label,
  PriceInput,
  Section,
  Textarea,
} from '../../assets/css/productStyle.ts';
import { ProductFormData, ProductImageData } from '../../type/product.ts';
import styled from 'styled-components';

const DescriptionImageUpload = styled.label`
    display: inline-flex;
    align-items: center;
    padding: 8px 16px;
    gap: 8px;
    cursor: pointer;
    border: 1px solid #e0e0e0;
    border-radius: 8px;
    background: #fff;
    font-size: 14px;
    color: #666;
    margin-top: 12px;
    transition: all 0.2s ease;

    &:hover {
        background: #f8f9fa;
        border-color: #bbb;
    }
`;

export const DescriptionImagePreview = styled.div`
    position: relative;
    width: 200px;
    margin-top: 16px;
`;

export const PreviewImage = styled.img`
    width: 100%;
    height: auto;
    border-radius: 8px;
    border: 1px solid #e0e0e0;
`;

export const DeleteImageButton = styled.button`
    position: absolute;
    top: 8px;
    right: 8px;
    width: 24px;
    height: 24px;
    border-radius: 12px;
    background: rgba(255, 255, 255, 0.9);
    border: 1px solid #ddd;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    color: #666;
    font-size: 14px;

    &:hover {
        background: #fff;
        color: #ff4444;
    }
`;

interface ProductInfoFormProps {
  formData: ProductFormData;
  onChange: (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
  onDescriptionImageChange: (image: ProductImageData | null) => void;
}

export default function ProductInfo({ formData, onChange, onDescriptionImageChange }: ProductInfoFormProps) {
  const handleDescriptionImageChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const imageData: ProductImageData = {
        file,
        preview: URL.createObjectURL(file),
      };
      onDescriptionImageChange(imageData);
    }
  };

  const handleDeleteDescriptionImage = () => {
    onDescriptionImageChange(null);
  };

  console.log(formData);

  return (
    <Section>
      <h3>상품 정보</h3>

      <InputGroup>
        <Label>상품명</Label>
        <Input
          name="title"
          value={formData.title}
          onChange={onChange}
          placeholder="상품명을 입력하세요"
        />
      </InputGroup>

      <InputGroup>
        <Label>상품 설명</Label>
        <Textarea
          name="description"
          value={formData.description}
          onChange={onChange}
          placeholder="상품 설명을 입력하세요"
        />

        <DescriptionImageUpload>
          이미지 추가
          <input
            type="file"
            accept="image/*"
            onChange={handleDescriptionImageChange}
            style={{ display: 'none' }}
          />
        </DescriptionImageUpload>

        {formData.descriptionImage && (
          <DescriptionImagePreview>
            <PreviewImage
              src={formData.descriptionImage.preview}
              alt="상품 이미지 설명"
            />
            <DeleteImageButton type="button" onClick={handleDeleteDescriptionImage}>&times;</DeleteImageButton>
          </DescriptionImagePreview>
        )}
      </InputGroup>

      <InputGroup className="price-group">
        <div>
          <Label>원가</Label>
          <PriceInput
            type="number"
            name="originalPrice"
            value={formData.originalPrice}
            onChange={onChange}
            min="0"
          />
        </div>
        <div>
          <Label>할인율</Label>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
            <PriceInput
              type="number"
              name="discountRate"
              value={formData.discountRate}
              onChange={onChange}
              min="0"
              max="100"
            />
            <span className="unit">%</span>
          </div>
        </div>
        {formData.originalPrice > 0 && formData.discountRate > 0 && (
          <CalculatedPrice>
            할인가: {(formData.originalPrice * (1 - formData.discountRate / 100)).toLocaleString()}원
          </CalculatedPrice>
        )}
      </InputGroup>

      <InputGroup>
        <Label>재고 수량</Label>
        <PriceInput
          type="number"
          name="quantity"
          value={formData.quantity}
          onChange={onChange}
          min="0"
        />
      </InputGroup>

      <CheckboxGroup>
        <Label className={`checkbox-label ${formData.freeDelivery ? 'checked' : ''}`}>
          <input
            type="checkbox"
            name="freeDelivery"
            checked={formData.freeDelivery}
            onChange={onChange}
          />
          무료 배송
        </Label>

        <Label className={`checkbox-label ${formData.isSpecialPrice ? 'checked' : ''}`}>
          <input
            type="checkbox"
            name="isSpecialPrice"
            checked={formData.isSpecialPrice}
            onChange={onChange}
          />
          특가 상품
        </Label>
      </CheckboxGroup>
    </Section>
  );
};
