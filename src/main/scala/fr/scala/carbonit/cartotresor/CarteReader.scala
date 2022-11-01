package fr.scala.carbonit.cartotresor

import scala.io.Source

object CarteReader {

  def extractMap(filename: String) : Unit = {
    val bufferedSource = Source.fromFile(filename)
    val rawLines = bufferedSource.getLines.toList

    // Separating each information in each line
    val lines = rawLines
      .map( (str:String) => str.split("-").map(_.trim) )



    bufferedSource.close
  }

}
