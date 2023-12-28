//
//  HomeView.swift
//  Mall Demo
//
//  Created by wenwuchen on 2023/12/21.
//

import SwiftUI
import URLImage 

struct APIData: Codable {
    let productList: [Item]
}

struct CoverImage: Codable {
    let resourceUrl: String
}

struct Item: Codable, Identifiable {
    var id: String { spuId }
    let spuId: String
    let name: String
    let introduction: String
    let salePrice: Float
    let coverImage: CoverImage

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        name = try container.decode(String.self, forKey: .name)
        introduction = try container.decode(String.self, forKey: .introduction)
        salePrice = try container.decode(Float.self, forKey: .salePrice)
        coverImage = try container.decode(CoverImage.self, forKey: .coverImage)

        if let spuIdString = try? container.decode(String.self, forKey: .spuId) {
            spuId = spuIdString
        } else if let spuIdInt = try? container.decode(Int.self, forKey: .spuId) {
            spuId = String(spuIdInt)
        } else {
            throw DecodingError.dataCorruptedError(forKey: .spuId, in: container, debugDescription: "spuId should be either a string or an integer")
        }
    }
}

func loadItems() -> [Item] {
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

struct HomeView: View {
    
    let items = loadItems()

    var body: some View {
        // @@ NavigationLink 必须包裹在 NavigationView 中才可以
        NavigationView{
            List(items) { item in
                ZStack {
                    HStack(alignment: .center) {
                        URLImage(URL(string: item.coverImage.resourceUrl)!) { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                        }
                        .frame(width: 80, height: 80)
                        .padding(.trailing, 5)

                        VStack(alignment: .leading) {
                            Text(item.name)
                                .font(.headline)
                                .padding(.top, 5)
                                .padding(.bottom, 3)

                            Text(item.introduction)
                                .font(.subheadline)
                                .padding(.bottom, 3)

                            HStack {
                                Spacer()
                                Text("¥ \(String(format: "%.1f", item.salePrice))")
                                    .font(.subheadline)
                                    .fontWeight(.bold)
                                    .foregroundColor(Color(hex: "#787F48"))
                            }
                        }
                    }

                    NavigationLink(destination: ProductDetailView(spuId: item.spuId)) {
                        EmptyView()
                    }.opacity(0).buttonStyle(PlainButtonStyle())
                }
            }
        }
        .padding(.top, 1) // @@ 必须有上边距，不然 statusBar 不会生效
        .statusBar(hidden: false)
    }
    
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}


extension Color {
    init(hex: String) {
        let hex = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        var int: UInt64 = 0
        Scanner(string: hex).scanHexInt64(&int)
        let r, g, b: UInt64
        switch hex.count {
        case 3: // RGB (12-bit)
            (r, g, b) = ((int >> 8) * 17, (int >> 4 & 0xF) * 17, (int & 0xF) * 17)
        case 6: // RGB (24-bit)
            (r, g, b) = (int >> 16, int >> 8 & 0xFF, int & 0xFF)
        default:
            (r, g, b) = (0, 0, 0)
        }
        self.init(
            .sRGB,
            red: Double(r) / 255,
            green: Double(g) / 255,
            blue: Double(b) / 255,
            opacity: 1
        )
    }
}
