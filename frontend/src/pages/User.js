import React, { useState } from "react";

import Head from "../util/sharedComponents/Head";
import Chart from "../components/user/Chart";
import PostSummary from "../components/user/PostSummary";
import UserSummary from "../components/user/UserSummary";
import { AppWrapper } from "../util/SharedStyles";
import DropButton from "../components/user/DropButton";

const User = () => {
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
    battery: "컴퓨터",
    design: "화학",
    useful: "물리",
    speed: "경제",
    weight: "경영",
  });

  const [userPost, setUserPost] = useState([
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

  const [userAnswer, setUserAnswer] = useState([
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

  const tryDrop = () => {};

  return (
    <AppWrapper>
      <Head text="내 정보 보기" />
      <Chart
        data={{
          chart: { data: chartData, captions: captions },
        }}
      />
      <UserSummary
        data={{
          userSummary: userSummary,
        }}
      />
      <PostSummary data={{ title: "내가 올린 질문", summary: userPost }} />
      <PostSummary data={{ title: "내가 올린 답변", summary: userAnswer }} />
      <DropButton functions={{ tryDrop: tryDrop }} />
    </AppWrapper>
  );
};

export default User;
