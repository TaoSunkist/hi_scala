class Point(var x: Int = 0, var y: Int = 0) {
  override def hashCode() = y + (31 * x)

  override def equals(that: Any): Boolean = true
}

class Point2(var x: Int = 0, var y: Int = 0) extends Equals {
  def move(x:Int, y:Int):Unit={
      this.x += x
      this.y += y
  }
  def canEqual(that: Any) = that match {
    case that: Point2 => true //类型判定
    case _ => false
  }

  override def hashCode() = y + (31 * x)

  override def equals(that: Any): Boolean = {
    def strictEquals(other: Point2) = this.x == other.x && this.y == other.y
    that match {
      //Scala中基本类型都来自与AnyVal,Java中则是AnyRef,他是Object的alias
      case x: AnyRef if this eq x => true
      case x: Point2 => canEqual(x) && strictEquals(x)
      case _ => false
    }
  }
}

var p1 = new Point(2, 1)
var p2 = new Point(1, 2)
println(s"$p1\n$p2")

var p21 = new Point2(1, 1)
var p22 = new Point2(1, 2)
println(s"p21 = $p21\np22 = $p22")

val pMap = Map[Any, String](p1 -> "p1", p2 -> "p2", p21 -> "p21", p22 -> "p22")
println(s"Map[Any,String] : $pMap")

var p23 = new Point2(1, 1)
println(s"p23 hashCode : $p23")

val pMap2 = collection.immutable.HashMap(p21 -> "p21", p22 -> "p22")
println(s"HashMap[Any,String] : $pMap2")
p21.move(1,1)
println(s"p21 = $p21")

//hash值是不会动态更新的
//println(s"HashMap[Any,String] : ${pMap2(p21)}")
//throw NoSuchElementException
println(s"${pMap2.find( _._1 == p21)}")
