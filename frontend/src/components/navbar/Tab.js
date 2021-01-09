import React from "react";
import styled from "styled-components";

const clickedColor = "#800000";
const unclickedColor = "red";

const TabWrapper = styled.div`
  width: 25%;
`;

const TabImg = styled.svg`
  // fill: ${(props) => (props.present ? clickedColor : unclickedColor)};
`;

const TabName = styled.div``;

const Tab = ({ data }) => {
  console.log(window.location);
  console.log(data.url, data.url === window.location.pathname);
  return (
    <TabWrapper onClick={() => {}}>
      <TabImg src={data.src} present={data.url === window.location.pathname} />
      <TabName>{data.name}</TabName>
    </TabWrapper>
  );
};

export default Tab;
