package fr.scala.carbonit.cartotresor

import scala.io.Source

object CarteReader {

  def extractMap(filename: String) : Unit = {
    val bufferedSource = Source.fromFile(filename)
    val rawLines = bufferedSource.getLines.toList

    // Closing Buffer
    bufferedSource.close

    // Separating each information in each line
    val lines = rawLines
      .map( (str:String) => str.split("-").map(_.trim) )

    try {
      // TODO Utile ?
      if(lines(0)(0) != "C") throw new AssertionError("Define a map")

      val width = lines(0)(1).toInt
      val height = lines(0)(2).toInt

//      val carte = Carte(width, height)

      val carteBuilder = new CarteBuilder(width, height)

      lines
        .drop(1)
        .foreach( (arr:Array[String]) => {
          arr(0) match {
            case "M" => carteBuilder.addMountain(arr(1).toInt, arr(2).toInt)
            case "T" => carteBuilder.addTreasure(arr(1).toInt, arr(2).toInt, arr(3).toInt)
            case "A" => {
               // TODO
            }
          }
        })

      val carte = carteBuilder.build()

      println(carte.toString)

    } catch {
      case e:AssertionError => println("File not valid")
    }

  }

}
