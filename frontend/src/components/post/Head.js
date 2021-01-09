import React from "react";
import styled from "styled-components";
import BackButton from "./BackButton";
import WriteButton from "./WriteButton";

const HeadWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 1rem;
  margin-bottom: .75rem;
  width: calc(100% - 1rem);
  height: 1.625rem;
`;

const Head = ({ type }) => {

  return (
    <>
      <HeadWrapper>
        <BackButton type={type} />
        <WriteButton>글쓰기</WriteButton>
      </HeadWrapper>
    </>
  );
};

export default Head;
