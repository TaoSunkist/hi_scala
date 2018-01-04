/**
 * 尾递归的优化成为循环执行，思考尾递归的弊端？
 * 模式匹配的优化成为java的跳转表【tableswitch】，这样会更优效率
 * 这个两个优化都是可以用注解来标注是否被使用:@tailrec @switch
 */
/**
 * 这个方法会执行tableswitch优化指令
 */
def unannotated(x:Int) = x match {
    /**
     * 如果要生成tableswitch指令，他需要明确的知道每个case语句的值
     */
    case 1 => "One"
    case 2 => "Two!"
    case z => z + "?"
}
println(unannotated(2))
import annotation.switch
/*warning: could not emit switch for @switch annotated match
 * 模式匹配无法被优化的，错误
def notOptimised(x:Int) = (x : @switch) match {
    case 1 => "One"
    case 2 => "Two!"
    case i:Int => "Other"//增加类型检查
}*/
/**
 * 广度优先搜索算法：搜索图或树的算法，先检测顶层元素，然后是邻居，接着邻居的邻居
 */

import annotation.tailrec
case class Node(name:String, edges:List[Node] = Nil)

def search(start:Node, predicate:Node=>Boolean) : Option[Node] = { 
    @tailrec
    def loop(nodeQueue:List[Node], visited:Set[Node]):Option[Node] = nodeQueue match {
        case head :: tail if predicate(head) => Some(head)
        case head :: tail if !visited.contains(head) => loop(tail ++ head.edges, visited + head)
        case head :: tail => loop(tail, visited)
        case Nil => None
    }
    loop(List(start), Set())
}

