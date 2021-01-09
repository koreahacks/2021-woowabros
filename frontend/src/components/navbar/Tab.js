import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { NavLink } from "react-router-dom";
import { useRecoilState } from "recoil";
import { authState } from "../../store";

const clickedColor = "#800000";
const unclickedColor = "red";

const TabWrapper = styled(NavLink)`
  width: 25%;
  height: 100%;
  margin: auto;
  text-decoration: none;
  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    text-decoration: none;
  }
`;

const TabContent = styled.div`
  width: 50%;
  height: 100%;
  margin: auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const TabImg = styled.img`
  // fill: ${(props) => (props.present ? clickedColor : unclickedColor)};
`;

const TabName = styled.div`
  font-size: 0.5rem;
  color: #000;
  text-align: center;
`;

const Tab = ({ data, functions }) => {
  const [userId, setUserId] = useState(null);

  useEffect(() => {
    setUserId(authState.userId);
  }, [authState.userId]);

  console.log(userId);

  return (
    <TabWrapper to={data.url}>
      <TabContent>
        <TabImg
          src={data.src}
          present={data.url === window.location.pathname}
        />
        <TabName>{data.name}</TabName>
      </TabContent>
    </TabWrapper>
  );
};

export default Tab;
