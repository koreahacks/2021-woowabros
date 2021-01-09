import React from "react";
import styled from "styled-components";
import ImageSrc from "../../ImageSrc";
import Tab from "./Tab";

const NavigationWrapper = styled.div`
  position: fixed;
  left: 0;
  bottom: 0;
  background-color: #fff;
  box-shadow: 0 3px 9px 0 rgba(0, 0, 0, 0.15);
  height: 50px;
  width: 100%;
  display: flex;
`;

const NavigationBar = () => {
  return (
    <NavigationWrapper>
      <Tab
        data={{
          src: ImageSrc.HOME,
          name: "홈",
          url: "/",
        }}
      />
      <Tab
        data={{
          src: ImageSrc.NAMED,
          name: "실명게시판",
          url: "/posts/named",
        }}
      />
      <Tab
        data={{
          src: ImageSrc.ANONYMOUS,
          name: "익명게시판",
          url: "/posts/anonymous",
        }}
      />
      <Tab
        data={{
          src: ImageSrc.ACCOUNT,
          name: "내 정보",
          url: `/users/`,
        }}
      />
    </NavigationWrapper>
  );
};

export default NavigationBar;
