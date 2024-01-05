import SwiftUI

struct MeView: View {
    @ObservedObject var viewModel: MeViewModel
    
    var body: some View {
        VStack {
            // 上半部分
            ZStack {
                Color.green
                    .edgesIgnoringSafeArea(.all)
                
                VStack {
                    Spacer()
                    HStack {
                        Spacer()
                        VStack {
                            Image("Avator")
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(width: 60, height: 60)
                                .clipShape(Circle())
                                .overlay(Circle().stroke(Color.white, lineWidth: 4))
                                .shadow(radius: 10)
                            Text(viewModel.user.name).fontWeight(.bold).foregroundColor(Color("Grid")).padding(.top, 30)
                        }.padding(.horizontal, 100).padding(.vertical, 50)
                            .background(Color.white)
                            .cornerRadius(10)
                        Spacer()
                    }
                    Spacer()
                }
            }
            
            // 下半部分
            VStack {
                ForEach(1..<4) { row in
                    HStack {
                        ForEach(1..<3) { col in
                            Rectangle()
                                .fill(Color.gray)
                                .frame(width: 150, height: 62)
                                .overlay(Text("设置\(row * col)").foregroundColor(.white))
                                .padding()
                        }
                    }
                }
            }
        }
        .onAppear {
            viewModel.fetchUserData()
        }
    }
}
