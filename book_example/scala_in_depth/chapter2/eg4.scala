import java.io.File
def getTemporaryDirectory(tmpArg:Option[String]):File = {
    tmpArg.map(name => { new File(name) }).filter(_.isDirectory).getOrElse(new File(System.getProperty("java.io.tmpdir")))
}
println(getTemporaryDirectory(Some("/data1/www")))

println("John".map(s=>Map[String,Int](s.toString->1)).toList)

val username:Option[String] = Some("Lilly")
for (uname <- username) {
    println(uname)
}
//使用多个未初始化的变量构造变量
case class JdbcConn(url:String, uname:String, pword:String) 

def createJdbcConn(urlOpt:Option[String], unameOpt:Option[String], pwordOpt:Option[String]):Option[JdbcConn] = for {
        url      <- urlOpt
        username <- unameOpt
        password <- pwordOpt
} yield JdbcConn(url, username, password)

println(createJdbcConn(Some("jdbc:postgresql://127.0.0.1/dirname"), Some("postgres") ,Some("postgres")))

//通用转换函数
def lift3[A, B, C, D](f:Function3[A, B, C, D]):Function3[Option[A], Option[B], Option[C], Option[D]] = {
    (oa:Option[A], ob:Option[B], oc:Option[C]) => for (a <- oa; b <- ob; c <- oc) yield f(a,b,c)
}
println(lift3(createJdbcConn))
//res3: (Option[Option[String]], Option[Option[String]], Option[Option[String]]) => Option[Option[JdbcConn]] = $$Lambda$1288/1786872722@26fb4d06
