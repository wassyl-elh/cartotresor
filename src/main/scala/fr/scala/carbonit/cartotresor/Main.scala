package fr.scala.carbonit.cartotresor

import fr.scala.carbonit.cartotresor.carte.CarteReader

object Main {
  def main(args: Array[String]): Unit = {
    println("La carte aux trésors")
    val filename = "madre-de-dios.txt"
    val filename2 = "large-carte.txt"
//    val carte = CarteReader.extractMap(filename)
    val carte = CarteReader.extractMap(filename2)

    //
    carte.process()

    // Print results
    carte.results()
  }
}
