import styled from 'styled-components';
import React, { ChangeEvent, useEffect, useState } from 'react';
import { ProductImageData } from '../../type/product.ts';

const UploadArea = styled.label<{ $isDragging: boolean }>`
    border: 2px dashed ${props => props.$isDragging ? '#007bff' : '#ddd'};
    border-radius: 4px;
    padding: 40px 20px;
    text-align: center;
    cursor: pointer;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    position: relative;
    background-color: ${props => props.$isDragging ? 'rgba(0, 123, 255, 0.05)' : '#fff'};
    transition: all 0.2s ease;
    min-height: 150px;
    width: 100%;
    box-sizing: border-box;
    margin-bottom: 20px;
`;

const ImagePreviewContainer = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
    gap: 16px;
    width: 100%;
`;

const ImagePreviewWrapper = styled.div`
    position: relative;
    aspect-ratio: 1;
    border-radius: 4px;
    overflow: hidden;
    border: 1px solid #ddd;
`;

const PreviewImage = styled.img`
    width: 100%;
    height: 100%;
    object-fit: cover;
`;

const DeleteButton = styled.button`
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

const UploadText = styled.div`
    color: #666;
    font-size: 0.9rem;
    margin-top: 8px;
`;

const MaxCount = styled.div`
    color: #999;
    font-size: 0.8rem;
    margin-top: 4px;
`;

interface ProductImageProps {
  images: ProductImageData[];
  onChangeImage: (images: ProductImageData[]) => void;
  onDeleteImage: (index: number) => void;
  maxImages?: number;
}

export default function ProductImage({ images, onChangeImage, onDeleteImage, maxImages = 5 }: ProductImageProps) {
  const [isDragging, setIsDragging] = useState(false);

  const handleImageChange = (e: ChangeEvent<HTMLInputElement>) => {
    const files = Array.from(e.target.files || []);

    if (images.length + files.length > maxImages) {
      alert(`최대 ${maxImages}개의 이미지만 업로드할 수 있습니다.`);
      return;
    }

    const newImages: ProductImageData[] = files.map(file => ({
      file, preview: URL.createObjectURL(file),
    }));

    onChangeImage([...images, ...newImages]);
  };

  const handleDragEnter = (e: React.DragEvent<HTMLLabelElement>) => {
    e.preventDefault();
    setIsDragging(true);
  };


  const handleDragOver = (e: React.DragEvent<HTMLLabelElement>) => {
    e.preventDefault();
    setIsDragging(true);
  };

  const handleDragLeave = (e: React.DragEvent<HTMLLabelElement>) => {
    e.preventDefault();
    setIsDragging(false);
    if (e.currentTarget === e.target) setIsDragging(false);
  };

  const handleDrop = (e: React.DragEvent<HTMLLabelElement>) => {
    e.preventDefault();
    setIsDragging(false);

    const files = Array.from(e.dataTransfer.files);

    if (files.some(file => !file.type.startsWith('image/'))) {
      alert('이미지 파일만 업로드 가능합니다.');
      return;
    }

    if (images.length + files.length > maxImages) {
      alert(`최대 ${maxImages}개의 이미지만 업로드할 수 있습니다.`);
      return;
    }

    const newImages: ProductImageData[] = files.map(file => ({
      file,
      preview: URL.createObjectURL(file),
    }));

    onChangeImage([...images, ...newImages]);
  };

  useEffect(() => {
    return () => {
      images.forEach(image => URL.revokeObjectURL(image.preview));
    };
  }, [images]);

  return (
    <section>
      <h3>상품 이미지</h3>
      <UploadArea
        htmlFor="image-upload"
        onDragEnter={handleDragEnter}
        onDragOver={handleDragOver}
        onDragLeave={handleDragLeave}
        onDrop={handleDrop}
        $isDragging={isDragging}
      >
        <input
          type="file"
          accept="image/*"
          multiple
          onChange={handleImageChange}
          style={{ display: 'none' }}
          id="image-upload"
        />
        <UploadText>
          {isDragging ? ('이미지를 놓으세요.') : (`클릭하여 이미지를 업로드하세요 (최대 ${maxImages}개)`)}
        </UploadText>
        <MaxCount>최대 {maxImages}개의 이미지 업로드 가능</MaxCount>
      </UploadArea>

      <ImagePreviewContainer>
        {images.map((image, index) => (
          <ImagePreviewWrapper key={image.preview}>
            <PreviewImage src={image.preview} alt={`Preview ${index + 1}`} />
            <DeleteButton onClick={() => onDeleteImage(index)}>x</DeleteButton>
          </ImagePreviewWrapper>
        ))}
      </ImagePreviewContainer>
    </section>
  );
};
