package com.example.app

import org.scalatra._
import java.util.concurrent.TimeUnit

class MyScalatraServlet extends ScalatraServlet {

  get("/") {
    TimeUnit.SECONDS.sleep(3)
    "Hi MyScalatraServlet:GET!\n"
  }

}
