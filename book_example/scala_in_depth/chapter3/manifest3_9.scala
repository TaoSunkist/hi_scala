class Parent {
    def foo(bar:Int = 1, baz:Int = 2):Int = {
        println(s"Parent: bar = $bar, baz = $baz")
        bar + baz
    }
}
class Child extends Parent {
    //编译时由父类确定参数的顺序
    override def foo(baz:Int = 3, bar:Int = 4):Int = {
        println(s"Child: bar = $bar, baz = $baz")
        super.foo(baz, bar)
    }
}
val x:Parent = new Child()
//命名参数根据静态类型来决定参数顺序
println(x.foo(bar = 1))
