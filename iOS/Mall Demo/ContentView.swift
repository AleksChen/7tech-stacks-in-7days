//
//  ContentView.swift
//  Mall Demo
//
//  Created by wenwuchen on 2023/12/21.
//

import SwiftUI

struct ContentTabView: View {
    // 创建视图模型的实例
    let homeViewModel = HomeViewModel()
    let meViewModel = MeViewModel(user: User(id: 1, name: "Pixel Bird"))
    
    @State private var selectedTab = 0
    @State private var isTabbarHidden = false
    
    var body: some View {
        VStack {
            Spacer()
            
            // 这里是你的内容视图
            if selectedTab == 0 {
                HomeView(viewModel: homeViewModel,isTabbarHidden: $isTabbarHidden)
            } else if selectedTab == 1 {
                MeView(viewModel: meViewModel)
            }
            
            Spacer()
            
            VStack(alignment: .center) {
                if !isTabbarHidden {
                    GeometryReader { geometry in
                        HStack {
                            Button(action: {
                                selectedTab = 0
                            }) {
                                Label("咖啡", systemImage: "house.fill")
                                    .foregroundColor(selectedTab == 0 ? .blue : .gray)
                            }
                            .frame(width: geometry.size.width / 2,height:40)
                            
                            Button(action: {
                                selectedTab = 1
                            }) {
                                Label("我的", systemImage: "person.fill")
                                    .foregroundColor(selectedTab == 1 ? .blue : .gray)
                            }
                            .frame(width: geometry.size.width / 2,height:40)
                        }
                    }
                }
            }.frame(height: 50)}
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentTabView()
    }
}
