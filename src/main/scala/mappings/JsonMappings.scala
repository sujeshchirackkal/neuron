package mappings

import models.{ JobOp, JobOpUp, JobInfo, JobStepSub, JobMeta }
import spray.json.DefaultJsonProtocol
import java.sql.Timestamp
import spray.json.JsonFormat
import spray.json.DeserializationException
import spray.json.JsNumber
import spray.json.JsValue

trait JsonMappings extends DefaultJsonProtocol {
  implicit object TimestampFormat extends JsonFormat[Timestamp] {
    def write(obj: Timestamp) = JsNumber(obj.getTime)

    def read(json: JsValue) = json match {
      case JsNumber(time) => new Timestamp(time.toLong)

      case _              => throw new DeserializationException("Date expected")
    }
  }

  implicit val jobOpsFormat = jsonFormat6(JobOp)
  implicit val jobOpUpFormat = jsonFormat3(JobOpUp)

  implicit val jobStepFormat = jsonFormat4(JobStepSub)
  implicit val jobMetaFormat = jsonFormat6(JobMeta)
  implicit val jobInfoFormat = jsonFormat8(JobInfo)
}