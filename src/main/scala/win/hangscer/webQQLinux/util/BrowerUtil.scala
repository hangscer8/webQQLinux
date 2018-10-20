package win.hangscer.webQQLinux.util

import java.lang.reflect.{Field, Modifier}
import java.math.BigInteger

import com.teamdev.jxbrowser.chromium.ay
object BrowerUtil {
  def firstStep() = { //第一步调用该方法
    try {
      val e = classOf[ay].getDeclaredField("e");
      e.setAccessible(true);
      val f = classOf[ay].getDeclaredField("f");
      f.setAccessible(true);
      val modifersField = classOf[Field].getDeclaredField("modifiers");
      modifersField.setAccessible(true);
      modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
      modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
      e.set(null, new BigInteger("1"));
      f.set(null, new BigInteger("1"));
      modifersField.setAccessible(false);
    } catch {
      case ex =>
        ex.printStackTrace();
    }
  }
}