import React from "react";
import styled from "styled-components";

import { SharedPurpleWrapper } from "../../util/SharedStyles";

const PostSummaryWrapper = styled(SharedPurpleWrapper)`
  display: block;
`;

const Title = styled.div`
  color: #aa8fbf;
`;

const Header = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const More = styled.div`
  font-size: 0.5rem;
`;

const SummaryWrapper = styled.div`
  padding-left: 1rem;
`;

const Summary = styled.div`
  color: #392f31;
  font-size: 0.75rem;
  margin-top: 0.75rem;
`;

const PostSummary = ({ data, functions }) => {
  return (
    <PostSummaryWrapper>
      <Header>
        <Title>{data.title}</Title>
        <More>{"더보기 >"}</More>
      </Header>
      <SummaryWrapper>
        {data.summary.map(({ summary, id }, key) => (
          <Summary key={key} id={id}>
            {summary}
          </Summary>
        ))}
      </SummaryWrapper>
    </PostSummaryWrapper>
  );
};

export default PostSummary;
