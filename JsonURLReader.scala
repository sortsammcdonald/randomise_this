// JsonFileReader.scala

import org.json4s._
import org.json4s.native.JsonMethods._

object JsonURLReader {
  
  def readJsonURL(URL: String): JValue ={
    val jsonString2 = scala.io.Source.fromURL(URL).mkString
    parse(jsonString2)
  }

}



// def main(args: Array[String]): Unit =