import React from 'react';
import styled from "styled-components";

const Badge = styled.p`
  margin-left: 0.2rem;
  padding: 0.25rem;
  border-radius: 5px;
  font-size: 0.75rem;
  font-weight: 100;
  background-color: #444;
  color: #ddd;
`;
const UnselectedBadge = () => {
    return (
        <Badge>
            미채택
        </Badge>
    );
};

export default UnselectedBadge;