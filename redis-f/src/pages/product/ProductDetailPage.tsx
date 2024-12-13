import ProductImage from '../../components/product/ProductImage.tsx';
import ProductInfo from '../../components/product/ProductInfo.tsx';
import ProductCategory from '../../components/product/ProductCategory.tsx';

const ProductDetailPage = () => {
  return (
    <>
      <ProductImage />
      <ProductInfo />
      <ProductCategory />
    </>
  );
};

export default ProductDetailPage;