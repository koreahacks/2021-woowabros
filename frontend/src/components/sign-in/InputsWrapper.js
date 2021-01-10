import React from "react";
import styled from "styled-components";

const Input = styled.input`
  width: 17rem;
  height: 2.5rem;
  margin: 0.375rem 0;
  outline: 0;
  :focus {
    outline: none;
  }
`;
const Title = styled.p`
margin: 0;
`;
const InputsWrapper = ({ inputs, handleInputChange }) => {
  const { email, password, confirmPassword, nickname, major } = inputs;
  return (
    <>
        <Title>이메일</Title>
      <Input
        type="text"
        name="email"
        value={email}
        onChange={handleInputChange}
        placeholer="학교 이메일을 입력해주세요."
      />
        <Title>비밀번호</Title>
      <Input
        type="password"
        name="password"
        value={password}
        onChange={handleInputChange}
        placeholer="비밀번호는 8글자 이상 16글자 이하입니다."
      />
        <Title>비밀번호 확인</Title>
      <Input
        type="password"
        name="confirmPassword"
        value={confirmPassword}
        onChange={handleInputChange}
        placeholer="비밀번호를 한번 더 입력해주세요."
      />
        <Title>닉네임</Title>
      <Input
        type="text"
        name="nickname"
        value={nickname}
        onChange={handleInputChange}
        placeholer="본인의 닉네임을 입력해주세요."
      />
        <Title>전공</Title>
      <select name="major" value={major} onChange={handleInputChange}>
        <option value="COMPUTER_SCIENCE">컴퓨터 공학과</option>
        <option value="BUSINESS">경영학과</option>
        <option value="MATH">수학과</option>
        <option value="PHYSICS">물리학과</option>
        <option value="CHEMISTRY">화학과</option>
        <option value="BIOLOGY">생물학과</option>
        <option value="STATISTICS">통계학과</option>
        <option value="MEDICAL">의학과</option>
        <option value="PHARMACY">약학과</option>
        <option value="INDUSTRY">산업과</option>
        <option value="ETC">기타</option>
      </select>
    </>
  );
};

export default InputsWrapper;
