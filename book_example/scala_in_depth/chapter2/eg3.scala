//索引服务，使用Key来查找特定项
trait Service[Key,Value] {
  def lookUp(k:Key):Option[Value]
  def insert(k:Key, v:Value):Unit 
}
/**
 * 可变的HashMap
 */
import collection.mutable.{HashMap=>MutableHashMap}
class MutableService[Key,Value] extends Service[Key,Value] {
  var currentIndex = new MutableHashMap[Key,Value]
  def lookUp(k:Key):Option[Value] synchronized = currentIndex.get(k)
  /**
   * 使用锁来同步插入操作
   */
  def insert(k:Key,v:Value):Unit = synchronized {
    currentIndex.put(k, v)
  }
}
/**
 * 不可变的HasHmap
 */
import collection.immutable.{HashMap=>ImmutableHashMap}
class ImmutableService[Key,Value] extends Service[Key,Value] {
  //可变引用的不可变变量
  var currentIndex = new ImmutableHashMap[Key,Value]
  def lookUp(k:Key):Option[Value] = currentIndex.get(k)
    
  def insert(k:Key,v:Value):Unit = synchronized {
    currentIndex = currentIndex + ((k, v))
  }
}
