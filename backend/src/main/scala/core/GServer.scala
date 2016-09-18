package core

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import core.controllers.{LoginController, PingController}
import core.database.{DBInstance, UserService}
import core.services.LoginService

object GServerInstance extends GServer

class GServer extends HttpServer {

  override def defaultFinatraHttpPort = ":8080"

  override def configureHttp(router: HttpRouter) {
    val loginService = new LoginService(new UserService(DBInstance))
    router
      .filter[CommonFilters]
      .add(new PingController(loginService))
      .add(new LoginController(loginService))
  }

}
