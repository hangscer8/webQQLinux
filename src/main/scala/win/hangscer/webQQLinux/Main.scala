package win.hangscer.webQQLinux

import javafx.application.Application
import javafx.scene.web.WebView
import javafx.scene.{Group, Scene}
import javafx.stage.Stage

class QQWebMainApplication extends Application {
  override def start(stage: Stage): Unit = {
    stage.setResizable(false)
    stage.setWidth(400)
    stage.setHeight(650)
    val scene = new Scene(new Group())
    val webView = new WebView()
    val webEngine = webView.getEngine
    scene.setRoot(webView)
    stage.setScene(scene)
    webEngine.load("https://web2.qq.com/")
    stage.show()
  }
}