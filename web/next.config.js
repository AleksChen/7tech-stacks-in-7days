/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "gridcoffee-online.fbcontent.cn",
      },
    ],
  },
};

module.exports = nextConfig;
