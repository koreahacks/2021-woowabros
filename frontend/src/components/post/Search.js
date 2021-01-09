import React from "react";
import styled from "styled-components";
import ImageSrc from "../../ImageSrc";

const SearchWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
`;
const SearchBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid #ddd 1px;
  border-radius: 2px;
  width: calc(100% - 1.75rem);
  padding: 0.5rem 0.25rem;
  margin: 0.5rem 0;
`;
const SearchInput = styled.input`
  font-size: 0.8rem;
  border: 0;
  width: 100%;
  &:focus {
    outline: none;
  }
`;
const SearchIcon = styled.div`
  width: 1rem;
  height: 1rem;
`;
const SearchImage = styled.img`
  width: 100%;
  height: 100%;
`;
const Search = ({
  search,
  handleSearchChange,
  handleEnterKeyPress,
  handleSearchIconClick,
}) => {
  return (
    <SearchWrapper>
      <SearchBox>
        <SearchInput
          type="text"
          value={search}
          onChange={handleSearchChange}
          onKeyPress={handleEnterKeyPress}
          placeholder="검색어를 입력하세요"
        />
        <SearchIcon onClick={handleSearchIconClick}>
          <SearchImage src={ImageSrc.SEARCH_ICON} alt="검색 아이콘" />
        </SearchIcon>
      </SearchBox>
    </SearchWrapper>
  );
};

export default Search;
