"use client";

import { apiGetDetail } from "@/apis";
import Image from "next/image";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import React from "react";
import Slider from "react-slick";
import dynamic from "next/dynamic";

const CommonPageContainer = dynamic(
  () => import("@/components/CommonPageContainer"),
  { ssr: false }
);

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

const DetailPage = ({ params }: { params: { id: string } }) => {
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
          <p className="mt-2 text-xl font-bold">{"ID" + params.id}</p>
          <p className="mt-2 text-2xl font-bold">{"¥" + data.salePrice}</p>
          <p className="mt-2 text-lg text-gray-600">{data.introduction}</p>
          <p className="mt-2 text-lg text-gray-600">
            {"产地: " + data.beansSource}
          </p>
          <p className="mt-2">
            <Image
              className="h-8 w-8"
              src={data.beansTagImage.resourceUrl}
              alt="img"
              width={100}
              height={100}
            />
          </p>
        </div>

        <div className="bg-white">
          <Image
            className="w-full mb-1"
            src={data.coverImage.resourceUrl}
            alt="img"
            width={100}
            height={100}
          />
          <div className="p-4">
            <p className="mt-2 text-lg text-gray-600">
              {data.detailedIntroductions.join("、")}
            </p>
          </div>
          <Image
            className="w-full mb-1"
            src={data.coverImage.resourceUrl}
            alt="img"
            width={100}
            height={100}
          />
          <div className="p-4">
            <p className="mt-2 text-lg text-gray-600">
              {data.detailedIntroductions.join("、")}
            </p>
          </div>
          <Image
            className="w-full"
            width={100}
            height={100}
            src={data.coverImage.resourceUrl}
            alt="img"
          />
        </div>
      </div>
    </CommonPageContainer>
  );
};

export default DetailPage;
