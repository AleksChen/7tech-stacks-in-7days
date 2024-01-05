import SwiftUI
import URLImage

struct ProductDetailView: View {
    @ObservedObject var viewModel: ProductDetailViewModel

    var body: some View {
        VStack {
            
            
            if let product = viewModel.product {
//                TabView {
//                                ForEach(product.carouselImages, id: \.self) { item in
//                                    URLImage(URL(string: item.resourceUrl)!) { image in
//                                        image
//                                            .resizable()
//                                            .aspectRatio(contentMode: .fit)
//                                    }
//                                    .frame(width: 80, height: 80)
//                                    .padding(.trailing, 5)
//                                }
//                            }
//                            .frame(height: 200)
//                            .tabViewStyle(PageTabViewStyle())
                
                // 展示产品详情
                Text(product.name)
                Text(product.introduction)
                Text("\(product.salePrice)")
            }
        }
        .onAppear {
            viewModel.fetchProductDetail()
        }
    }
}
