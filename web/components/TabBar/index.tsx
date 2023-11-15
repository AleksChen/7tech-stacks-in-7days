export enum Tab {
  CATEGORY = 1,
  ME = 2,
}

const TAB_MAP = {
  [Tab.CATEGORY]: "点单",
  [Tab.ME]: "我的",
};

interface TabBarProps {
  value: Tab;
  onChange: (value: Tab) => void;
}

const TabBar: React.FC<TabBarProps> = ({ value, onChange }) => {
  return (
    <nav className="mx-auto flex border-t-2 border-zinc-200 fixed inset-x-0 bottom-0 bg-white shadow-lg">
      <div
        className={`text-2xl flex items-center justify-center h-16 flex-1    border-zinc-400  ${
          value === Tab.CATEGORY ? "text-white bg-lime-700" : "text-zinc-400"
        }`}
        onClick={() => onChange(Tab.CATEGORY)}
      >
        {TAB_MAP[Tab.CATEGORY]}
      </div>
      <div
        className={`text-2xl flex items-center justify-center h-16 flex-1    border-zinc-400 ${
          value === Tab.ME ? "text-white bg-lime-700" : "text-zinc-400"
        }`}
        onClick={() => onChange(Tab.ME)}
      >
        {TAB_MAP[Tab.ME]}
      </div>
    </nav>
  );
};

export default TabBar;
