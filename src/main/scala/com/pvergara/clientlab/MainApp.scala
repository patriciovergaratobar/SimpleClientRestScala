package com.pvergara.clientlab
import com.softwaremill.sttp._
import com.softwaremill.sttp.json4s._


object MainApp {
  
  case class Gps(telefono: String,imei:String,simcard:String, serial:String, latitud: String, longitud: String, fechaEvento: String, fechaGPS: String, contrato:String)
  implicit val serialization =  org.json4s.native.Serialization

  def main(args: Array[String]): Unit = {

    println("Inicio")


    callApiGet
    callApiPost
  }

  def callApiGet(): Unit = {

    val request = sttp
      .get(uri"http://gps.grupoglobal.cl/api/service.php/report/device/withoutReporting/FMB")
      .response(asJson[Seq[Gps]])

    implicit val backend = HttpURLConnectionBackend()

    val response: Id[Response[Seq[Gps]]] = request.send()

    if (response.code == StatusCodes.Ok) println("GET Ok!")


    println(response.body)
    val lista: Seq[Gps] = response.body.toOption.get
    for {
      gps <- lista
    } {
      println(gps)
      backend.close()
    }
  }

  def callApiPost(): Unit = {

    val signup = Some("yes")
    val request = sttp
      // send the body as form data (x-www-form-urlencoded)
      //.body(Map("name" -> "John", "surname" -> "doe"))
      // send the body as json data (application/json)
      .body(Seq("name" -> "Patricio", "surname" -> "Abraham"))
      // use an optional parameter in the URI
      .post(uri"https://httpbin.org/post?signup=$signup")

    implicit val backend = HttpURLConnectionBackend()
    val response = request.send()

    if (response.code == StatusCodes.Ok) println("POST Ok!")

    println(response.body)
    println(response.headers)
  }

}
