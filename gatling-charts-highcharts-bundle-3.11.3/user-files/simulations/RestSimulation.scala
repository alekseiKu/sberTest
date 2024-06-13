import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RestSimulation extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:5000")

  val scn = scenario("Send message to Kafka")
    .exec(http("Send Message")
    .post("/send")
    .body(StringBody("""{"message": "Hello, Kafka!"}""")).asJson
    .check(status.is(200)))

  setUp(scn.inject(atOnceUsers(10)).protocols(httpProtocol))
}
