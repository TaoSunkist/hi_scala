
val seq = Seq(Seq("1"),Seq("2"),Seq("3"))
var test:Seq[Char]
 for {
    n1 <- seq
    n2 <- n1.head
} yield test
println(test)


