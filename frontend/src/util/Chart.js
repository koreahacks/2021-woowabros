import React from "react";
import RadarChart from "react-svg-radar-chart";
import "react-svg-radar-chart/build/css/index.css";

const Chart = ({ data }) => {
  return (
    <RadarChart
      size={data.size}
      options={data.options}
      captions={data.captions}
      data={data.data}
    />
  );
};

export default Chart;
