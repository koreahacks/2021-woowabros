import React from "react";
import { useHistory } from "react-router-dom";
import styled from "styled-components";
import ImageSrc from "../../ImageSrc";

const BackButtonWrapper = styled.div`
  display: flex;
  align-items: center;
  text-decoration: none;
  padding-left: 1rem;
`;
const ButtonImageWrapper = styled.div`
  height: 24px;
  width: 24px;
  margin-right: 0.5rem;
`;
const ButtonImage = styled.img`
    height: 80%;
`;
const Text = styled.p`
  margin: 0;
  height: 1.2rem;
  color: #392f31;
`;
const BackButton = () => {
    const history = useHistory();
    const handleBackButtonClick = () => {
        history.goBack();
    }
  return (
    <BackButtonWrapper onClick={handleBackButtonClick}>
      <ButtonImageWrapper>
        <ButtonImage src={ImageSrc.BACK} alt="뒤로가기" />
      </ButtonImageWrapper>
    </BackButtonWrapper>
  );
};

export default BackButton;
