import styled from 'styled-components';
import { Section } from '../../../assets/css/productStyle.ts';

const DescriptionSection = styled(Section)`
    margin-top: 24px;
`;

const DescriptionImage = styled.img`
    width: 100%;
    border-radius: 8px;
    margin-bottom: 24px;
`;

const DescriptionText = styled.div`
    font-size: 14px;
    line-height: 1.6;
    color: #666;

    p {
        margin-bottom: 16px;

        &:last-child {
            margin-bottom: 0;
        }
    }
`;


export default function({ description, descriptionImage }: {
  description: string | undefined,
  descriptionImage: Blob | null
}) {

  return (
    <DescriptionSection>
      <h3>상품 상세정보</h3>

      {descriptionImage && ( // descriptionImage null 체크 추가
        <DescriptionImage
          src={URL.createObjectURL(descriptionImage)}
          alt="Product description image"
        />
      )}

      {description && (
        <DescriptionText>
          {description.split('\n').map((paragraph, idx) => (
            <p key={idx}>{paragraph}</p>
          ))}
        </DescriptionText>
      )}
    </DescriptionSection>
  );
}
