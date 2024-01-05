struct DetailData: Codable {
    let singleSpu: Detail
}

struct HashCoverImage: Codable, Hashable {
    let resourceUrl: String
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(resourceUrl)
        // hasher.combine(其他属性)
    }
}

struct Detail: Codable, Identifiable {
    var id: String { spuId }
    let spuId: String
    let name: String
    let introduction: String
    let salePrice: Float
    let cupType: [String]
    let beansSource: String
    let coverImage: CoverImage
    let beansTagImage: CoverImage
    let carouselImages: [HashCoverImage]

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        name = try container.decode(String.self, forKey: .name)
        introduction = try container.decode(String.self, forKey: .introduction)
        beansSource = try container.decode(String.self, forKey: .beansSource)
        salePrice = try container.decode(Float.self, forKey: .salePrice)
        coverImage = try container.decode(CoverImage.self, forKey: .coverImage)
        cupType = try container.decode([String].self, forKey: .cupType)
        beansTagImage = try container.decode(CoverImage.self, forKey: .beansTagImage)
        carouselImages = try container.decode([HashCoverImage].self, forKey: .carouselImages)

        if let spuIdString = try? container.decode(String.self, forKey: .spuId) {
            spuId = spuIdString
        } else if let spuIdInt = try? container.decode(Int.self, forKey: .spuId) {
            spuId = String(spuIdInt)
        } else {
            throw DecodingError.dataCorruptedError(forKey: .spuId, in: container, debugDescription: "spuId should be either a string or an integer")
        }
    }
}
