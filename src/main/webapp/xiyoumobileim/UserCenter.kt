import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import javax.websocket.Session

object UserCenter {
    val userMap : ConcurrentHashMap<String,Session> = ConcurrentHashMap()
    val userCounter = AtomicInteger()


    inline fun addUser(name:String,session: Session){
        userCounter.incrementAndGet()
        userMap[name] = session
    }
}