//
//  ContentView.swift
//  Mall Demo
//
//  Created by wenwuchen on 2023/12/21.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        TabView {
            HomeView()
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("首页")
                }
            
            MeView()
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("我")
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
