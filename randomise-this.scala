import scala.io.Source
import org.json4s._
import JsonURLReader._
import scala.util.Random
import org.json4s.native.JsonMethods._
import org.json4s.JString

object use_this_scala {
  @main def randomise: Unit = {

    def getRandomElement[A](list: List[A]): A = {
      val randomIndex = Random.nextInt(list.length)
      list(randomIndex)
    } // Takes parameter A and returns A from a list containing multiple As

    def convertURL(A: String, B: String): String = {
      val updateURL = s"$A$B"
      val lowerUpdateURL = updateURL.toLowerCase()
      lowerUpdateURL
    } // generates composite URL from strings to check API. Making lower case per UseThis API's requirements

    val jsonCat = readJsonURL("https://usesthis.com/api/categories/")
    val categories = (jsonCat \ "categories").children
    val categoryNames = categories.map(category => (category \ "name").values.toString)

    val categoryList: List[String] = categoryNames.toList

    val randomCategory = getRandomElement(categoryList)

    val baseURL = "https://usesthis.com/api/categories/"

    val baseURL2 = "https://usesthis.com/interviews/"
    val newURL = convertURL(baseURL, randomCategory) // used to generate URL for names
    val toRemove = "api/"
    val newURL2 = newURL.replace(toRemove, "") // used to generate URL for interview

    val jsonContent = readJsonURL(newURL)

    val interviews = jsonContent \ "interviews"

    val slug = (interviews \ "slug")

    val slug_elements = slug match {
      case JArray(list) => list
      case _ => List.empty}

    val names = slug_elements.collect {
      case JString(value) => value
    }

    val rand_interview = getRandomElement(names)

    val rand_interview_url = convertURL(baseURL2, rand_interview)
    
    println(newURL2) // use to make interface

    println(rand_interview_url) // use to make interface
  }

}
