import com.example.app._
import org.scalatra._
import javax.servlet.ServletContext
import com.example.app.MyScalatraServlet
import com.example.app.LoginServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet, "/*")
    context.mount(new LoginServlet, "/login")
  }
}
