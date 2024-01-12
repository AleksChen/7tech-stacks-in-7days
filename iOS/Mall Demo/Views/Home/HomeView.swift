import SwiftUI
import URLImage
import Kingfisher


struct HomeView: View {
    @ObservedObject var viewModel: HomeViewModel
    @Binding var isTabbarHidden: Bool
    
    var body: some View {
        NavigationView {
            List(viewModel.products, id: \.id) { item in
                ZStack{
                    HStack(alignment: .center) {
                        URLImage(URL(string: item.coverImage.resourceUrl)!) { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                        }
                        .frame(width: 80, height: 80)
                        .padding(.trailing, 5)
                        
                        VStack(alignment: .leading) {
                            HStack(alignment: .center) {
                                if let imageUrl = item.tagImage?.resourceUrl {
                                    KFImage(URL(string: imageUrl)).resizable()
                                        .aspectRatio(contentMode: .fit)
                                        .scaledToFit().frame(width: 18, height: 18)
                                }
                                Text(item.name)
                                    .font(.headline)
                                    .padding(.top, 5)
                                    .padding(.bottom, 3)
                            }
                            
                            Text(item.introduction)
                                .font(.subheadline)
                                .padding(.bottom, 3)
                            
                            HStack {
                                Spacer()
                                Text("¥ \(String(format: "%.1f", item.salePrice))")
                                    .font(.subheadline)
                                    .fontWeight(.bold)
                                    .foregroundColor(Color("Grid"))
                            }
                        }
                        
                    }
                    NavigationLink(destination: ProductDetailView( viewModel: ProductDetailViewModel(isTabbarHidden: $isTabbarHidden))) {}.opacity(0)
                }
            }
        }
        .padding(.top, 1) // @@ 必须有上边距，不然 statusBar 不会生效
        .statusBar(hidden: false)
        .onAppear {
            viewModel.fetchProducts()
            // isTabbarHidden = false
        }
    }
}
