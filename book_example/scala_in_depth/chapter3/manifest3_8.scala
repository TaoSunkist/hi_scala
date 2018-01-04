class Foo{
    def foo(one:Int = 1, two:String = "two", three:Double = 2.5):String = one + two + three
}
val f = new Foo
f.foo(one=3)
println(s"${f.foo()}")

println(s"${f.foo(two = "not tow")}")
//参数的命名在动态中存在困惑
println(f.foo(4, three = 3.0f))
