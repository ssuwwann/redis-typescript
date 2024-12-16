import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { ProductListData } from '../../type/product.ts';

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

const TagContainer = styled.div`
    height: 28px; // 특가 태그의 고정된 높이
    margin-bottom: 10px;
`;

const SpecialPrice = styled.div`
    display: inline-block;
    background: #674ea7;
    color: white;
    padding: 4px 8px;
    border-radius: 4px;
    font-size: 12px;
`;

const ProductImage = styled.img`
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 4px;
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
    min-height: 60px; // 가격 정보의 최소 높이 설정
`;

const DiscountContainer = styled.div`
    height: 24px; // 할인율을 위한 고정된 높이
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
    height: 20px; // 배송 정보를 위한 고정된 높이
`;

interface MainListItemProps {
  product: ProductListData;
}

export default function MainListItem({ product }: MainListItemProps) {
  return (
    <CardLink key={product.id} to={`/products/${product.id}`}>
      <TagContainer>
        {product.isSpecialPrice && <SpecialPrice>주말특가</SpecialPrice>}
      </TagContainer>
      <ProductImage src={product.image} alt={product.title} />
      <ProductTitle>{product.title}</ProductTitle>
      <PriceInfo>
        <DiscountContainer>
          {product.discountRate > 0 && (
            <Discount>{product.discountRate}%</Discount>
          )}
        </DiscountContainer>
        <Price>{product.currentPrice.toLocaleString()}원</Price>
        <OriginalPrice>{product.originalPrice.toLocaleString()}원</OriginalPrice>
      </PriceInfo>
      <DeliveryInfo>{product.delivery}</DeliveryInfo>
    </CardLink>
  );
}