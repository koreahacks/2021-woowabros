import axios from "axios";

const client = axios.create({
  baseURL: "https://uniconn.me/api",
});

export const login = (loginRequest) =>
  client.post("/members/login", loginRequest);

export const signUp = (signUpRequest) =>
  client.post("/members/join", signUpRequest);

export const retrieveSelf = () =>
  client.get("/members", {
    headers: {
      Authorization: "test-token",
    },
  });

export const retrieveById = (id) =>
  client.get(`/members/${id}`, {
    headers: {
      Authorization: "test-token",
    },
  });

export const retrieveInfoSelf = () =>
  client.get("/members/info", {
    headers: {
      Authorization: "test-token",
    },
  });

export const retrieveInfo = (id) =>
  client.get(`/members/info/${id}`, {
    headers: {
      Authorization: "test-token",
    },
  });

export const deleteMember = () =>
  client.delete("/members", {
    headers: {
      Authorization: "test-token",
    },
  });

export const findAllQuestions = () =>
  client.get("/questions", {
    headers: {
      Authorization: "test-token",
    },
  });

export const findQuestionById = (id) =>
  client.get(`/questions/${id}`, {
    headers: {
      Authorization: "test-token",
    },
  });

export const createQuestion = (createQuestionRequest) =>
  client.post("/questions", createQuestionRequest, {
    headers: {
      Authorization: "test-token",
    },
  });

export const deleteQuestionById = (id) =>
  client.delete(`/questions/${id}`, {
    headers: {
      Authorization: "test-token",
    },
  });

export const createAnswer = (createAnswerRequest) =>
  client.post("/answers", createAnswerRequest, {
    headers: {
      Authorization: "test-token",
    },
  });

export const findAnswerById = (id) =>
  client.get(`/answers/${id}`, {
    headers: {
      Authorization: "test-token",
    },
  });

export const doReaction = (id, reactionType) =>
  client.post(`/answers/${id}`, reactionType, {
    headers: {
      Authorization: "test-token",
    },
  });

export const selectAnswer = (id) =>
  client.post(`/answers/${id}`, {
    headers: {
      Authorization: "test-token",
    },
  });

export const deleteAnswer = (id) =>
  client.delete(`/answers/${id}`, {
    headers: {
      Authorization: "test-token",
    },
  });
