import SwiftUI
import Combine

func loadItems() -> [Product] {
    guard let url = Bundle.main.url(forResource: "product-list", withExtension: "json"),
          let data = try? Data(contentsOf: url) else {
        print("Failed to load file")
        return []
    }
    do {
        let res = try JSONDecoder().decode(APIData.self, from: data)
        print(res.productList)
        return res.productList
    } catch {
        print("Failed to decode JSON: \(error)")
        return []
    }
}

class HomeViewModel: ObservableObject {
    @Published var products: [Product]

    init() {
        self.products = []
    }

    func fetchProducts() {
        self.products = loadItems()
    }
}
