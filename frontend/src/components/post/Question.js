import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import SelectedBadge from "./SelectedBadge";
import UnselectedBadge from "./UnselectedBadge";
const QuestionWrapper = styled.div`
  width: 100%;
  padding: 0.25rem 0;
  border-bottom: solid 1px #eee;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  & > * {
  text-decoration: none;
`;
const TopLine = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem 0.5rem 0 0.5rem;
  height: 1.25rem;
  margin-top: ;
`;
const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
`;
const Title = styled.p`
  font-size: 0.815rem;
  font-weight: 600;
  color: #333333;
  margin: 0;
`;

const Writer = styled.p`
  color: #392f31;
  margin: 0;
  font-size: 0.6rem;
`;
const UnderLine = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 1rem;
  padding: 0.25rem 0.5rem 0.25rem;
`;
const Price = styled.p`
  font-size: 0.6rem;
  margin: 0;
  color: #392f31;
`;
const Date = styled.p`
  font-size: 0.5rem;
  margin: 0;
  color: #392f31;
`;
const Question = ({
  id,
  title,
  createdBy,
  selected,
  price,
  createdAt,
  type,
}) => {
  return (
    <QuestionWrapper key={id}>
      <Link to={"/posts/" + type + "/" + id}>
        <TopLine>
          <TitleWrapper>
            <Title>{title}</Title>
            {selected ? <SelectedBadge /> : <UnselectedBadge />}
          </TitleWrapper>
          <Writer>{createdBy}</Writer>
        </TopLine>
        <UnderLine>
          <Price>{price}원</Price>
          <Date>{createdAt}</Date>
        </UnderLine>
      </Link>
    </QuestionWrapper>
  );
};

export default Question;
