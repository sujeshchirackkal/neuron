import dao.BaseDao
import models.JobOp

import akka.event.{ NoLogging, LoggingAdapter }
import akka.http.scaladsl.testkit.ScalatestRouteTest
import utils.MigrationConfig
import org.scalatest._
import scala.concurrent.Await
import scala.concurrent.duration._
import java.sql.Timestamp

trait BaseServiceSpec extends WordSpec
  with Matchers
  with ScalatestRouteTest
  with Routes
  with MigrationConfig
  with BaseDao {

  protected val log: LoggingAdapter = NoLogging

  import driver.api._

  val testJobOp = Seq(
    JobOp(Some(100), 1, "Running", "Job is running", new Timestamp(1532595624668L), new Timestamp(1532595624668L)),
    JobOp(Some(101), 1, "Completed", "Job is finished", new Timestamp(1532595624668L), new Timestamp(1532595624668L)),
    JobOp(Some(102), 2, "Running", "Job is running", new Timestamp(1532595624668L), new Timestamp(1532595624668L)))

  reloadSchema()
  Await.result(jobOpsTable ++= testJobOp, 10.seconds)
}