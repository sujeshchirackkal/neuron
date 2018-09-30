package dao

import models.{ JobExecutionId, JobOp, JobOpUp }
import slick.driver.PostgresDriver.api._
import scala.concurrent.Future

object JobOpsDao extends BaseDao {

  //Create New Job Ops
  def create(jobOp: JobOp): Future[JobExecutionId] =
    jobOpsTable returning jobOpsTable.map(_.jobExecutionId) += jobOp

  //Update Job Ops
  def update(newJobOp: JobOpUp, jobExecutionId: JobExecutionId): Future[Int] =
    jobOpsTable.filter(_.jobExecutionId === jobExecutionId)
      .map(jobOp => (jobOp.executionStatus, jobOp.log, jobOp.endTime))
      .update((newJobOp.executionStatus, newJobOp.log, newJobOp.endTime))

  //Get All Job Ops for a Job ID
  def findByJobId(jobId: Long): Future[Seq[JobOp]] = jobOpsTable.filter(_.jobId === jobId).result

  // Get Latest Job Ops for a Job ID
  def findLatestJobOpsByJobId(jobId: Long): Future[JobOp] =
    jobOpsTable.filter(_.jobId === jobId).sortBy(_.jobExecutionId.desc).result.head

  // Get Specific Job Ops by Job Execution Id
  def findByJobExecutionId(jobExecutionId: JobExecutionId): Future[JobOp] =
    jobOpsTable.filter(_.jobExecutionId === jobExecutionId).result.head
}
