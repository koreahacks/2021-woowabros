import React from "react";
import styled from "styled-components";
import BackButton from "./BackButton";
import WriteButton from "./WriteButton";
import logo from "../../assets/unicorn.svg";
import {Link} from "react-router-dom";

const HeadWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 1rem;
  margin-bottom: 0.75rem;
  width: 100%; 
  height: 1.625rem;
  border-bottom: 1px solid #000;
  padding-bottom: 1rem;
  margin-left: -
`;

const Logo = styled.img`
    width: 2rem;
    padding: 0.2rem;
    border-radius: 100%;
    border: 1px solid #ddd;
`

const Head = () => {
  return (
    <>
      <HeadWrapper>
        <BackButton />
        <Link to="/">
          <Logo src={logo} alt="home"/>
        </Link>
        <WriteButton />
      </HeadWrapper>
    </>
  );
};

export default Head;
