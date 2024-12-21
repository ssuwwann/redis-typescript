import styled from 'styled-components';
import { Section } from '../../../assets/css/productStyle.ts';
import { useEffect, useState } from 'react';

const ImageSection = styled(Section)`
    position: relative;
    padding: 0;
    overflow: hidden;
    aspect-ratio: 1;
`;

const ProductImage = styled.img`
    width: 100%;
    height: 100%;
    object-fit: cover;
`;

const NavigationButton = styled.button`
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    background: rgba(255, 255, 255, 0.8);
    border: none;
    border-radius: 50%;
    width: 40px;
    height: 40px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;

    &:hover {
        background: rgba(255, 255, 255, 0.9);
    }

    &.prev {
        left: 16px;
    }

    &.next {
        right: 16px;
    }
`;

const ImageIndicators = styled.div`
    position: absolute;
    bottom: 16px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    gap: 8px;
`;

const Indicator = styled.button<{ $active: boolean }>`
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background: ${props => props.$active ? '#007bff' : 'rgba(255, 255, 255, 0.6)'};
    border: none;
    padding: 0;
    cursor: pointer;
`;

export default function ProductImages({ productImages }: { productImages: Blob[] }) {
  const [currentIdx, setCurrentIdx] = useState<number>(0);

  const handlePrev = () => {
    setCurrentIdx(prev => prev === 0 ? productImages.length - 1 : prev - 1);
  };

  const handleNext = () => {
    setCurrentIdx(prev => prev === productImages.length - 1 ? 0 : prev + 1);
  };

  useEffect(() => {
    if (productImages.length > 0 && currentIdx >= productImages.length) {
      setCurrentIdx(0);
    }
  }, [productImages.length]);

  return (
    <ImageSection>
      {productImages[currentIdx] && (
        <ProductImage
          src={URL.createObjectURL(productImages[currentIdx])}
          alt="product image"
        />
      )}
      {productImages.length > 1 && (
        <>
          <NavigationButton className="prev" onClick={handlePrev}>
            ←
          </NavigationButton>

          <NavigationButton className="next" onClick={handleNext}>
            →
          </NavigationButton>
          <ImageIndicators>
            {productImages.map((_, idx) => (
              <Indicator
                key={idx}
                $active={currentIdx === idx}
                onClick={() => setCurrentIdx(idx)} />
            ))}
          </ImageIndicators>
        </>
      )}


    </ImageSection>
  );
};
