import React from "react";
import styled from "styled-components";
const DeleteButtonWrapper = styled.div`
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
  color: #800000;
`;
const DeleteButton = () => {
    // TODO: delete by onclick event handler
  return (
      <DeleteButtonWrapper>
          <Text>삭제</Text>
      </DeleteButtonWrapper>
  );
};

export default DeleteButton;
