import React from "react";
import styled from "styled-components";
import BackButton from "../components/post/BackButton";
import { AppWrapper } from "../util/SharedStyles";
import DeleteButton from "../components/post/DeleteButton";
import Comment from "../components/post/Comment";

const QuestionWrapper = styled(AppWrapper)`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
const Head = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 1rem;
  margin-bottom: 0.75rem;
  width: calc(100% - 1rem);
  height: 1.625rem;
`;
const Body = styled.div`
  padding: 0.5rem 1rem;
  border-bottom: solid 1px black;
`;
const TitleWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 0.5rem;
`;
const Title = styled.p`
  margin: 0;
  font-size: 1.25rem;
`;
const CreatedBy = styled.p`
  margin: 0;
  font-size: 1rem;
`;
const Content = styled.pre`
  padding: 0 1rem;
  white-space: pre-wrap;
`;
const CreatedAtWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const CommentNumber = styled.p``;
const Date = styled.p``;
const Comments = styled.div`
  padding: 0 1rem;
  width: calc(100% - 1rem);
`;
const Question = ({ match }) => {
  const { type, id } = match.params;
  const MockData = {
    id: id,
    title: "안녕하세요",
    content:
      "근로자는 근로조건의 향상을 위하여 자주적인 단결권·단체교섭권 및 단체행동권을 가진다. 헌법재판소 재판관은 탄핵 또는 금고 이상의 형의 선고에 의하지 아니하고는 파면되지 아니한다. 재산권의 행사는 공공복리에 적합하도록 하여야 한다. 농업생산성의 제고와 농지의 합리적인 이용을 위하거나 불가피한 사정으로 발생하는 농지의 임대차와 위탁경영은 법률이 정하는 바에 의하여 인정된다.\n" +
      "\n" +
      "법률은 특별한 규정이 없는 한 공포한 날로부터 20일을 경과함으로써 효력을 발생한다. 국가는 전통문화의 계승·발전과 민족문화의 창달에 노력하여야 한다. 국회는 헌법 또는 법률에 특별한 규정이 없는 한 재적의원 과반수의 출석과 출석의원 과반수의 찬성으로 의결한다. 가부동수인 때에는 부결된 것으로 본다.\n" +
      "\n" +
      "모든 국민은 법률이 정하는 바에 의하여 공무담임권을 가진다. 모든 국민은 건강하고 쾌적한 환경에서 생활할 권리를 가지며, 국가와 국민은 환경보전을 위하여 노력하여야 한다. 제3항의 승인을 얻지 못한 때에는 그 처분 또는 명령은 그때부터 효력을 상실한다. 이 경우 그 명령에 의하여 개정 또는 폐지되었던 법률은 그 명령이 승인을 얻지 못한 때부터 당연히 효력을 회복한다.\n" +
      "\n" +
      "군인은 현역을 면한 후가 아니면 국무총리로 임명될 수 없다. 모든 국민은 소급입법에 의하여 참정권의 제한을 받거나 재산권을 박탈당하지 아니한다. 국무총리는 대통령을 보좌하며, 행정에 관하여 대통령의 명을 받아 행정각부를 통할한다. 모든 국민은 자기의 행위가 아닌 친족의 행위로 인하여 불이익한 처우를 받지 아니한다.",
    price: 3000,
    selected: true,
    createdAt: "2020-01-01",
    createdBy: {
      name: "김경",
    },
    comments: [
      {
        id: 1,
        content: "무슨말인지 잘 모르겠어요",
        createdAt: "2020-01-01",
        createdBy: {
          name: "김호돌",
        },
        likes: 10,
        dislikes: 2,
      },
    ],
  };
  return (
    <QuestionWrapper>
      <Head>
        <BackButton type={type === "anonymous" ? "익명" : "실명"} />
        <DeleteButton />
      </Head>
      <Body>
        <TitleWrapper>
          <Title>{MockData.title}</Title>
          <CreatedBy>{MockData.createdBy.name}</CreatedBy>
        </TitleWrapper>
        <Content>{MockData.content}</Content>
        <CreatedAtWrapper>
          <CommentNumber>답변 {MockData.comments.length}개</CommentNumber>
          <Date>{MockData.createdAt}</Date>
        </CreatedAtWrapper>
      </Body>
      <Comments>
        {MockData.comments.map((comment) => (
          <Comment
            content={comment.content}
            createdBy={comment.createdBy.name}
            likes={comment.likes}
            dislikes={comment.dislikes}
          />
        ))}
      </Comments>
    </QuestionWrapper>
  );
};

export default Question;
