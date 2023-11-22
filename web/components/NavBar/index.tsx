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
            <svg
              className="w-6 h-6"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M15 19l-7-7 7-7"
              ></path>
            </svg>
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
