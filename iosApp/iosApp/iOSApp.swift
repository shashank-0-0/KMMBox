import SwiftUI

@main
struct iOSApp: App {
    let user1 = User(name: "Shashank", age: 25)


    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onAppear(){
                    print("my obj1 = \(user1)")
                    print("my obj2 = \(user1.copy(name: "shivang"))")
                }
        }
    }
}
struct User {
    let name: String
    let age: Int

    func copy(name: String? = nil, age: Int? = nil) -> User {
        return User(
            name: name ?? self.name,
            age: age ?? self.age
        )
    }
}
