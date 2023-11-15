import { apiGetDetail } from "@/apis";
import CommonPageContainer from "@/components/CommonPageContainer";

import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import React from "react";
import Slider from "react-slick";

const SimpleSlider = ({ imgs }: { imgs: string[] }) => {
  var settings = {
    dots: true,
    autoplay: true,
    arrows: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };
  return (
    <Slider {...settings}>
      {imgs.map((url, index) => (
        <img key={index} src={url} />
      ))}
    </Slider>
  );
};

const DetailPage = () => {
  const data = apiGetDetail();
  return (
    <CommonPageContainer title="详情页">
      <div className="overflow-scroll pb-2">
        <div className="bg-white pb-10">
          <SimpleSlider
            imgs={data.carouselImages.map((item) => item.resourceUrl)}
          />
        </div>

        <div className="my-2 p-4 bg-white">
          <p className="text-3xl font-bold">{data.name}</p>
          <p className="mt-2 text-2xl font-bold">{"¥" + data.salePrice}</p>
          <p className="mt-2 text-lg text-gray-600">{data.introduction}</p>
          <p className="mt-2 text-lg text-gray-600">
            {"产地: " + data.beansSource}
          </p>
          <p className="mt-2">
            <img className="h-8 w-8" src={data.beansTagImage.resourceUrl} />
          </p>
        </div>

        <div className="bg-white">
          <img className="w-full mb-1" src={data.coverImage.resourceUrl} />
          <div className="p-4">
            <p className="mt-2 text-lg text-gray-600">
              {data.detailedIntroductions.join("、")}
            </p>
          </div>
          <img className="w-full mb-1" src={data.coverImage.resourceUrl} />
          <div className="p-4">
            <p className="mt-2 text-lg text-gray-600">
              {data.detailedIntroductions.join("、")}
            </p>
          </div>
          <img className="w-full" src={data.coverImage.resourceUrl} />
        </div>
      </div>
    </CommonPageContainer>
  );
};

export default DetailPage;
