import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import Title from "../components/login/Title";
import LoginInputs from "../components/login/LoginInputs";
import ImageSrc from "../ImageSrc";
import UserHelp from "../components/login/UserHelp";
import { login } from "../api";
import { authState } from "../store/index";

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

  const [auth, setAuth] = useRecoilState(authState);
  const history = useHistory();
  const handleButtonClick = async () => {
    const loginRequest = {
      email,
      password,
    };
    login(loginRequest)
      .then((response) => {
        setAuth({ ...auth, loginToken: response.data.accessToken });
        localStorage.setItem("loginToken", auth.loginToken);
        if (remember) {
          localStorage.setItem("email", email);
        }
        if (!remember) {
          localStorage.setItem("email", "");
        }
        history.push("/");
      })
      .catch((e) => {
        console.log(e.message);
      });
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
