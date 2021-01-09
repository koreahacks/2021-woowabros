import React from "react";
import styled from "styled-components";
import ImageSrc from "../../ImageSrc";

const Input = styled.input`
  width: 17rem;
  height: 2.5rem;
  margin: 0.375rem 0;
  border: 0;
  outline: 0;
  :focus {
    outline: none;
  }
`;

const RememberWrapper = styled.div`
  display: flex;
  padding-left: 0.25rem;
  align-items: center;
`;
const CheckBoxWrapper = styled.div`
  width: 1.25rem;
  height: 1.25rem;
`;
const CheckBox = styled.img`
  width: 100%;
  height: 100%;
`;
const RememberText = styled.p`
    color: #C0C0C0;
    font-size: .75rem;
    margin-left: .75rem;s
`;
const ButtonWrapper = styled.div`
  width: 17rem;
  height: 2.5rem;
  margin: 0.375rem 0;
`;
const Button = styled.button`
  width: 100%;
  height: 100%;
  font-family: BMJUA;
  background: ${(props) => props.color};
  color: ${(props) => props.textColor};
  font-size: ${(props) => props.fontSize};
  border: 0;
  outline: 0;
`;

const LoginInputs = ({
  handleChange,
  email,
  password,
  remember,
  handleRememberClick,
}) => {
  return (
    <>
      <Input
        type="text"
        name="email"
        value={email}
        onChange={handleChange}
        placeholder="이메일"
      />
      <Input
        type="password"
        name="password"
        value={password}
        onChange={handleChange}
        placeholder="비밀번호"
      />
      <RememberWrapper>
        <CheckBoxWrapper onClick={handleRememberClick}>
          {remember ? (
            <CheckBox src={ImageSrc.CHECKBOX_FILL} alt={"기억하기"} />
          ) : (
            <CheckBox src={ImageSrc.CHECKBOX} alt={"기억하기"} />
          )}
        </CheckBoxWrapper>
        <RememberText>로그인 상태 유지</RememberText>
      </RememberWrapper>
      <ButtonWrapper>
        <Button color="#D2C4DD" textColor="#392F31" fontSize="1.5rem">
          로그인
        </Button>
      </ButtonWrapper>
    </>
  );
};

export default LoginInputs;
