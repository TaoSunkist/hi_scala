/*
object MyApplication extends App {
    def main(args:Array[String]):Unit = {
        println("Hi")
    }
}
*/
class Main extends Greeting with App {
    println(say)
}
trait Greeting {
    lazy val say = "Hi"
}

trait Application {
    def main(args:Array[String]):Unit = {
        println("Hi")
    }
}
//对象构建器和事件转发器，回想trait在创建的时候会做哪些事情?
