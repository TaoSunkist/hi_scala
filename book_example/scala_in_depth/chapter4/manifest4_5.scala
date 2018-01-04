trait Test2 extends Test{
    override def one():Unit = {
        println("Hi")
    }
    def test():Unit={
        super.one()
    }
}
trait Test{
    def one():Unit={
    }
}
