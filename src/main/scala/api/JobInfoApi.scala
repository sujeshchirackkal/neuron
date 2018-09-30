package api

import dao.JobInfoDao

import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import mappings.JsonMappings
import models.{ JobInfo, JobOpUp, JobMeta, JobStepSub, JobStep, JobDependency }

import spray.json._

trait JobInfoApi extends JsonMappings {
  val jobInfoApi =
    (path("job") & post) {
      entity(as[JobInfo]) { jobInfo => {

        println("Job Info = " + jobInfo)

        val metaInsertResult = JobInfoDao.createJob(
          JobMeta(
            jobInfo.jobId,
            jobInfo.jobName,
            jobInfo.jobDescription,
            jobInfo.scheduleType,
            jobInfo.scheduleTime,
            jobInfo.appId
          )
        )

        JobInfoDao.createJobStep(jobInfo.jobSteps.map { jobStep: JobStepSub =>

          val step =  JobStep(
            jobInfo.jobId,
            jobStep.stepNo,
            jobStep.stepType,
            jobStep.stepDescription,
            jobStep.sql)
          println("Step = " + step)
          step
        })

        JobInfoDao.createJobDep(jobInfo.dependentJobIds.map { jobDep =>
          val dep = JobDependency(jobInfo.jobId, jobDep)
          println("Dep = " + dep)
          dep
        })

        complete(metaInsertResult.map(_.toJson))
      }}
    } ~
      (path("job" / LongNumber) & delete) { jobId =>
        JobInfoDao.deleteJobStep(jobId)
        JobInfoDao.deleteJobDep(jobId)
        complete(JobInfoDao.deleteJob(jobId).map(_.toJson))
      }
}