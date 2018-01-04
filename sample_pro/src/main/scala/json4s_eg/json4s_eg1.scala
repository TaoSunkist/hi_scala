package me.huitao.json4s_eg

import me.huitao._
import okhttp3._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{write} 
import java.nio.charset.Charset

object Json4sEg1{ 
    implicit val formats = Serialization.formats(NoTypeHints)
    val JSON = MediaType.parse("application/json; charset=utf-8")
    val client = new OkHttpClient()
    case class Neo4jDto(sqlid:String,params:Map[String,String])
    val testJson = """{"data":null,"errmsg":"","errno":200}"""
    case class Result[T](val data:T,val errno:Int,val errmsg:String) 
    type UserStatus = Seq[UserStatu]

    case class UserStatu(
            status:String,
            overdue_date:Int,
            reject_cause:String,
            apply_time:String,
            apply_no:String,
            approve_status:Int
    )

    def json2Obj[T:Manifest](jsonStr:String):Option[T]={
        println(jsonStr)
        val userStatusJson = parse(jsonStr)
        val result = userStatusJson.extractOpt[T]
        result
    }

    def test(){
        val responseStr = OkHttpWrapper.newBuilder.add("userid","3244181e-ff97-3600-9927-ff5ae8eac4ba")
        .method("get") .httpUrl("http://credit.xiaoxinyong.com/credit/api/user_status").build.send
        println(json2Obj[Result[UserStatus]](responseStr))
       

        val neo4jDto = Neo4jDto("af0001",Map("id_number"->"510122199508194972","real_name"->"唐志颖"))
        val body = new FormBody.Builder().add("parameter",write(neo4jDto)).build()
        val request = new Request.Builder() .url("https://neoapi.xiaoxinyong.com/v1/operation_neo4j") .post(body).build()
        val response = client.newCall(request).execute()
        println(response.body.string)
        val args = Seq("0", "1")
        args.foreach(i=> println(i)) 
        args.foreach(println)
        //to返回的不是个数组,而是个包含了0-2并且可以让for遍历的序列
        for(i <- 0 to args.length){ println }
        var greetStrings = new Array[String](3)
        greetStrings(0) = "Hello"
        greetStrings.update(1," ")
        greetStrings(2) = "Scala\n"
        greetStrings.foreach(print)
        greetStrings.update(2,"Scala!\n")
        greetStrings.foreach(print)
        //_______________________________________________________________
        var strList = List[String]("a","b","c","d")//List和Array最大的区别在于List中的元素的内容是不可变的
        strList.foreach(s=>{print(s)})
        println("")
        //:::实现List的叠加
        var strList1 = List[String]("E","F","G","H")
        strList1.foreach(s=>{print(s)})
        println("")
        val newStrList = strList1:::strList
        newStrList.foreach(s=>{print(s" $s")})
    }
}
