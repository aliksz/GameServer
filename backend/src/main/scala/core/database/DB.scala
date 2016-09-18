package core.database

import com.twitter.finagle.postgres.Client

/**
  * Created by alex on 17.09.16.
  */

object DBInstance extends DB

class DB {

  val client = Client(
    host = "localhost:5432",
    username = "postgres",
    password = Some("postgres"),
    database = "game"
    /*useSsl = false,             // Optional - defaults to `false`
    hostConnectionLimit = 1,    // Optional - defaults to `1`
    numRetries = 4,             // Optional - defaults to `4`
    customTypes = false,        // Optional - defaults to `false
    customReceiveFunctions = {  // Optional - defaults to no-op partial function
      case "mytyperecv" => ValueDecoder.instance(str => ???, (buf, charset) => ???)
    },
    binaryResults = false,      // Optional - defaults to `false`
    binaryParams = false        // Optional - defaults to `false`*/
  )

}
