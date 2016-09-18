package core.database

import com.twitter.util.Future
import core.User
/**
  * Created by alex on 17.09.16.
  */

class UserService(db: DB) {

  def getUserByName(username: String): Future[Option[User]] = {
    db.client.select("select * from users") { row =>
      User(row.get("id"), row.get("name"), row.get("password"))
    }.map(_.find(user => user.username == username))
  }
}
