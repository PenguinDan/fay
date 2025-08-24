import SwiftUI
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate {
    lazy var applicationComponent: IOSApplicationComponent = createApplicationComponent()

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
//        applicationComponent.rootAppInitializer.doInit()
        return true
    }

    private func createApplicationComponent() -> IOSApplicationComponent {
        IOSApplicationComponentCreateComponentKt.createComponent()
    }
}

@main
struct iOSApp: App {
    // Register app delegate for Firebase Setup
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    var body: some Scene {
        WindowGroup {
            ContentView(applicationComponent: delegate.applicationComponent)
        }
    }
}
