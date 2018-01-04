object holder {

  class Foo {
    private var x: Int = 1000
  }

  object Foo {
    def log(f: Foo) = f.x
  }

}

var x = new holder.Foo()
println(holder.Foo.log(x))
