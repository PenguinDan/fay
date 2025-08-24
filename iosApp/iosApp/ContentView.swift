import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    private let applicationComponent: IOSApplicationComponent
    
    init(applicationComponent: IOSApplicationComponent) {
        self.applicationComponent = applicationComponent
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.mainViewController(
            applicationComponent: self.applicationComponent
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    private let applicationComponent: IOSApplicationComponent
    
    init(applicationComponent: IOSApplicationComponent) {
        self.applicationComponent = applicationComponent
    }
    
    var body: some View {
        ComposeView(applicationComponent: self.applicationComponent)
                .ignoresSafeArea() // Compose has own keyboard handler
    }
}
