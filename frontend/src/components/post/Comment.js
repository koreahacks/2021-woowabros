import React from "react";
import styled from "styled-components";
import { AppWrapper } from "../../util/SharedStyles";
import ImageSrc from "../../ImageSrc";
import DeleteButton from "./DeleteButton";
const Commentwrapper = styled(AppWrapper)`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: calc(100% - 1rem);
`;
const UserWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 0.5rem;
  width: 100%;
`;
const UserName = styled.p`
  margin: 0;
`;
const UserSchool = styled.p`
  margin: 0;
`;
const Content = styled.pre`
  margin: 0 0 0.5rem 0;
  padding: 0 1rem;
  white-space: pre-wrap;
`;
const ButtonWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
`;
const LikesWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const Comment = ({ createdBy, content, likes, dislikes }) => {
  return (
    <Commentwrapper>
      <UserWrapper>
        <UserName>{createdBy}</UserName>
        <UserSchool>한국대학교 디자인학과</UserSchool>
      </UserWrapper>
      <Content>{content}</Content>
      <ButtonWrapper>
        <LikesWrapper>
          <img src={ImageSrc.THUMBS_UP} alt="좋아요" />
          {likes}개
          <img src={ImageSrc.THUMBS_DOWN} alt="싫어요" />
          {dislikes}개
        </LikesWrapper>
        <DeleteButton />
      </ButtonWrapper>
    </Commentwrapper>
  );
};

export default Comment;
