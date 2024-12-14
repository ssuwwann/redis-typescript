import { ChangeEvent, FormEvent, useState } from 'react';
import { ProductFormDataWithDesc, ProductImageData } from '../../type/product.ts';
import { Button, PageContainer } from '../../assets/css/productStyle.ts';
import ProductImage from '../../components/product/ProductImage.tsx';
import ProductCategory from '../../components/product/ProductCategory.tsx';
import ProductInfo from '../../components/product/ProductInfo.tsx';
import { saveProduct } from '../../api/product.ts';

const initProductFormData: ProductFormDataWithDesc = {
  category: '',
  title: '',
  description: '',
  originalPrice: 0,
  discountRate: 0,
  currentPrice: 0,
  isSpecialPrice: false,
  freeDelivery: false,
  quantity: 0,
  descriptionImage: null,
  productImages: [],
};

export default function ProductRegisterPage() {
  const [formData, setFormData] = useState<ProductFormDataWithDesc>(initProductFormData);
  const [images, setImages] = useState<ProductImageData[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value, type } = e.target;
    const newValue = type === 'checkbox' ?
      (e.target as HTMLInputElement).checked :
      value;

    setFormData(prev => {
      const updated = { ...prev, [name]: newValue };

      if (name === 'originalPrice' || name === 'discountRate') {
        const originalPrice = name === 'originalPrice' ? Number(value) : prev.originalPrice;
        const discountRate = name === 'discountRate' ? Number(value) : prev.discountRate;

        updated.currentPrice = originalPrice * (1 - discountRate / 100);
      }

      return updated;
    });
  };

  const handleCategoryChange = (category: string) => {
    setFormData(prev => ({ ...prev, category: category }));
  };

  const handleChangeImage = (newImages: ProductImageData[]) => {
    setImages(newImages);
  };

  const handleDeleteImage = (index: number) => {
    setImages(prev => prev.filter((_, i) => i !== index));
  };

  const handleDescriptionImageChange = (image: ProductImageData | null) => {
    setFormData(prev => ({ ...prev, descriptionImage: image }));
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    const newFormData = new FormData();

    newFormData.append('category', formData.category);
    newFormData.append('title', formData.title);
    newFormData.append('description', formData.description);
    newFormData.append('originalPrice', String(formData.originalPrice));
    newFormData.append('discountRate', String(formData.discountRate));
    newFormData.append('currentPrice', String(formData.currentPrice));
    newFormData.append('isSpecialPrice', String(formData.isSpecialPrice));
    newFormData.append('freeDelivery', String(formData.freeDelivery));
    newFormData.append('quantity', String(formData.quantity));

    if (formData.descriptionImage?.file) newFormData.append('descriptionImage', formData.descriptionImage.file);

    if (images.length > 0) {
      images.forEach(image => {
        if (image.file) newFormData.append('productImages', image.file);
      });
    }

    try {
      await saveProduct(newFormData);
    } catch (error) {
      console.error('Failed to save product', error);
    }
  };

  const handleModal = (flag: boolean) => {
    setIsModalOpen(flag);
  };

  return (
    <PageContainer>
      <form onSubmit={handleSubmit}>
        <ProductCategory onChange={handleCategoryChange}
                         isModalOpen={isModalOpen}
                         handleModal={handleModal}
        />
        <ProductInfo formData={formData} onChange={handleChange}
                     onDescriptionImageChange={handleDescriptionImageChange} />
        <ProductImage images={images} onChangeImage={handleChangeImage} onDeleteImage={handleDeleteImage}
                      maxImages={5}
        />

        <Button $primary>상품 등록</Button>
      </form>
    </PageContainer>
  );
};
