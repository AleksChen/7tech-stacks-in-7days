// @ts-nocheck

import { apiGetList } from "@/apis";
import { useRequest } from "ahooks";
import Link from "next/link";

const ListPage = () => {
  const { data = {} } = useRequest(apiGetList);
  const { productList = [] } = data;
  return (
    <div className="h-fit overflow-y-scroll pt-5 px-5 pb-20">
      {productList.map((product) => (
        <Link href="/detail" key={product.spuId}>
          <div className="px-2 py-4 flex items-center space-x-4 bg-white mb-4  rounded-lg">
            <img
              className="w-20 h-20 rounded-full"
              src={product.coverImage.resourceUrl}
            ></img>
            <div className="ml-8 w-64">
              <p className="text-lg font-bold">{product.name}</p>
              <p className="text-sm font-light mb-1">{product.introduction}</p>
              <p className="text-zinc-500">{"¥" + product.salePrice}</p>
            </div>
          </div>
        </Link>
      ))}
    </div>
  );
};

export default ListPage;
