const MePage = () => {
  return (
    <div className="pb-12">
      <div className="h-64 flex items-center justify-center bg-lime-700 rounded">
        <div className="text-center">
          <img
            className="w-24 h-24 rounded-full mx-auto bg-white"
            src="/static/avator.png"
          />
          <h2 className="mt-4 text-2xl font-semibold text-white">
            这是一个昵称
          </h2>
        </div>
      </div>
      <div className="w-full mt-0 flex flex-wrap justify-around p-4">
        {Array.from({ length: 8 }).map((_, index) => (
          <div key={index} className="w-1/2">
            <div className="h-32 flex items-center justify-center p-4 border-8 rounded text-white text-xl bg-red-300">
              配置入口{index}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MePage;
