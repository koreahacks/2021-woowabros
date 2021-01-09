import React from "react";
import styled from "styled-components";

const WriteButtonWrapper = styled.div`
  width: 3.5rem;
  height: 1.125rem;
  display: flex;
  justify-content: center;
  align-items: center;
  border: solid #707070 2px;
  border-radius: 10px;
`;
const Text = styled.p`
  font-size: 16px;
  color: #aa8fbf;
`;
const WriteButton = () => {
  return (
    <WriteButtonWrapper>
      <Text>글쓰기</Text>
    </WriteButtonWrapper>
  );
};

export default WriteButton;
