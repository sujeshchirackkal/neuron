package models.definitions

import slick.driver.PostgresDriver.api._
import models.JobMeta
import java.sql.Timestamp

class JobMetaTable(tag: Tag) extends Table[JobMeta](tag, "job_meta") {
  def jobId = column[Long]("job_id", O.PrimaryKey)
  def jobName = column[String]("job_name")
  def jobDescription = column[String]("job_description")
  def scheduleType = column[String]("schedule_type")
  def scheduleTime = column[Timestamp]("schedule_time")
  def appId = column[Long]("app_id")

  def * = (jobId, jobName, jobDescription, scheduleType, scheduleTime, appId) <>
    ((JobMeta.apply _).tupled, JobMeta.unapply)

  def app = foreignKey("app_job_fk", appId, TableQuery[AppInfoTable])(_.appId)
}