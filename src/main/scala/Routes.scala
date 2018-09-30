import akka.http.scaladsl.server.Directives._
import api.{ ApiErrorHandler, JobOpsApi, JobInfoApi }

trait Routes extends ApiErrorHandler with JobOpsApi with JobInfoApi {
  val routes =
    pathPrefix("neuronapi") {
      jobOpsApi ~ jobInfoApi
    } //~ path("")(getFromResource("public/index.html"))
}