struct APIData: Codable {
    let productList: [Product]
}

struct CoverImage: Codable {
    let resourceUrl: String
}

struct Product: Codable, Identifiable {
    var id: String { spuId }
    let spuId: String
    let name: String
    let introduction: String
    let salePrice: Float
    let coverImage: CoverImage
    let tagImage: CoverImage?

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        name = try container.decode(String.self, forKey: .name)
        introduction = try container.decode(String.self, forKey: .introduction)
        salePrice = try container.decode(Float.self, forKey: .salePrice)
        coverImage = try container.decode(CoverImage.self, forKey: .coverImage)
        tagImage = try container.decode(CoverImage.self, forKey: .tagImage)

        if let spuIdString = try? container.decode(String.self, forKey: .spuId) {
            spuId = spuIdString
        } else if let spuIdInt = try? container.decode(Int.self, forKey: .spuId) {
            spuId = String(spuIdInt)
        } else {
            throw DecodingError.dataCorruptedError(forKey: .spuId, in: container, debugDescription: "spuId should be either a string or an integer")
        }
    }
}