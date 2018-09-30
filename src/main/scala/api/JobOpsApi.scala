package api

import dao.JobOpsDao

import scala.concurrent.ExecutionContext.Implicits.global
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import mappings.JsonMappings
import models.{ JobOp, JobOpUp }
import akka.http.scaladsl.server.Directives._
import spray.json._

trait JobOpsApi extends JsonMappings {
  val jobOpsApi =
    (path("jobops") & post) {
      entity(as[JobOp]) { jobOp =>
        complete(JobOpsDao.create(jobOp).map(_.toJson))
      }
    } ~
    (path("jobops" / LongNumber) & put) { jobExecutionId =>
      entity(as[JobOpUp]) { jobOpUp =>
        complete(JobOpsDao.update(jobOpUp, jobExecutionId).map(_.toJson))
      }
    } ~
    (path("jobops" / "all") & parameter("jobId") & get) { jobId =>
      complete(JobOpsDao.findByJobId(jobId.toLong).map(_.toJson))
    } ~
    (path("jobops" / "latest") & parameter("jobId") & get) { jobId =>
      complete(JobOpsDao.findLatestJobOpsByJobId(jobId.toLong).map(_.toJson))
    } ~
    (path("jobops" / LongNumber) & get) { jobExecutionId =>
      complete(JobOpsDao.findByJobExecutionId(jobExecutionId).map(_.toJson))
    }
}