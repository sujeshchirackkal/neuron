package models.definitions

import models.{ JobExecutionId, JobOp }
import slick.driver.PostgresDriver.api._
import java.sql.Timestamp

class JobOpsTable(tag: Tag) extends Table[JobOp](tag, "job_ops") {
  def jobExecutionId = column[JobExecutionId]("job_execution_id", O.PrimaryKey, O.AutoInc)
  def jobId = column[Long]("job_id")
  def executionStatus = column[String]("execution_status")
  def log = column[String]("log")
  def startTime = column[Timestamp]("start_time")
  def endTime = column[Timestamp]("end_time")

  def * = (jobExecutionId.?, jobId, executionStatus, log, startTime, endTime) <>
    ((JobOp.apply _).tupled, JobOp.unapply)

  def job = foreignKey("ops_job_fk", jobId, TableQuery[JobMetaTable])(_.jobId)
}