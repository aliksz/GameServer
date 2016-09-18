package core

import java.util.UUID

import com.twitter.finagle.http.{Request, RequestProxy}
/**
  * Created by alex on 25.08.16.
  */

case class AuthorizedRequest(request: Request, userData: User) extends RequestProxy

case class User(id: UUID, username: String, password: String)
case class Session(id : UUID, userId: UUID)

object SessionContext {
  private val SessionField = Request.Schema.newField[Session]()

  implicit class SessionContextSyntax(val request: Request) extends AnyVal {
    def session: Session = request.ctx(SessionField)
  }

  private[core] def injectSession(session: Session, request: Request): Unit =
    request.ctx.update(SessionField, session)
}

object Names {
  val SessionId = "sessionId"
  val Username = "username"
  val Password = "password"
}