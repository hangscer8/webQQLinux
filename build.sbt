name := "webQQLinux"

version := "0.1"

scalaVersion := "2.12.6"

unmanagedJars in Compile ++= Seq(
  file(baseDirectory.value + "/lib/jxbrowser-linux64-6.16.jar"),
  file(baseDirectory.value + "/lib/jxbrowser-mac-6.16.jar"),
  file(baseDirectory.value + "/lib/jxbrowser-mac-6.16.jar"),
  file(baseDirectory.value + "/lib/jxbrowser-6.16.jar"),
)