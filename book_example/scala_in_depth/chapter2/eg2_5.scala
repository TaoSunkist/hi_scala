//深入理解多态场景下的判等
import java.util.Date
/**
 * scala.Equals可以和equals串联起来使用， canEquals能使子类可以跳出父类的判等实现
 */
trait InstantaneousTime {
  val repr : Int

  override def equals(other:Any):Boolean = other match {
    case that:InstantaneousTime => {
      if (this eq that) {//引用判等
        true
      } else {//深度判等
        (that.## == this.##) && 
        (that.repr == this.repr)
      }
    }
    case _ => false
  }
  /**
   * 连接到equals的实现
   */
  override def hashCode():Int = repr.##
}

/**-----------------------------------------------------------------------\
 * 最好避免在深度判等的情况下使用多态，Scala为此不支持case class的子类继承？
 **----------------------------------------------------------------------*/
trait Event extends InstantaneousTime {
  val name  : String

  override def equals(other:Any):Boolean = other match {
    case that : Event => {
      if (this eq that) {//快速引用检查
        true
      } else { 
        (repr == that.repr) && 
        (name == that.name) 
      }
    }
    case _ => false
  }
}
 
val i = new InstantaneousTime {
  val repr = 2
}
val e = new Event {
  val repr = 2
  val name = "TestEvent"
}

println(i)
println(e)
println(i == e) 
println(e == i)
trait InstantaneousTime2 extends Equals {
  val repr : Int

  override def canEqual(other:Any):Boolean = other.isInstanceOf[InstantaneousTime2]

  override def equals(other:Any):Boolean = other match {
    case that:InstantaneousTime2 => {
      if (this eq that) {//引用判等
        true
      } else {//深度判等
        (that canEqual this) && 
        (that.## == this.##) && 
        (that.repr == this.repr)
      }
    }
    case _ => false
  }
  /**
   * 连接到equals的实现
   */
  override def hashCode():Int = repr.##
}

/**-----------------------------------------------------------------------\
 * 最好避免在深度判等的情况下使用多态，Scala为此不支持case class的子类继承？
 **----------------------------------------------------------------------*/
trait Event2 extends InstantaneousTime2 {
  val name  : String

  override def canEqual(other:Any):Boolean = other.isInstanceOf[Event2]

  override def equals(other:Any):Boolean = other match {
    case that : Event2 => {
      if (this eq that) {//快速引用检查
        true
      } else { 
        (that canEqual this) && 
        (repr == that.repr) && 
        (name == that.name) 
      }
    }
    case _ => false
  }
}

val i2 = new InstantaneousTime2 {
  val repr = 2
}
val e2 = new Event2 {
  val repr = 2
  val name = "TestEvent"
}

println(i2)
println(e2)
println(i2 == e2) 
println(e2 == i2)
