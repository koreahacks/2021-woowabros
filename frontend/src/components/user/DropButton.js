import React from "react";
import styled from "styled-components";

const DropButtonWrapper = styled.div`
  margin-top: 1rem;
  display: flex;
  justify-content: center;
`;

const Drop = styled.div`
  width: 3.5rem;
  height: 1.125rem;
  color: #aa8fbf;
  border: 1px solid #707070;
  border-radius: 5px;
  text-align: center;
`;

const DropButton = ({ functions }) => {
  return (
    <DropButtonWrapper>
      <Drop onClick={() => functions.tryDrop()}>탈퇴</Drop>
    </DropButtonWrapper>
  );
};

export default DropButton;
