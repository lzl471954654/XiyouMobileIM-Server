import java.util.concurrent.CopyOnWriteArraySet
import javax.websocket.*
import javax.websocket.server.ServerEndpoint

@ServerEndpoint(value = "/chatRoom")
class ChatRoom {

    lateinit var session: Session

    companion object {
        val set = CopyOnWriteArraySet<Session>()
    }

    @OnOpen
    fun onOpen(session: Session){
        println("open")
        this.session = session
        set.add(session)
    }

    @OnClose
    fun onClose(){
        set.remove(session)
    }


    @OnMessage
    fun onMessage(message:String,session: Session){
        println("onMessage")
        for (s in set) {
            if (s != session){
                s.basicRemote.sendText(message)
            }
        }
    }

    @OnError
    fun onError(session: Session, throwable: Throwable){
        session.close()
        set.remove(session)
    }
}