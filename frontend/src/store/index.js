import { atom } from "recoil";

export const loginTokenState = atom({
  key: "loginToken",
  default: "",
});
