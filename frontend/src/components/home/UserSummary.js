import React from "react";
import styled from "styled-components";

import { SharedPurpleWrapper } from "../../util/SharedStyles";
import RadarChart from "../../util/Chart";

const UserSummaryWrapper = styled(SharedPurpleWrapper)`
  justify-content: space-between;
`;

const Chart = styled(RadarChart)`
  width: 8rem;
  height: 8rem;
  background-color: #fff;
`;

const UserInfoWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const Univ = styled.div`
  font-size: 1.5rem;
`;

const Major = styled.div`
  font-size: 1.25rem;
`;

const Name = styled.div`
  font-size: 1rem;
`;

const Percentile = styled.div`
  font-size: 0.75rem;
`;

const ShowMyInfo = styled(Percentile)`
  margin-top: 0.5rem;
  color: #800000;
`;

const UserSummary = ({ data, functions }) => {
  const { userSummary } = data;

  return (
    <UserSummaryWrapper>
      <Chart
        data={{
          size: 128,
          data: data.chart.data,
          captions: data.chart.captions,
          options: {
            captionProps: () => ({
              textAnchor: "middle",
              fontSize: 1,
            }),
          },
        }}
      />
      <UserInfoWrapper>
        <Univ>{userSummary.school}</Univ>
        <Major>{userSummary.major}</Major>
        <Name>{userSummary.name}</Name>
        <Percentile>상위 {userSummary.rank}%</Percentile>
        <ShowMyInfo>내 정보 보기</ShowMyInfo>
      </UserInfoWrapper>
    </UserSummaryWrapper>
  );
};

export default UserSummary;
