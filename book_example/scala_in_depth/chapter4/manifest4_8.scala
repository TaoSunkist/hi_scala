trait Logger {
    def log(content:String):Unit
}
trait DataAccess {
    val logger = new Logger
    val dao = new DataAccess
    def Query[T](in:String):T={
        logger.log("***") 
        dao.query(in)
    }
}

