def forTest(){
    for {
        n <- {
            println("exec method n1")
            Some("from method n1 data")
        }
        n <- n2(n)
    } { println(n) }
}
def n2(arg:String):Some[String] = {
    println(s"exec method n2, $arg")
    Some("from method n2 data")
}

forTest()
