package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.exception.InvalidCarteParsingException
import fr.scala.carbonit.cartotresor.entity.{Cardinals, Directions}

import scala.io.Source

object CarteReader {

  def extractMap(filename: String): Unit = {
    val bufferedSource = Source.fromFile(filename)
    val rawLines = bufferedSource.getLines.toList

    // Closing Buffer
    bufferedSource.close

    // Separating each information in each line
    val lines = rawLines.map((str: String) => str.split("-").map(_.trim))

    // TODO Utile ?
    if (lines.head(0) != "C") throw new InvalidCarteParsingException("Define a map")

    val width = lines.head(1).toInt
    val height = lines.head(2).toInt

    val carteBuilder = new CarteBuilder(width, height)

    lines
      .drop(1)
      .foreach((arr: Array[String]) => {
        arr(0) match {
          case "M" => carteBuilder.addMountain(arr(1).toInt, arr(2).toInt)
          case "T" => carteBuilder.addTreasure(arr(1).toInt, arr(2).toInt, arr(3).toInt)
          case "A" => {
            val sequence = arr(5).toArray.map((c: Char) => Directions.valueOf(c))
            carteBuilder.addAdventurer(arr(1), arr(2).toInt, arr(3).toInt, Cardinals.valueOf(arr(4).head), sequence)
          }
          case _ => throw new InvalidCarteParsingException("Unknown default direction given : " + arr(0))
        }
      })

    val carte = carteBuilder.build()

    println(carte.toString)

    carte.process()
  }

  //  private def translateFromFileContent() = {
  //
  //  }

}
