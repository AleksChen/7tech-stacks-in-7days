"use client";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import TabBar from "@/components/TabBar";

const inter = Inter({ subsets: ["latin"] });

const metadata: Metadata = {
  title: "Grid Coffee",
  description: "Grid Coffee",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        {children}
        <TabBar />
      </body>
    </html>
  );
}
