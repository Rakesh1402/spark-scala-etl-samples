package com.rakeshd.retailetl.refinedzone.input_handler
import org.apache.spark.sql.{DataFrame, SparkSession}

object CSVFileReader extends FileReader {
  override def readFiles(sparkSession: SparkSession, inputPath: String): Option[DataFrame] = {

    if (!isFilePresent(sparkSession, inputPath, InputFileType.CSV.toString.toLowerCase))
      return Option.empty[DataFrame]

    logger.info("Going to read csv files from " + inputPath + " directory")
    val inputDF = sparkSession
      .read
      .option("header", true)
      .option("delimeter", ",")
      .csv(inputPath)

    Some(inputDF)
  }
}