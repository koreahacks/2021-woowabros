import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import Title from "../components/login/Title";
import LoginInputs from "../components/login/LoginInputs";
import ImageSrc from "../ImageSrc";
import { loginTokenState } from "../store";
import UserHelp from "../components/login/UserHelp";

const LoginContent = styled.div`
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
const LoginWrapper = styled.div`
  padding: 0 1rem;
`;

const Login = () => {
  const [inputs, setInputs] = useState({
    email: localStorage.getItem("email") ? localStorage.getItem("email") : "",
    password: "",
  });
  const { email, password } = inputs;
  const handleChange = (event) => {
    const { value, name } = event.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  };

  const [remember, setRemember] = useState(false);
  const handleRememberClick = () => {
    setRemember(!remember);
  };

  const [loginToken, setLoginToken] = useRecoilState(loginTokenState);
  const history = useHistory();
  const handleButtonClick = () => {
    // TODO: link with api
    setLoginToken("hiImGroot");
    localStorage.setItem("loginToken", loginToken);
    if (remember) {
      localStorage.setItem("email", email);
    }
    if (!remember) {
      localStorage.setItem("email", "");
    }
    history.push("/");
  };

  return (
    <LoginContent>
      <UniconnWrapper>
        <UniconnIcon src={ImageSrc.UNICORN} />
      </UniconnWrapper>
      <LoginWrapper>
        <Title />
        <LoginInputs
          handleChange={handleChange}
          email={email}
          password={password}
          remember={remember}
          handleRememberClick={handleRememberClick}
          handleButtonClick={handleButtonClick}
        />
        <UserHelp />
      </LoginWrapper>
    </LoginContent>
  );
};

export default Login;
