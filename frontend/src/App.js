import { RecoilRoot } from "recoil";
import SwitchRoutes from "./Routes";
import AuthStore from "./store/authStore";

function App() {
  return (
    <RecoilRoot>
      <AuthStore>
        <SwitchRoutes />
      </AuthStore>
    </RecoilRoot>
  );
}

export default App;
