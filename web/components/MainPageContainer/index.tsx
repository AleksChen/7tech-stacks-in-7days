import { useCallback, useState } from "react";
import TabBar, { Tab } from "../TabBar";

interface PageContainerProps {
  tabConfig: { key: Tab; children: React.ReactNode }[];
}
const MainPageContainer: React.FC<PageContainerProps> = ({ tabConfig }) => {
  const [currentTab, setCurrentTab] = useState(Tab.CATEGORY);

  const toggleTab = useCallback((tab: Tab) => {
    setCurrentTab(tab);
  }, []);

  return (
    <div className="w-screen h-screen bg-white-800">
      {tabConfig.map((item) => (
        <div
          key={item.key}
          className={
            item.key === currentTab ? "h-full overflow-y-scroll" : "hidden"
          }
        >
          {item.children}
        </div>
      ))}
      <TabBar value={currentTab} onChange={toggleTab} />
    </div>
  );
};

export default MainPageContainer;
