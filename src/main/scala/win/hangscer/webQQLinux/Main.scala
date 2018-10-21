package win.hangscer.webQQLinux

import java.awt._
import java.nio.file.Paths

import com.teamdev.jxbrowser.chromium._
import com.teamdev.jxbrowser.chromium.swing.BrowserView
import javax.swing._
import win.hangscer.webQQLinux.util.BrowerUtil

import scala.util.Try

class QQWebMain  {
  val userDataPath = Paths.get(System.getProperty("user.home") + "/.webQQLinux" + "/Cache")
  val lazyFileOption = () => Option(userDataPath.toFile).flatMap(f => Option(f, f.exists())) match {
    case Some((f, false)) =>
      f.mkdirs()
    case _ =>
  }

  Try(lazyFileOption())
  BrowerUtil.firstStep()
  val params = new BrowserContextParams(userDataPath.toString)
  params.setStorageType(StorageType.DISK)
  val context = new BrowserContext(params)
  val browser = new Browser(BrowserType.HEAVYWEIGHT, context)
  browser.setAudioMuted(false)
  val view = new BrowserView(browser)
  val jFrame = new JFrame("webQQLinux")
  jFrame.setIconImage(new ImageIcon(this.getClass.getResource("/img/qq3.png")).getImage)
  jFrame.add(view, BorderLayout.CENTER)
  jFrame.setSize(400, 670)
  jFrame.setVisible(true)
  jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  browser.setLoadHandler(new DefaultLoadHandler {
    override def onCertificateError(params: CertificateErrorParams): Boolean = {
      false //ignore error
    }
  })
  browser.loadURL("https://web2.qq.com")
}