import { useRouter } from "next/navigation";

interface NavBarProps {
  title?: string;
  canBack?: boolean;
}

const NavBar: React.FC<NavBarProps> = ({ title, canBack = true }) => {
  const router = useRouter();
  return (
    <nav className="fixed inset-x-0 top-0 h-12 mx-auto flex border-b-2 border-zinc-200 bg-white z-10">
      {canBack && (
        <div
          className="absolute left-4 top-1/2 -translate-y-1/2"
          onClick={() => router.back()}
        >
          <div className="w-8 h-8 bg-gray-200 rounded-full flex items-center justify-center cursor-pointer">
            后退
          </div>
        </div>
      )}
      <div className="flex-1 flex items-center justify-center text-2xl ">
        {title}
      </div>
    </nav>
  );
};

export default NavBar;
