import styled from 'styled-components';
import { CloseButton, ModalContent, ModalHeader, ModalOverlay, ModalTitle } from '../../assets/css/CommonModalStyle.ts';
import { Button } from '../../assets/css/productStyle.ts';

const CategoryGrid = styled.div`
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 8px;
    padding: 20px;
`;

interface CategoryModalProps {
  isOpen: boolean;
  handleModal: (flag: boolean) => void;
  onSelect: (secondaryCategory: string) => void;
  categories: string[];
  title: string;
}

export default function ProductCategoryModal({
                                               isOpen,
                                               handleModal,
                                               onSelect,
                                               categories,
                                               title,
                                             }: CategoryModalProps) {

  if (!isOpen) return null;

  return (
    <ModalOverlay onClick={() => handleModal(false)}>
      <ModalContent onClick={(e) => e.stopPropagation()}>
        <ModalHeader>
          <ModalTitle>{title}</ModalTitle>
          <CloseButton onClick={() => handleModal(false)}>&times;</CloseButton>
        </ModalHeader>
        <CategoryGrid>
          {categories.map(category => (
            <Button type="button" key={category} onClick={() => onSelect(category)}>
              {category}
            </Button>
          ))}
        </CategoryGrid>
      </ModalContent>
    </ModalOverlay>
  );
}