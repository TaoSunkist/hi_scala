package me.huitao

import okhttp3._



object OkHttpWrapper {

    val mClient = new OkHttpClient()
    var mBuilder:Builder = null
    
    def newBuilder = {
        mBuilder = Builder()
        this.mBuilder
    }

    def init(builder:Builder)= {
        mBuilder = builder
        this
    }
    def send()={
        val requestBuilder = new Request.Builder()
        val parameters = mBuilder.mParameters 
        if ("get".equals(mBuilder.mMethod)) {
            var param = "?"
            parameters.foreach{ case(k,v)=>{
                if(param.contains("=")) param+="&"
                param += s"$k=$v"
            }}
            mBuilder.mHttpUrl+=param
            requestBuilder.url(mBuilder.mHttpUrl)
        } else if ("post".equals(mBuilder.mMethod)) {
            //TODO WAIT...add post body
        }
        requestBuilder.url(mBuilder.mHttpUrl)
        val request = requestBuilder.build()
        val response = mClient.newCall(request).execute()
        response.body.string
    }

    case class Builder() {

        var mHttpUrl:String = ""
        var mParameters:Map[String,String] = Map()
        var mMethod:String = ""

        def add(name:String,value:String)={
            mParameters += (name -> value)
            this
        }
        def method(method:String) = {
            this.mMethod = method
            this
        }
        def httpUrl(httpUrl:String) = {
            this.mHttpUrl = httpUrl
            this
        }
        def build = OkHttpWrapper.init(this)
    }
}
