import React, { useState } from "react";
import styled from "styled-components";
import Question from "../components/post/Question";
import Head from "../components/post/Head";
import Search from "../components/post/Search";
import {AppWrapper} from "../util/SharedStyles";

const PostWrapper = styled(AppWrapper)`
display: flex;
flex-direction: column;
align-items: center;
`

const QuestionWrapper = styled.div`
  width: calc(100% - 1rem);
`;
const Post = ({ match }) => {
  const { type } = match.params;
  const question = {
    questions: [
      {
        id: "1",
        title: "안뇽하세요",
        price: 30000,
        selected: null,
        createdAt: "2020-01-01",
        createdBy: {
          name: "꿀벌",
        },
      },
      {
        id: "2",
        title: "안뇽하세요",
        price: 30000,
        selected: 7,
        createdAt: "2020-01-01",
        createdBy: {
          name: "꿀벌",
        },
      },
      {
        id: "3",
        title: "안뇽하세요",
        price: 30000,
        selected: null,
        createdAt: "2020-01-01",
        createdBy: {
          name: "꿀벌",
        },
      },
      {
        id: "4",
        title: "안뇽하세요",
        price: 30000,
        selected: null,
        createdAt: "2020-01-01",
        createdBy: {
          name: "꿀벌",
        },
      },
      {
        id: "5",
        title: "안뇽하세요",
        price: 30000,
        selected: null,
        createdAt: "2020-01-01",
        createdBy: {
          name: "꿀벌",
        },
      },
      {
        id: "6",
        title: "안뇽하세요",
        price: 30000,
        selected: null,
        createdAt: "2020-01-01",
        createdBy: {
          name: "꿀벌",
        },
      },
      {
        id: "7",
        title: "안뇽하세요",
        price: 30000,
        selected: null,
        createdAt: "2020-01-01",
        createdBy: {
          name: "꿀벌",
        },
      },
    ],
  };

  const [search, setSearch] = useState("");
  const handleSearchChange = (event) => {
    const { value } = event.target;
    setSearch(value);
  };
  const handleEnterKeyPress = (event) => {
    if (event.key === "Enter") {
      setSearch("");
    }
  };
  const handleSearchIconClick = () => {
    setSearch("");
  };
  return (
    <PostWrapper>
      <Head type={type === "anonymous" ? "익명" : "실명"} />
      <Search
        search={search}
        handleSearchChange={handleSearchChange}
        handleEnterKeyPress={handleEnterKeyPress}
        handleSearchIconClick={handleSearchIconClick}
      />
      <QuestionWrapper>
        {question.questions.map((question) => {
          return (
            <Question
              id={question.id}
              title={question.title}
              createdBy={question.createdBy.name}
              selected={question.selected}
              price={question.price}
              createdAt={question.createdAt}
              type={type}
            />
          );
        })}
      </QuestionWrapper>
    </PostWrapper>
  );
};

export default Post;
