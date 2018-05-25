import javax.websocket.*
import javax.websocket.server.ServerEndpoint

@ServerEndpoint(value = "/char")
class TestChar {
    @OnOpen
    fun onOpen(session: Session){
        println("Session:${session.id}----onOpen")
        println(session.queryString)
        println(session.requestParameterMap)
    }

    @OnClose
    fun onClose(){
        println("onClose")
    }


    @OnMessage
    fun onMessage(message:String,session: Session){
        println("Session:${session.id}----Message:$message")
        session.asyncRemote.sendText("phasmatos!")
    }

    @OnError
    fun onError(session: Session,throwable: Throwable){
        //println("Session:${session.id}----onError:${throwable.message}")
    }
}