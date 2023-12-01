import Link from "next/link";
import { usePathname } from "next/navigation";

export enum Tab {
  CATEGORY = 1,
  ME = 2,
}

const TabBar: React.FC = () => {
  const pathname = usePathname();
  if (pathname.includes("/detail")) return null;
  return (
    <div className="mx-auto flex border-t-2 border-zinc-200 fixed inset-x-0 bottom-0 bg-white shadow-lg">
      <Link
        href="/"
        scroll={false}
        className={`text-2xl flex items-center justify-center h-16 flex-1    border-zinc-400  ${
          pathname === "/" ? "text-white bg-lime-700" : "text-zinc-400"
        }`}
        replace
      >
        点单
      </Link>
      <Link
        href="/me"
        className={`text-2xl flex items-center justify-center h-16 flex-1    border-zinc-400 ${
          pathname === "/me" ? "text-white bg-lime-700" : "text-zinc-400"
        }`}
        replace
      >
        我的
      </Link>
    </div>
  );
};

export default TabBar;
