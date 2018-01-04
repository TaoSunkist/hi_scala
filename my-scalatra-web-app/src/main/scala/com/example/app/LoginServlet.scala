package com.example.app

import org.scalatra._

class LoginServlet extends ScalatraServlet {

  get("/") {
    println("Test")
    "Hi LoginServlet:GET!\n"
  }

}
