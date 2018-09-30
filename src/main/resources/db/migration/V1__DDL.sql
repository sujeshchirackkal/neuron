DROP TABLE if exists "application";

CREATE TABLE "application" (
  "app_id" BIGINT PRIMARY KEY,
  "app_name" VARCHAR
);

INSERT INTO "application" ("app_id", "app_name")
VALUES
    (1, 'Hadoop'),
    (2, 'Teradata'),
    (3, 'Snowflake'),
    (4, 'Openshift hosted Web Service'
);

CREATE TABLE "job_meta" (
  "job_id"   BIGINT PRIMARY KEY,
  "job_name" VARCHAR NOT NULL,
  "job_description" VARCHAR NOT NULL,
  "schedule_type" VARCHAR NOT NULL,
  "schedule_time" TIMESTAMP,
  "app_id" BIGINT REFERENCES application(app_id)
);

CREATE TABLE "job_steps" (
  "job_id" BIGINT REFERENCES job_meta(job_id),
  "step_no" INTEGER,
  "step_type" VARCHAR NOT NULL,
  "step_description" VARCHAR NOT NULL,
  "sql" VARCHAR NOT NULL
);

CREATE TABLE "job_dependency" (
  "job_id" BIGINT REFERENCES job_meta(job_id),
  "dependent_job_id" BIGINT REFERENCES job_meta(job_id)
);

CREATE TABLE "job_ops" (
  "job_execution_id" BIGSERIAL PRIMARY KEY,
  "job_id" BIGINT REFERENCES job_meta(job_id),
  "execution_status" VARCHAR NOT NULL,
  "log" VARCHAR NOT NULL,
  "start_time" TIMESTAMP,
  "end_time" TIMESTAMP
);



