import React from "react";
import styled from "styled-components";

import { SharedPurpleWrapper } from "../../util/SharedStyles";

const UserSummaryWrapper = styled(SharedPurpleWrapper)`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  height: 7.5rem;
`;

const UnivMajorWrapper = styled.div`
  display: flex;
  align-items: flex-end;
`;

const Univ = styled.div`
  margin-right: 0.5rem;
  font-size: 1.5rem;
`;

const Major = styled.div``;

const Name = styled.div``;

const Percentile = styled.div``;

const UserSummary = ({ data }) => {
  const { userSummary } = data;
  return (
    <UserSummaryWrapper>
      <UnivMajorWrapper>
        <Univ>{userSummary.school}</Univ>
        <Major>{userSummary.major}</Major>
      </UnivMajorWrapper>
      <Name>{userSummary.name}</Name>
      <Percentile>상위 {userSummary.rank}%</Percentile>
    </UserSummaryWrapper>
  );
};

export default UserSummary;
