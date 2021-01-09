import React, { useState } from "react";

import Header from "../components/home/Header";
import UserSummary from "../components/home/UserSummary";
import { AppWrapper } from "../util/SharedStyles";
import Post from "../components/home/PostButtons";
import PostSummary from "../components/home/PostSummary";

const Home = () => {
  const [userSummary, setUserSummary] = useState({
    school: "한국대학교",
    major: "컴퓨터학과",
    name: "김경",
    id: 1,
    rank: 0.00001,
  });
  const [chartData, setChartData] = useState([
    {
      data: {
        battery: 0.7,
        design: 0.8,
        useful: 0.9,
        speed: 0.67,
        weight: 0.8,
      },
      meta: { color: "blue" },
    },
    {
      data: {
        battery: 0.6,
        design: 0.85,
        useful: 0.5,
        speed: 0.6,
        weight: 0.7,
      },
      meta: { color: "red" },
    },
  ]);
  const [captions, setCaptions] = useState({
    battery: "Battery Capacity",
    design: "Design",
    useful: "Usefulness",
    speed: "Speed",
    weight: "Weight",
  });

  const [namedPostSummary, setNamedPostSummary] = useState([
    {
      id: 1,
      summary: "아니 로렘입섬은 어떻게 쓰는거야!",
    },
    {
      id: 1,
      summary: "아니 로렘입섬은 어떻게 쓰는거야!",
    },
    {
      id: 1,
      summary: "아니 로렘입섬은 어떻게 쓰는거야!",
    },
  ]);

  const [anonymousPostSummary, setAnonymousPostSummary] = useState([
    {
      id: 1,
      summary: "아니 로렘입섬은 어떻게 쓰는거야!",
    },
    {
      id: 1,
      summary: "아니 로렘입섬은 어떻게 쓰는거야!",
    },
    {
      id: 1,
      summary: "아니 로렘입섬은 어떻게 쓰는거야!",
    },
  ]);

  return (
    <AppWrapper>
      <Header />
      <UserSummary
        data={{
          userSummary: userSummary,
          chart: { data: chartData, captions: captions },
        }}
      />
      <Post />
      <PostSummary data={{ title: "실명 게시판", summary: namedPostSummary }} />
      <PostSummary
        data={{ title: "익명 게시판", summary: anonymousPostSummary }}
      />
    </AppWrapper>
  );
};

export default Home;
