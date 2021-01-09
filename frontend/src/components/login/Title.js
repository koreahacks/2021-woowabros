import React from "react";
import styled from "styled-components";

const TitleText = styled.p`
  font-size: 2rem;
  color: #392f31;
  margin: 0.625rem 0;
`;

const Title = () => {
  return (
    <>
      <TitleText>전국에서 하는 대학생활</TitleText>
      <TitleText>Uniconn</TitleText>
    </>
  );
};

export default Title;
