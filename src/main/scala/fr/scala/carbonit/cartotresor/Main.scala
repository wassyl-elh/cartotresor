package fr.scala.carbonit.cartotresor

object Main {
  def main(args: Array[String]): Unit = {
    println("La carte aux trésors")
    val filename = "madre-de-dios.txt"
//    val carte = CarteReader.extractMap(filename)
    CarteReader.extractMap(filename)

  }
}
