This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Getting Started

First, run the development server:

```bash
yarn dev
```

> node version >= v18.17.0

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start editing the page by modifying `app/page.tsx`. The page auto-updates as you edit the file.

> Change Log

- APP Router 需要使用 ` next/navigation`` 去实现路由跳转才能保持页面状态，使用  `<Link>` 跳转则不行
- 使用 Layout 去实现 Tabbar，使用 `usePathname` 去获取路径。对非 Tab 页面不展示 Tabbar
