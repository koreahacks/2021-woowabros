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
      <UserSummary data={{ userSummary }} />
      <Post />
      <PostSummary data={{ title: "실명 게시판", summary: namedPostSummary }} />
      <PostSummary
        data={{ title: "익명 게시판", summary: anonymousPostSummary }}
      />
    </AppWrapper>
  );
};

export default Home;
