package dao

import models.{ JobInfo, JobMeta, JobStep, JobStepSub, JobDependency }
import slick.driver.PostgresDriver.api._
import scala.concurrent.Future

object JobInfoDao extends BaseDao {

  def createJob(jobMeta: JobMeta): Future[Long] = {
    jobMetaTable returning jobMetaTable.map(_.jobId) += jobMeta
  }

  def createJobStep(jobSteps: Seq[JobStep]): Future[Option[Int]] =
    jobStepsTable ++= jobSteps

  def createJobDep(jobDependency: Seq[JobDependency]): Future[Option[Int]] =
    jobDependencyTable ++= jobDependency

  def deleteJob(jobId: Long): Future[Int] = jobMetaTable.filter(_.jobId === jobId).delete

  def deleteJobStep(jobId: Long): Future[Int] = jobStepsTable.filter(_.jobId === jobId).delete
  
  def deleteJobDep(jobId: Long): Future[Int] = jobDependencyTable.filter(_.jobId === jobId).delete

}