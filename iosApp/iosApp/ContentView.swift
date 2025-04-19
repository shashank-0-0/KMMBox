import SwiftUI
import MyFramework
import MyCoreNetwork

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        VStack {
            Button("Click \(getHttpCLientIos()) !") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
        .onAppear {
//            print("SEEHERE \(Random().randomk1())")
        }
    }
}
func getHttpCLientIos() -> String{
//    return Random().random1()
    return ""
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
func changeDirection(turn: Turn) {
    switch turn {
    case .left:
        print("go left")
    case .right:
        print("go right")
    }
}
