import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";
import { useRecoilValue } from "recoil";

import HomePage from "./pages/Home";
import LoginPage from "./pages/Login";
import SignupPage from "./pages/Signup";
import UserPage from "./pages/User";
import PostPage from "./pages/Post";
import RankingPage from "./pages/Ranking";
import UserPostPage from "./pages/UserPost";
import { loginTokenState } from "./store";

const routes = [
  {
    path: "/",
    page: HomePage,
    exact: true,
    name: "홈",
  },
  {
    path: "/sign-in",
    page: LoginPage,
    exact: true,
    name: "로그인",
  },
  {
    path: "/sign-up",
    page: SignupPage,
    exact: true,
    name: "회원가입",
  },
  {
    path: "/users/:userId",
    page: UserPage,
    exact: true,
    name: "유저페이지",
  },
  {
    path: "/users/:userId/posts",
    page: UserPostPage,
    exact: true,
    name: "유저포스트",
  },
  {
    path: "/posts/:type",
    page: PostPage,
    exact: true,
    name: "게시판",
  },

  {
    path: "/ranking?type=:type",
    page: RankingPage,
    exact: true,
    name: "랭킹",
  },
];

const SwitchRoutes = () => {
  const loginToken = useRecoilValue(loginTokenState);
  return (
    <>
      <Switch>
        {routes.map(({ path, page, exact }, key) => (
          <Route path={path} key={key} component={page} exact={exact} />
        ))}
      </Switch>
      {loginToken === "" ? <Redirect to="/sign-in" /> : <></>}
    </>
  );
};

export default SwitchRoutes;
