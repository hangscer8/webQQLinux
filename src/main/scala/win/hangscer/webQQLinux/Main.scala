package win.hangscer.webQQLinux

import java.awt._
import java.nio.file.Paths

import com.teamdev.jxbrowser.chromium._
import com.teamdev.jxbrowser.chromium.swing.BrowserView
import javax.swing._
import win.hangscer.webQQLinux.util.BrowerUtil

import scala.util.Try

object QQWeb extends App {
  val userDataPath = Paths.get(System.getProperty("user.home") + "/.webQQLinux" + "/Cache")
  val lazyFileOption = () => Option(userDataPath.toFile).flatMap(f => Option(f, f.exists())) match {
    case Some((f, false)) => f.mkdirs()
    case _ =>
  }
  Try(lazyFileOption())
  BrowerUtil.firstStep()
  val params = new BrowserContextParams(userDataPath.toString)
  params.setStorageType(StorageType.DISK)
  val context = new BrowserContext(params)
  val browser = new Browser(BrowserType.LIGHTWEIGHT, context)
  val view = new BrowserView(browser)
  val jFrame = new JFrame("webQQLinux")
  jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  jFrame.add(view, BorderLayout.CENTER)
  jFrame.setSize(420, 650)
  jFrame.setVisible(true)
  browser.loadURL("https://web2.qq.com")

  val icon = new ImageIcon(this.getClass.getResource("/img/qq.png"))
  val trayIcon = new TrayIcon(icon.getImage)
  trayIcon.setToolTip("webQQLinux")
  trayIcon.setPopupMenu(Option(new PopupMenu())
    .map { menu =>
      val menuItem = new MenuItem("refresh")
      menuItem.addActionListener { _ =>
        browser.loadURL("https://web2.qq.com")
      }
      menu.add(menuItem)
      menu
    }
    .map { menu =>
      menu.addSeparator()
      menu
    }
    .map { menu =>
      val menuItem = new MenuItem("exit")
      menuItem.addActionListener { e =>
        System.exit(1)
      }
      menu.add(menuItem)
      menu
    }
    .get)
  val systemTray = SystemTray.getSystemTray
  systemTray.add(trayIcon)
}