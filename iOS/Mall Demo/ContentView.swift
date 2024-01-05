//
//  ContentView.swift
//  Mall Demo
//
//  Created by wenwuchen on 2023/12/21.
//

import SwiftUI

struct ContentView: View {
  // 创建视图模型的实例
    let homeViewModel = HomeViewModel()
    let meViewModel = MeViewModel(user: User(id: 1, name: "Pixel Bird"))
    
    var body: some View {
        NavigationView{
            TabView {
                HomeView(viewModel: homeViewModel)
                    .tabItem {
                        Image(systemName: "house.fill")
                        Text("首页")
                    }
                MeView(viewModel: meViewModel)
                    .tabItem {
                        Image(systemName: "person.fill")
                        Text("我")
                    }
            }.font(.headline)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
