import React, { useState } from "react";
import styled from "styled-components";
import InputsWrapper from "../components/sign-in/InputsWrapper";
import ImageSrc from "../ImageSrc";
import { login, signUp } from "../api";

const SignUpWrapper = styled.div`
  width: 100%;
  height: 100%;
  background: #aa8fbf;
  display: flex;
  flex-direction: column;
  align-items: center;
`;
const UniconnWrapper = styled.div`
  width: 7.5rem;
  height: 7.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 4.5rem;
`;
const UniconnIcon = styled.img`
  width: 100%;
  height: 100%;
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
const Signup = () => {
  const [inputs, setInputs] = useState({
    email: "",
    password: "",
    confirmPassword: "",
    nickname: "",
    major: "COMPUTER_SCIENCE",
  });
  const { email, password, confirmPassword, nickname, major } = inputs;
  const handleInputChange = (event) => {
    const { value, name } = event.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  };
  const handleButtonClick = async () => {
    signUp(inputs).then((response) => console.log(response.data));
  };
    console.log(inputs);
  return (
    <SignUpWrapper>
      <UniconnWrapper>
        <UniconnIcon src={ImageSrc.UNICORN} />
      </UniconnWrapper>
      <InputsWrapper inputs={inputs} handleInputChange={handleInputChange} />
      <ButtonWrapper>
        <Button
          onClick={handleButtonClick}
          color="#D2C4DD"
          textColor="#392F31"
          fontSize="1.5rem"
        >
          회원가입
        </Button>
      </ButtonWrapper>
    </SignUpWrapper>
  );
};

export default Signup;
