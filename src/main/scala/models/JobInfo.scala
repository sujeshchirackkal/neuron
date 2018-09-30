package models

import java.sql.Timestamp

case class JobInfo(
  jobId: Long,
  jobName: String,
  jobDescription: String,
  scheduleType: String,
  scheduleTime: Timestamp,
  jobSteps: Seq[JobStepSub],
  dependentJobIds: Seq[Long],
  appId: Long
)