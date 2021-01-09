import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import ImageSrc from "../../ImageSrc";

const BackButtonWrapper = styled(Link)`
  display: flex;
  align-items: center;
  text-decoration: none;
`;
const ButtonImageWrapper = styled.div`
  height: 1.2rem;
  margin-right: 0.5rem;
`;
const ButtonImage = styled.img``;
const Text = styled.p`
  margin: 0;
  height: 1.2rem;
  color: #392f31;
`;
const BackButton = ({ type }) => {
  return (
    <BackButtonWrapper to="/">
      <ButtonImageWrapper>
        <ButtonImage src={ImageSrc.BACK} alt="뒤로가기" />
      </ButtonImageWrapper>
      <Text>{type} 게시판</Text>
    </BackButtonWrapper>
  );
};

export default BackButton;
