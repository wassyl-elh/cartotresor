package fr.scala.carbonit.cartotresor

import fr.scala.carbonit.cartotresor.carte.CarteReader
import org.scalatest.flatspec.AnyFlatSpec

class CarteReaderSpec extends AnyFlatSpec{

  it should "print : Lana - Last position is (0,3) and has found 3" in {
    val carte = CarteReader.extractMap("madre-de-dios.txt")
    carte.process()
    carte.results()
  }

  it should "print : Walter - Last position is (2,3) and has found 2\nJesse - Last position is (2,0) and has found 3" in {
    val carte = CarteReader.extractMap("large-carte.txt")
    carte.process()
    carte.results()
  }

  it should "print : Walter - Last position is (0,1) and has found 0\nJesse - Last position is (1,1) and has found 0" in {
    val carte = CarteReader.extractMap("adventurer-collision.txt")
    carte.process()
    carte.results()
  }

}
