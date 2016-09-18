package core.controllers

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import core.filtres.AuthFilter
import core.services.LoginService

class PingController(loginService: LoginService) extends Controller {

  filter(new AuthFilter(loginService)).get("/ping") { request: Request =>
    response.ok.plain("pong")
  }
}
