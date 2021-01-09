import React from "react";
import styled from "styled-components";

import { SharedPurpleWrapper } from "../../util/SharedStyles";
import IMAGE_SRC from "../../ImageSrc";

const PostWrapper = styled(SharedPurpleWrapper)`
   ;
`;

const ButtonWrapper = styled.div`
  width: 60%;
  display: flex;
  justify-content: space-between;
`;

const ButtonImg = styled.img`
  width: 2rem;
  height: 2rem;
`;

const Button = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ButtonText = styled.div`
  margin-top: 0.5rem;
  font-size: 0.75rem;
`;

const PostButtons = ({ functions }) => {
  return (
    <PostWrapper>
      <ButtonWrapper>
        <Button onClick={() => functions.go("/posts/named")}>
          <ButtonImg src={IMAGE_SRC.ANONYMOUS} alt="anonymous-board-image" />
          <ButtonText>실명게시판</ButtonText>
        </Button>
        <Button onClick={() => functions.go("/posts/anonymous")}>
          <ButtonImg src={IMAGE_SRC.NAMED} alt="named-board-image" />
          <ButtonText>익명게시판</ButtonText>
        </Button>
      </ButtonWrapper>
    </PostWrapper>
  );
};

export default PostButtons;
