package models

case class JobStep(
  jobId: Long,
  stepNo: Int,
  stepType: String,
  stepDescription: String,
  sql: String
)

case class JobStepSub(
  stepNo: Int,
  stepType: String,
  stepDescription: String,
  sql: String
)