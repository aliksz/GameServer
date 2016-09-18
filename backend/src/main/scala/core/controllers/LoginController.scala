package core.controllers

import com.twitter.finagle.http.{Request, _}
import com.twitter.finatra.http.Controller
import com.twitter.util.Future
import core.Names.{Password, SessionId, Username}
import core.services.LoginService
/**
  * Created by alex on 23.08.16.
  */
class LoginController(loginService: LoginService) extends Controller {

  post("/login") { req: Request =>
    loginService.login(req.getParam(Username, ""), req.getParam(Password, ""))
      .map {
        case Some(s) => response.ok
          .plain(s.id.toString + s.userId)
          .cookie(new Cookie(SessionId, s.id.toString))
        case None => response.unauthorized.plain("Wrong username/password")
      }
  }

  def unauthorized(request: Request): Future[Response] =
    Future.value(Response(Version.Http11, Status.Unauthorized))

}
