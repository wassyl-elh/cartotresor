package fr.scala.carbonit.cartotresor

import fr.scala.carbonit.cartotresor.carte.CarteReader

object Main {
  def main(args: Array[String]): Unit = {
    println("CARTE AUX TRESORS")
    val filename = "madre-de-dios.txt"
    val output = "output.txt"

    println("Reading file '"+filename+"' ... ")
    // Read carte from file
    val carte = CarteReader.extractMap(filename)

    println("Computing adventurers' travel ...")
    // Compute adventurers' travel
    carte.process()

    println("Results :")
    // Print adventurer results
    carte.results()

    println("Writing into " + output)
    // Write results into output file
    carte.writeInto(output)
  }
}
