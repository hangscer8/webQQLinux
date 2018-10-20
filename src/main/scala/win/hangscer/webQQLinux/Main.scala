package win.hangscer.webQQLinux

import java.awt._

import com.teamdev.jxbrowser.chromium.Browser
import com.teamdev.jxbrowser.chromium.swing.BrowserView
import javax.swing._
import win.hangscer.webQQLinux.util.BrowerUtil

object QQWeb extends App {
  BrowerUtil.firstStep()
  val browser = new Browser()
  val view = new BrowserView(browser)
  val jFrame = new JFrame("webQQLinux")
  jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  jFrame.add(view, BorderLayout.CENTER)
  jFrame.setSize(500, 700)
  jFrame.setVisible(true)
  browser.loadURL("https://web2.qq.com")
}
