import { useEffect, useState } from 'react';
import { ProductDetailData } from '../../type/product.ts';
import { PageContainer } from '../../assets/css/productStyle.ts';
import ProductImages from '../../components/product/detail/ProductImages.tsx';
import ProductOrder from '../../components/product/detail/ProductOrder.tsx';
import ProductDescription from '../../components/product/detail/ProductDescription.tsx';
import { useParams } from 'react-router-dom';
import { getImages, getProduct } from '../../api/product.ts';

export default function ProductDetailPage() {
  const [product, setProduct] = useState<ProductDetailData>();
  const [productImages, setProductImages] = useState<Blob[]>([]);
  const [descriptionImage, setDescriptionImage] = useState<Blob | null>(null);
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    const fetchData = async () => {
      try {
        // 1. 상품 정보 가져오기
        const productResult = await getProduct(id);
        setProduct(productResult);

        // 2. 상품 설명 이미지 가져오기
        if (productResult?.productImagesId?.descriptionImageId) {
          const descImage = await getImages(productResult.id);
          setDescriptionImage(descImage);
        }

        // 3. 상품 이미지들 가져오기
        if (productResult?.productImagesId?.productImageIds) {
          const imagePromises = productResult.productImagesId.productImageIds.map(imageId =>
            getImages(imageId),
          );
          const images = await Promise.all(imagePromises);
          setProductImages(images);
        }
      } catch (error) {
        console.error('Failed to fetch product data', error);
      }
    };

    fetchData();
  }, [id]);

  return (
    <PageContainer>
      <ProductImages productImages={productImages} />
      <ProductOrder product={product} />
      <ProductDescription
        description={product?.description}
        descriptionImage={descriptionImage}
      />
    </PageContainer>
  );
};
