package models

import java.sql.Timestamp

case class JobOp(
  jobExecutionId: Option[JobExecutionId],
  jobId: Long,
  executionStatus: String,
  log: String,
  startTime: Timestamp,
  endTime: Timestamp
)

case class JobOpUp(executionStatus: String, log: String, endTime: Timestamp)
