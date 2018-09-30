package models.definitions

import slick.driver.PostgresDriver.api._
import models.JobDependency

class JobDependencyTable(tag: Tag) extends Table[JobDependency](tag, "job_dependency") {
  def jobId = column[Long]("job_id")
  def dependentJobId = column[Long]("dependent_job_id")

  def * = (jobId, dependentJobId) <> ((JobDependency.apply _).tupled, JobDependency.unapply)

  def job = foreignKey(
    "job_dependency_job_id_fkey",
    jobId,
    TableQuery[JobMetaTable])(_.jobId)

  def dependentJob = foreignKey(
    "job_dependency_dependent_job_id_fkey",
    dependentJobId,
    TableQuery[JobMetaTable])(_.jobId)
}