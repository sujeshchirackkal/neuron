package models.definitions

import slick.driver.PostgresDriver.api._
import models.JobStep
import java.sql.Timestamp

class JobStepsTable(tag: Tag) extends Table[JobStep](tag, "job_steps") {
  def jobId = column[Long]("job_id")
  def stepNo = column[Int]("step_no")
  def stepType = column[String]("step_type")
  def stepDescription = column[String]("step_description")
  def sql = column[String]("sql")

  def * = (jobId, stepNo, stepType, stepDescription, sql) <>
    ((JobStep.apply _).tupled, JobStep.unapply)
  
  def job = foreignKey("job_steps_job_id_fkey", jobId, TableQuery[JobMetaTable])(_.jobId)
}