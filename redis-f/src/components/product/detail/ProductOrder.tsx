import styled from 'styled-components';
import { Button, Input, Label, Section } from '../../../assets/css/productStyle.ts';
import { ProductDetailData } from '../../../type/product.ts';

const ProductSection = styled(Section)`
    margin-top: 24px;
`;

const ProductTitle = styled.h1`
    font-size: 24px;
    font-weight: 600;
    color: #333;
    margin-bottom: 24px;
`;

const PriceInfo = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 16px 0;
`;

const DiscountBadge = styled.span`
    color: #ff4646;
    font-weight: 600;
    font-size: 18px;
`;

const Price = styled.div`
    text-align: right;
`;

const CurrentPrice = styled.span`
    font-size: 24px;
    font-weight: 600;
    color: #333;
`;

const OriginalPrice = styled.span`
    color: #999;
    text-decoration: line-through;
    margin-left: 8px;
`;

const DeliveryBadge = styled.div`
    display: inline-block;
    padding: 4px 12px;
    background: #f0f7ff;
    color: #007bff;
    border-radius: 4px;
    font-size: 14px;
    font-weight: 500;
    margin-bottom: 24px;
`;

export default function({ product }: { product: ProductDetailData | undefined }) {
  if (!product) return null;

  return (
    <ProductSection>
      <ProductTitle>{product.title}</ProductTitle>

      <PriceInfo>
        {product.discountRate > 0 && (
          <>
            <DiscountBadge>{product.discountRate}% OFF</DiscountBadge>
          </>
        )}
        <Price>
          <>
            <CurrentPrice>{product.currentPrice}원</CurrentPrice>
            <OriginalPrice>{product.originalPrice}원</OriginalPrice>
          </>
        </Price>
      </PriceInfo>

      {product.freeDelivery && (
        <DeliveryBadge>무료 배송</DeliveryBadge>
      )}

      <Label htmlFor="quantity">수량</Label>
      <Input
        id="quantity"
        type="number"
        min="1"
        defaultValue="1"
        style={{ maxWidth: '160px' }}
      />

      <Button style={{ marginTop: '24px' }} $primary>장바구니 담기</Button>
      <Button style={{ marginTop: '12px' }}>바로 구매</Button>
    </ProductSection>
  );
}