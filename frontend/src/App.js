import { RecoilRoot } from "recoil";
import SwitchRoutes from "./Routes";
import NavigationBar from "./components/navbar/NavigationBar";

function App() {
  return (
    <RecoilRoot>
      <SwitchRoutes />
    </RecoilRoot>
  );
}

export default App;
