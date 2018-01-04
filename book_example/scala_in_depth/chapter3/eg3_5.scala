object Average {
    def avg(l:List[Double]):Double = {
        //val sum = l.foldLeft(0.0){ _ + _ }
        //val sum = (0.0/:(l))((x:Double,y:Double)=>{ x + y })
        val sum = (0.0/:(l)){ _ + _ }//简写模式，隐式函数
        return sum/l.size.toDouble
    }
    class `$anonfun`{
        class `1` {
            println("O MY!")
        }
    }
}
val list = List[Double](9,2.8,2.2,1)
println(Average.avg(list))
//class `Average$$anonfun$1`{
//    println("O MY!")
//}
//println(Average.avg(list))
