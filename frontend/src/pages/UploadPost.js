import React, { useState, useEffect } from "react";
import styled from "styled-components";

import { AppWrapper } from "../util/SharedStyles";
import Head from "../util/sharedComponents/Head";
import ImageSrc from "../ImageSrc";

const UploadPostWrapper = styled(AppWrapper)``;

const PostWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const TitleWrapper = styled.div`
  margin-bottom: 0.25rem;
`;

const TextWrapper = styled(TitleWrapper)``;

const PostTitle = styled.input`
  width: 100%;
  box-sizing: border-box;
  padding: 0.5rem;
  font-weight: 400;
`;

const PostText = styled.textarea`
  resize: none;
  width: 100%;
  box-sizing: border-box;
  height: 300px;
  padding: 0.5rem;
`;

const Container = styled.div`
  width: 100%;
  margin-bottom: 1rem;
`;

const PointCheckbox = styled.div`
  width: 100%;
  height: 2rem;
  border: 1px solid #000;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
`;

const PointInputWrapper = styled.div`
  width: 100%;
  display: flex;
  box-sizing: border-box;
  border: 1px solid #000;
`;

const PointInput = styled.input`
  width: 100%;
  height: 2rem;
  box-sizing: border-box;
  border: none;
  padding: 0.5rem;
  :focus {
    outline: 0;
  }
`;

const UploadButton = styled.button`
  width: 100%;
  height: 3rem;
  background-color: #d8bfd8;
  margin: auto;
  border-radius: 5px;
  border: none;
  opacity: ${(props) => (props.clickable ? "1" : "0.7")};
`;

const UploadPost = ({ match }) => {
  const { type } = match.params;
  const [postTitle, setPostTitle] = useState("");
  const [postText, setPostText] = useState("");
  const [point, setPoint] = useState(false);
  const [pointValue, setPointValue] = useState(0);
  const [clickable, setClickable] = useState(postTitle && postText);

  // console.log(postTitle);
  // console.log(JSON.stringify(postText));

  const tryUploading = () => {
    console.log(postTitle, postText);
  };

  const handlePointValue = (value) => {
    setPointValue(value);
  };

  useEffect(() => {
    setClickable(postTitle && postText);
  }, [postTitle, postText]);

  return (
    <UploadPostWrapper>
      <Head
        text={type === "named" ? "자기 이름으로 업로드" : "익명으로 업로드"}
        type={type}
      />
      <PostWrapper>
        <Container>
          <TitleWrapper>제목*</TitleWrapper>
          <PostTitle
            type="number"
            value={postTitle}
            onChange={(event) => setPostTitle(event.target.value)}
          />
        </Container>
        <Container>
          <TextWrapper>질문 내용*</TextWrapper>
          <PostText
            value={postText}
            onChange={(event) => setPostText(event.target.value)}
          />
        </Container>
        <Container>
          {point ? (
            <PointInputWrapper>
              <PointInput
                type="text"
                value={pointValue === 0 ? "" : pointValue}
                autoFocus={point}
                onChange={(e) => {
                  handlePointValue(e.target.value);
                }}
              />
              <img
                src={ImageSrc.CANCEL}
                alt="cancel"
                onClick={() => setPoint(false)}
              />
            </PointInputWrapper>
          ) : (
            <PointCheckbox true={point} onClick={() => setPoint(true)}>
              <div>포인트 걸기</div>
            </PointCheckbox>
          )}
        </Container>
        <UploadButton clickable={clickable}>업로드</UploadButton>
      </PostWrapper>
    </UploadPostWrapper>
  );
};

export default UploadPost;
