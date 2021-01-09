import { atom } from "recoil";

export const authState = atom({
  key: "auth",
  default: {
    userId: null,
    loginToken: null,
  },
});
