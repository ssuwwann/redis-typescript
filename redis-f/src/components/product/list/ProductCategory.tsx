import { Button, Grid, Section } from '../../assets/css/productStyle.ts';
import styled from 'styled-components';
import { useState } from 'react';
import ProductCategoryModal from './ProductCategoryModal.tsx';

const SelectedCategoryText = styled.div`
    margin-top: 12px;
    padding: 8px 12px;
    background: #f8f9fa;
    border: 1px solid #e0e0e0;
    border-radius: 4px;
    display: inline-flex;
    align-items: center;
`;

interface CategorySelectProps {
  isModalOpen: boolean;
  onChange: (value: string) => void;
  handleModal: (flag: boolean) => void;
}

interface Categories {
  [key: string]: string[];
}

const categories: Categories = {
  '가전': ['노트북', '태블릿', '데스크탑', '게임기', '생활가전'],
  '의류': ['상의', '하의', '신발', '모자', '잡화'],
  '식품': ['밥', '반찬', '김밥식', '고기', '채소'],
  '도서': ['추리', 'SF소설', '시', '자서전', '전공서적'],
};

export default function ProductCategory({
                                          onChange,
                                          isModalOpen,
                                          handleModal,
                                        }: CategorySelectProps) {
  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);
  const [selectedPrimary, setSelectedPrimary] = useState<string>('');

  const handlePrimarySelect = (category: string) => {
    setSelectedPrimary(category);
    handleModal(true);
  };

  const handleSecondarySelect = (secondaryCategory: string) => {
    const newCategories = `${selectedPrimary}, ${secondaryCategory}`;
    setSelectedCategories([selectedPrimary, secondaryCategory]);
    onChange(newCategories);
    handleModal(false);
  };

  return (
    <Section>
      <h3>카테고리 선택</h3>
      <Grid>
        {Object.keys(categories).map(category => (
          <Button
            type="button"
            key={category}
            onClick={() => handlePrimarySelect(category)}
            $primary={category === selectedPrimary}
          >
            {category}
          </Button>
        ))}
      </Grid>
      {selectedCategories.length > 0 && (
        <SelectedCategoryText>
          {selectedCategories.join(' > ')}
        </SelectedCategoryText>
      )}
      {isModalOpen && (
        <ProductCategoryModal
          isOpen={isModalOpen}
          handleModal={handleModal}
          onSelect={handleSecondarySelect}
          categories={categories[selectedPrimary]}
          title={`${selectedPrimary} 하위 카테고리`}
        />
      )}
    </Section>
  );
};
