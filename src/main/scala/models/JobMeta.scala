package models

import java.sql.Timestamp

// Structure representing the job_meta table
case class JobMeta(
  jobId: Long,
  jobName: String,
  jobDescription: String,
  scheduleType: String,
  scheduleTime: Timestamp,
  appId: Long
)