import React from "react";
import styled from "styled-components";

import { SharedPurpleWrapper } from "../../util/SharedStyles";
import RadarChart from "../../util/Chart";

const ChartWrapper = styled(SharedPurpleWrapper)``;

const Chart = ({ data }) => {
  return (
    <ChartWrapper>
      <RadarChart
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
    </ChartWrapper>
  );
};

export default Chart;
