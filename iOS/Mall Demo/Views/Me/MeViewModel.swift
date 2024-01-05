import SwiftUI
import Combine

class MeViewModel: ObservableObject {
    @Published var user: User

    init(user: User) {
        self.user = user
    }

    func fetchUserData() {
        // 这里是获取用户数据的代码，你可能需要从网络或本地数据库获取数据
        // 一旦数据获取成功，你可以更新self.user
    }
}
