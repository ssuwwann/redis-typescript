import ProductImage from '../../components/product/ProductImage.tsx';
import ProductInfo from '../../components/product/ProductInfo.tsx';
import ProductDesc from '../../components/product/ProductDesc.tsx';

const ProductPage = () => {
  return (
    <>
      <ProductImage />
      <ProductInfo />
      <ProductDesc />
    </>
  );
};

export default ProductPage;