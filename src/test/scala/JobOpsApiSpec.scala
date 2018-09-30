//import akka.http.scaladsl.model.{ StatusCode, MediaTypes, HttpEntity }
//import dao.JobOpsDao
//import models.JobOp
//import org.scalatest.concurrent.ScalaFutures
//import spray.json._
//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
//
//class JobOpsApiSpec extends BaseServiceSpec with ScalaFutures {
//  "JobOp service" should {
//    "retrieve JobOp list" in {
//      Get("/jobops/all?jobId=1") ~> jobOpsApi ~> check {
//        responseAs[JsArray] should be(testJobOp.take(2).toJson)
//      }
//    }
//    "retrieve JobOp by execution id" in {
//      Get("/jobops/100") ~> jobOpsApi ~> check {
//        responseAs[JsObject] should be(testJobOp.head.toJson)
//      }
//    }
//    "retrieve latest JobOp by job id" in {
//      Get("/jobops/latest?jobId=1") ~> jobOpsApi ~> check {
//        responseAs[JsObject] should be(testJobOp(1).toJson)
//      }
//    }
//    "create jobop properly" in {
//      val requestEntity = HttpEntity(
//        MediaTypes.`application/json`,
//        JsObject(
//          "jobId" -> JsNumber(testJobOp.head.jobId),
//          "executionStatus" -> JsString(testJobOp.head.executionStatus),
//          "log" -> JsString(testJobOp.head.log),
//          "startTime" -> JsNumber(testJobOp.head.startTime.toString().toLong),
//          "endTime" -> JsNumber(testJobOp.head.endTime.toString().toLong)).toString())
//      Post("/jobops", requestEntity) ~> jobOpsApi ~> check {
//        response.status should be(StatusCode.int2StatusCode(200))
//        Get("/jobops/all") ~> jobOpsApi ~> check {
//          responseAs[Seq[JobOp]] should have length 3
//        }
//      }
//    }
//
//    "update jobop by id" in {
//      val newExecutionStatus = "Completed"
//      val newLog = "Job is completed."
//      val newEndTime = 1532598248826L
//      val requestEntity = HttpEntity(
//        MediaTypes.`application/json`,
//        JsObject(
//          "executionStatus" -> JsString(newExecutionStatus),
//          "log" -> JsString(newLog),
//          "endtime" -> JsNumber(newEndTime)).toString())
//      Put("/jobops/100", requestEntity) ~> jobOpsApi ~> check {
//        response.status should be(StatusCode.int2StatusCode(200))
//        whenReady(JobOpsDao.findByJobExecutionId(100)) { result =>
//          result.executionStatus should be(newExecutionStatus)
//        }
//      }
//    }
//
//  }
//}