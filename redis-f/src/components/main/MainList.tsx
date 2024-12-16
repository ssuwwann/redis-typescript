import styled from 'styled-components';
import MainListItem from './MainListItem.tsx';
import { useEffect, useState } from 'react';
import { getProducts } from '../../api/product.ts';
import { ProductListData } from '../../type/product.ts';


const ListContainer = styled.div`
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
    padding: 20px;

    @media (max-width: 768px) {
        grid-template-columns: repeat(2, 1fr);
    }

    @media (max-width: 480px) {
        grid-template-columns: 1fr;
    }
`;

export default function MainList() {
  const [products, setProducts] = useState<ProductListData[]>([]);

  const fetchAllProduct = async () => {
    const result = await getProducts();
    setProducts(result);
  };

  useEffect(() => {
    fetchAllProduct();
  }, []);

  return (
    <ListContainer>
      {products.map(product => (
        <MainListItem key={product.id} product={product} />
      ))}
    </ListContainer>
  );
};