package models.definitions

import models.AppInfo
import slick.driver.PostgresDriver.api._

class AppInfoTable(tag: Tag) extends Table[AppInfo](tag, "application") {
  def appId = column[Long]("app_id")
  def appName = column[String]("app_name")

  def * = (appId, appName) <> ((AppInfo.apply _).tupled, AppInfo.unapply)
}