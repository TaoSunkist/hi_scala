case class SimulationMsg(id:Long, msg:String, ip:String, sender:AnyRef)
case class SimulationCtx(id:Long)
/**
 * 
 */
trait SimulationEntity {
    def handleMsg(msg:SimulationMsg, ctx:SimulationCtx):Unit
}
trait NetworkEntity {
    def getMacAddr(ipAddr:String):String
    def hasIpAddr():Boolean
    def pingReq(ip:String,sender:AnyRef):NetworkEntity
    def pingResp(ip:AnyRef,sender:AnyRef):NetworkEntity

    def handleMsg(msg:SimulationMsg, ctx:SimulationCtx):Unit = msg match {
        case pingReq(ip, sender) if hasIpAddr() => ctx resp (sender, pingResp(getMacAddr(ip)))
        case _ => super.handleMsg(msg,ctx)
    }
}
