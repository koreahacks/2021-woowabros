import { useSetRecoilState } from "recoil";
import { authState } from "./index";

const AuthStore = ({ children }) => {
  const loginToken = localStorage.getItem("loginToken");
  const setAuth = useSetRecoilState(authState);
  // LINK api - token보내면 userId 반환하여 authState에 loginToken과 userId 저장

  return <>{children}</>;
};

export default AuthStore;
