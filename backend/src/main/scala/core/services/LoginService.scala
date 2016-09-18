package core.services

import java.util.UUID

import com.twitter.util.Future
import core.database.{DB, UserService}
import core.{Session, User}

import scala.collection.concurrent.TrieMap

/**
  * Created by alex on 25.08.16.
  */
class LoginService(userService: UserService)  {

  private val sessions = TrieMap[UUID, Session]()

  def sessionGenerator() = java.util.UUID.randomUUID()

  def session(sessionId: UUID): Option[Session] = {
    sessions.get(sessionId)
  }

  def login(username: String, password: String): Future[Option[Session]] = {
    userService.getUserByName(username)
      .map(_.filter(user => user.password == password).map(u => createSession(u)))
  }

  private def createSession(user: User) = {
    val session = Session(sessionGenerator(), user.id)
    sessions.put(session.id, session)
    session
  }

}
