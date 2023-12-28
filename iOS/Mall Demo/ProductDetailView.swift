//
//  ProductDetailView.swift
//  Mall Demo
//
//  Created by wenwuchen on 2023/12/28.
//

import SwiftUI


struct ProductDetailView: View {
    var spuId: String

    var body: some View {
        VStack {
            Text(spuId)
            // Text(item.introduction)
            // 添加更多的视图来显示商品的详细信息
        }
    }
}

struct ProductDetailView_Previews: PreviewProvider {
    static var previews: some View {
        ProductDetailView(spuId: "1")
    }
}
