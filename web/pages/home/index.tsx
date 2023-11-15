import { Tab } from "@/components/TabBar";
import ListPage from "./components/list";
import MePage from "./components/me";
import MainPageContainer from "@/components/MainPageContainer";

const TABS = [
  { key: Tab.CATEGORY, children: <ListPage /> },
  { key: Tab.ME, children: <MePage /> },
];

const HomePage = () => {
  return <MainPageContainer tabConfig={TABS}></MainPageContainer>;
};

export default HomePage;
