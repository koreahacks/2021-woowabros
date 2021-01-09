import React from "react";
import styled from "styled-components";
import ImageSrc from "../../ImageSrc";

const SearchWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 0.75rem;
`;
const SearchBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: solid black 1px;
  border-radius: 10px;
  padding: 0 0.25rem;
  width: 15rem;
  height: 2rem;
`;
const SearchInput = styled.input`
  font-size: 0.8rem;
  border: 0;
  width: 12rem;
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
