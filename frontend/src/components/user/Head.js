import React from "react";
import styled from "styled-components";
import BackButton from "./BackButton";

const HeadWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 1rem;
  margin-bottom: 0.75rem;
  width: calc(100% - 1rem);
  height: 1.625rem;
`;

const Head = ({ type }) => {
  return (
    <>
      <HeadWrapper>
        <BackButton type={type} />
      </HeadWrapper>
    </>
  );
};

export default Head;
