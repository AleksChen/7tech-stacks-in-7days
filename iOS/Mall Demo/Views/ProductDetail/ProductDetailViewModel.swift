import SwiftUI
import Combine

func loadDetail() -> Detail? {
    guard let url = Bundle.main.url(forResource: "product-detail", withExtension: "json"),
          let data = try? Data(contentsOf: url) else {
        print("Failed to load file")
        return nil
    }
    
    do {
        let res = try JSONDecoder().decode(DetailData.self, from: data)
        return res.singleSpu
    } catch {
        print("Failed to decode JSON: \(error)")
        return nil
    }
}


class ProductDetailViewModel: ObservableObject {
    @Published var product: Detail?
    private var _isTabbarHidden: Binding<Bool> 
    

    init(isTabbarHidden: Binding<Bool>) {
        _isTabbarHidden = isTabbarHidden
    }

    func hiddenTabbar() {
        _isTabbarHidden.wrappedValue = true
    }

    func showTabbar() {
        _isTabbarHidden.wrappedValue = false
    }
    
    // 实现数据的获取和处理等逻辑
    func fetchProductDetail() {
        // 从服务调用/数据库获取产品详情
        self.product = loadDetail()
    }
}
