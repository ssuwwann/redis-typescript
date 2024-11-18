import styled from 'styled-components';
import vite from '/vite.svg';
import { Link } from 'react-router-dom';

// 카드 리스트 컨테이너
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

const CardLink = styled(Link)`
    text-decoration: none;
    color: inherit;
    display: block;
    border: 1px solid #e0e0e0;
    border-radius: 8px;
    padding: 15px;
    background: white;
    transition: transform 0.2s;

    &:hover {
        transform: translateY(-5px);
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }
`;

const ProductImage = styled.img`
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 4px;
    margin-bottom: 10px;
`;

const SpecialPrice = styled.div`
    display: inline-block;
    background: #674ea7;
    color: white;
    padding: 4px 8px;
    border-radius: 4px;
    font-size: 12px;
    margin-bottom: 10px;
`;

const ProductTitle = styled.h3`
    font-size: 16px;
    margin: 10px 0;
    line-height: 1.4;
    height: 44px;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
`;

const PriceInfo = styled.div`
    margin-top: 10px;
`;

const Discount = styled.span`
    color: #f04141;
    font-weight: bold;
    margin-right: 8px;
`;

const Price = styled.span`
    font-size: 18px;
    font-weight: bold;
`;

const OriginalPrice = styled.div`
    color: #999;
    text-decoration: line-through;
    font-size: 14px;
    margin-top: 4px;
`;

const DeliveryInfo = styled.div`
    font-size: 13px;
    color: #666;
    margin-top: 8px;
`;

interface Product {
  id: number;
  category: string;
  image: string;
  title: string;
  originalPrice: number;
  discountRate: number;
  currentPrice: number;
  isSpecialPrice: boolean;
  delivery?: string;
}

const MainList = () => {
  const products: Product[] = [
    {
      id: 1,
      category: '식품',
      image: vite,
      title: '미래생활 순수PURE전 연필프 25m 30롤',
      originalPrice: 35900,
      discountRate: 19,
      currentPrice: 28900,
      isSpecialPrice: true,
      delivery: '무료배송'
    }, {
      id: 2,
      category: '식품',
      image: vite,
      title: '미래생활 순수PURE전 연필프 25m 30롤',
      originalPrice: 35900,
      discountRate: 19,
      currentPrice: 28900,
      isSpecialPrice: true,
      delivery: '무료배송'
    }, {
      id: 3,
      category: '식품',
      image: vite,
      title: '미래생활 순수PURE전 연필프 25m 30롤',
      originalPrice: 35900,
      discountRate: 19,
      currentPrice: 28900,
      isSpecialPrice: true,
      delivery: '무료배송'
    }
  ];

  return (
    <ListContainer>
      {products.map(product => (
        <CardLink key={product.id} to={`/products/${product.id}`}>
          {product.isSpecialPrice && <SpecialPrice>주말특가</SpecialPrice>}
          <ProductImage src={product.image} alt={product.title} />
          <ProductTitle>{product.title}</ProductTitle>
          <PriceInfo>
            <Discount>{product.discountRate}%</Discount>
            <Price>{product.currentPrice.toLocaleString()}원</Price>
            <OriginalPrice>{product.originalPrice.toLocaleString()}원</OriginalPrice>
          </PriceInfo>
          {product.delivery && <DeliveryInfo>{product.delivery}</DeliveryInfo>}
        </CardLink>
      ))}
    </ListContainer>
  );
};

export default MainList;