# README

# 开始

![image-20231128145331949](/Users/wenwuchen/Library/Application Support/typora-user-images/image-20231128145331949.png)

# 笔记

## 视图绑定

在 Kotlin 中，Android Jetpack 提供了一个叫做 View Binding 的功能，这个功能可以在编译时生成对视图的引用。这样，你就可以避免使用 findViewById 并且使得代码更加简洁了。下面是如何使用 View Binding 来获取这个按钮：

```
android {
    ...
    viewBinding {
        enabled = true
    }
}

```

## 原理

Android 的页面是由一个个 Activity 组成的，页面主要分为 UI 布局部分和逻辑处理部分， UI 布局部分需要由上图中蓝色 main 目录下的 activity_main.xml 文件处理，而逻辑部分则是由 com.myname.myapplication 中的 MainActivity 文件处理。

- `app/src/main/res/layout/fragment_home.xml` 维护 Home 的视图
- `app/src/main/java/com/example/grid/ui/home/HomeFragment.kt` 维护 Home 的逻辑
