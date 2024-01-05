import SwiftUI
import Kingfisher

struct ProductDetailView: View {
    @ObservedObject var viewModel: ProductDetailViewModel
    @State private var selectedTab = 0
    
    @State private var selectedCupType: String? // 跟踪选中的按钮
    @State private var totalPrice: Int? // 新的状态变量
    
    var body: some View {
        ZStack {
            if let product = viewModel.product {
                Color.white
                    .edgesIgnoringSafeArea(.all)
                
                VStack(alignment: .leading) {
                    TabView(selection: $selectedTab) {
                        // @@ 需要用带缓存功能的图片库 KFImage
                        ForEach(product.carouselImages.indices, id: \.self) { index in
                            KFImage(URL(string: product.carouselImages[index].resourceUrl)!).resizable()
                                .aspectRatio(contentMode: .fit)
                                .scaledToFit()
                                .tag(index)
                        }
                    }
                    .tabViewStyle(PageTabViewStyle())
                    .indexViewStyle(PageIndexViewStyle(backgroundDisplayMode: .always))
                    .frame(height: 250)
                    .onAppear(perform: {
                        Timer.scheduledTimer(withTimeInterval: 3, repeats: true) { _ in
                            selectedTab = (selectedTab + 1) % product.carouselImages.count
                        }
                    })
                    
                    // 展示产品详情
                    VStack(alignment: .leading) {
                        Text(product.name).font(.system(size: 28)).fontWeight(.bold).foregroundColor(Color("Grid")).padding(.bottom, 10)
                        Text(product.introduction).font(.system(size: 18)).foregroundColor(.gray).padding(.bottom, 10)
                        
                        // 遍历渲染 product.cupType 字段
                        let gridItems = [GridItem(.adaptive(minimum: 100))] // 设置每个 GridItem 的最小宽度为 100
                        
                        LazyVGrid(columns: gridItems, spacing: 10) {
                            ForEach(product.cupType, id: \.self) { cupType in
                                Button(action: {
                                    // 点击按钮时生成一个随机数字
                                    totalPrice = cupType.count
                                    selectedCupType = cupType
                                }) {
                                    Text(cupType)
                                        .padding()
                                        .font(.system(size: 14))
                                        .background(selectedCupType == cupType ? Color("Grid") : Color.white) // 根据是否选中来选择不同的背景色
                                        .foregroundColor(selectedCupType == cupType ?  Color.white: Color("Grid")  )
                                        .cornerRadius(10) // 设置圆角
                                        .shadow(radius: 10) // 设置阴影
                                        .lineLimit(1) // 设置行数限制为 1
                                }
                            }
                        }                   
                    }.padding(20)
                }.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
                
                VStack {
                    Spacer() // 将视图推到屏幕底部
                    if let selectedCupType = selectedCupType {
                        Text("\(selectedCupType)")
                    }
                    Text((totalPrice ?? 0) > 0 ? "价格：\(totalPrice!) 元" : "未选择规格")
                        .frame(width: 300, height: 50)
                        .background(Color("Grid"))
                        .foregroundColor(.white)
                        .cornerRadius(10)
                }
            }
        }
        .navigationBarHidden(true)
        .onAppear {
            viewModel.fetchProductDetail()
        }
    }
}
