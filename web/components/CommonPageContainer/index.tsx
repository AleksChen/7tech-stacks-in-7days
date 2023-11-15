import NavBar from "../NavBar";
import "../../app/globals.css";

interface CommonPageContainerProps {
  title?: string;
  showNavbar?: boolean;
  children: React.ReactNode;
}
const CommonPageContainer: React.FC<CommonPageContainerProps> = ({
  title,
  children,
  showNavbar = true,
}) => {
  return (
    <div className="relative w-screen h-screen bg-grey-900 pt-12">
      {showNavbar && <NavBar title={title} />}
      {children}
    </div>
  );
};

export default CommonPageContainer;
