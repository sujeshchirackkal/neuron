package dao

import models.definitions._
import slick.dbio.{Effect, NoStream}
import slick.lifted.TableQuery
import slick.sql.{FixedSqlAction, FixedSqlStreamingAction, SqlAction}
import utils.DatabaseConfig

import scala.concurrent.Future

trait BaseDao extends DatabaseConfig {
  val jobOpsTable = TableQuery[JobOpsTable]
  val jobDependencyTable = TableQuery[JobDependencyTable]
  val jobMetaTable = TableQuery[JobMetaTable]
  val jobStepsTable = TableQuery[JobStepsTable]
  val appInfoTable = TableQuery[AppInfoTable]

  protected implicit def executeFromDb[A](
    action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]
  ): Future[A] = {
    db.run(action)
  }
  protected implicit def executeReadStreamFromDb[A](
    action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]
  ): Future[Seq[A]] = {
    db.run(action)
  }
}
