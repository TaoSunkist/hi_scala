organization := "me.huitao"
name         := "hi_scala"  
scalaVersion := "2.12.2"       
version      := "1.0.0" 

libraryDependencies ++= Seq (
    "com.typesafe.play" % "play-json_2.11" % "2.6.0",
    "com.squareup.okhttp3" % "okhttp" % "3.9.0",
    "javax.annotation" % "javax.annotation-api" % "1.3.1",
    "org.json4s" %% "json4s-jackson" % "3.5.2",
    "org.json4s" %% "json4s-native" % "3.5.2"
)
