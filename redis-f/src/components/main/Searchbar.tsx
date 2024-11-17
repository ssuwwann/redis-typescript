import { ChangeEvent, FormEvent, useState } from 'react';
import styled from 'styled-components';


const SearchbarContainer = styled.div`
    width: 100%;
    max-width: 600px;
    margin: 0 auto;
`;

const InputWrapper = styled.div`
    display: flex;
    align-items: stretch;
    height: 40px;
`;

const Input = styled.input`
    flex: 1;
    padding: 0 16px;
    border-top: 1px solid #000;
    border-bottom: 1px solid #000;
    font-size: 16px;

    &:focus {
        outline: none;
        border-color: #3182ce;
    }
`;

const SearchButton = styled.button`
    width: 60px;
    height: 100%;
    background-color: #3182ce;
    color: white;
    border: 1px solid #3182ce;
    cursor: pointer;
    transition: background-color 0.2s;
    margin-left: -1px;
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;

    &:hover {
        background-color: #2c5282;
    }
`;

const SearchType = styled.select`
    width: 100px;
    height: 100%;
    border: 1px solid #000;
    border-right: none;
    font-size: 14px;
    cursor: pointer;
    background-color: white;
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px;
    padding: 0 20px 0 10px;

    // Custom 화살표 추가
    background-repeat: no-repeat;
    background-position: right 5px center;
    background-size: 16px;

    &:focus {
        outline: none;
        border-color: #3182ce;
    }
`;


interface SearchInfo {
  type: string;
  keyword: string;
}

const initSearchInfo = {
  type: 'productName', // 기본값으로 지정하기 위해
  keyword: ''
};

const Searchbar = () => {
  const [searchInfo, setSearchInfo] = useState<SearchInfo>(initSearchInfo);

  const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setSearchInfo(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSearch = (e: FormEvent) => {
    e.preventDefault();

    if (searchInfo.keyword.trim()) {
      console.log('검색 타입:', searchInfo.type);
      console.log('검색어:', searchInfo.keyword);
      // 이제 검색 로직 추가
    }
  };

  return (
    <SearchbarContainer>
      <form onSubmit={handleSearch}>
        <InputWrapper>
          <SearchType>
            <option value="productName">상품명</option>
            <option value="seller">판매자</option>
          </SearchType>
          <Input
            type="text"
            value={searchInfo.keyword}
            name="keyword"
            onChange={handleChange}
            placeholder="검색어를 입력하세요"
          />
          <SearchButton type="submit">검색</SearchButton>
        </InputWrapper>
      </form>
    </SearchbarContainer>
  );
};

export default Searchbar;