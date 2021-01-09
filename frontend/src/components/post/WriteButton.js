import React from "react";
import styled from "styled-components";
import ImageSrc from "../../ImageSrc";

const WriteButtonWrapper = styled.div`
  display: flex;
  align-items: center;
  text-decoration: none;
  padding-right: 1rem;
`;

const ButtonImageWrapper = styled.div`
  width: 24px;
  height: 24px;
  margin-left: 0.5rem;
`;

const ButtonImage = styled.img`
  height: 100%;
`;

const Text = styled.p`
  font-size: 16px;
  color: #aa8fbf;
`;
const WriteButton = () => {
  return (
      <WriteButtonWrapper>
        <ButtonImageWrapper>
            <ButtonImage src={ImageSrc.NEW} alt="add post"/>
        </ButtonImageWrapper>
      </WriteButtonWrapper>
  );
};

export default WriteButton;
