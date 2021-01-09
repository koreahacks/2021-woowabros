import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

const UserHelpWrapper = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  & > a {
    text-decoration: none;
  }
  & > a::link {
    text-decoration: none;
  }
  & > a::visited {
    text-decoration: none;
  }
  padding: 0 2rem;
`;
const Text = styled.p`
  font-size: 1.25rem;
  color: ${(props) => props.color};
`;
const HorizontalBar = styled.div`
  width: 1px;
  height: 1.25rem;
  background-color: #c0c0c0;
`;
const UserHelp = () => {
  return (
    <UserHelpWrapper>
      <Link to="/sign-up">
        <Text color="#bfc6e2">회원가입</Text>
      </Link>
      <HorizontalBar />
      <Link to="#">
        <Text color="#cfdac7">계정 찾기</Text>
      </Link>
    </UserHelpWrapper>
  );
};

export default UserHelp;
