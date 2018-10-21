# a web wrapper  app for [webQQ](https://web2.qq.com/)

## runtime require 
* java >= 1.8
* operation system in (linux,mac)

## build require
* java >= 1.8
* sbt >= 1.0

## how to build

```
sbt universal:packageBin ## universal zip
sbt debian:packageBin ## debian package
sbt rpm:packageBin ## rpm package
sbt run ## just run
```

## snapshot

![snap1](snapshot/snapshot1.png)