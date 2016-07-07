package fantasyfootball.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class PostTest extends Simulation {
  val baseHttp = http.baseURL("http://jsonplaceholder.typicode.com")
  val postFeed = csv("posts.csv").circular

  setUp(
    scenario("User View Scenario")
      .feed(postFeed)
      .exec(
        http("get post 1")
          .get("/posts/${postId}")
          .check(jsonPath("$.id").is("${postId}"))
      )
      .inject(constantUsersPerSec(10) during (10 seconds))
  ).protocols(baseHttp)
}
