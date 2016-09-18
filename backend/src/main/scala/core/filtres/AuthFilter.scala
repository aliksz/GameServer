package core.filtres

import java.util.UUID

import com.twitter.finagle.http.{Request, Response, Status, Version}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.util.Future
import core.Names.SessionId
import core.SessionContext
import core.services.LoginService


/**
  * Created by alex on 25.08.16.
  */
import SessionContext._

class AuthFilter(loginService: LoginService) extends SimpleFilter[Request, Response] {

  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    val session = request.cookies.getValue(SessionId)
      .flatMap(sessionId => loginService.session(UUID.fromString(sessionId)))

    if (session.isDefined) {
      injectSession(session.get, request)
      service(request)
    }
    else unauthorized(request)
  }

  def unauthorized(request: Request): Future[Response] =
    Future.value(Response(Version.Http11, Status.Unauthorized))
}
