package win.hangscer.webQQLinux

import java.awt._
import java.awt.event._
import java.nio.file.Paths

import com.teamdev.jxbrowser.chromium._
import com.teamdev.jxbrowser.chromium.swing.BrowserView
import javax.swing._
import win.hangscer.webQQLinux.util.BrowerUtil

import scala.util.Try

object QQWeb extends App {
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
  val browser = new Browser(BrowserType.LIGHTWEIGHT, context)
  browser.setAudioMuted(false)
  val view = new BrowserView(browser)
  val jFrame = new JFrame("webQQLinux")
  jFrame.add(view, BorderLayout.CENTER)
  jFrame.setSize(420, 650)
  jFrame.setVisible(true)
  jFrame.addWindowListener(new WindowAdapter {
    override def windowClosing(e: WindowEvent): Unit = jFrame.setVisible(false)
  })
  browser.loadURL("https://web2.qq.com")

  Option(System.getProperty("os.name")).map(p => p.contains("mac") || p.contains("Mac")) match {
    case Some(true) =>
      Try(com.apple.eawt.Application.getApplication.setDockIconImage(new ImageIcon(this.getClass.getResource("/img/qq3.png")).getImage))
    case _ =>
      jFrame.setIconImage(new ImageIcon(this.getClass.getResource("/img/qq3.png")).getImage)
  }

  val icon = new ImageIcon(this.getClass.getResource("/img/qq.png"))
  val trayIcon = new TrayIcon(icon.getImage)
  trayIcon.setToolTip("webQQLinux")
  trayIcon.setImageAutoSize(true)
  trayIcon.setPopupMenu(Option(new PopupMenu())
    .map { menu =>
      val menuItem = new MenuItem("show")
      menuItem.addActionListener { _ =>
        jFrame.setVisible(true)
      }
      menu.add(menuItem)
      menu
    }
    .map { menu =>
      menu.addSeparator()
      menu
    }
    .map { menu =>
      val menuItem = new MenuItem("refresh")
      menuItem.addActionListener { _ =>
        Try(browser.loadURL(browser.getURL))
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