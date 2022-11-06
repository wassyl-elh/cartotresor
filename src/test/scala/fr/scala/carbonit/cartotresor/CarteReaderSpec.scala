package fr.scala.carbonit.cartotresor

import fr.scala.carbonit.cartotresor.carte.CarteReader
import fr.scala.carbonit.cartotresor.exception.InvalidCarteParsingException
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.a

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

  it should "throw InvalidCarteParsingException if a mountain is placed out of boundaries" in {
    a [InvalidCarteParsingException] should be thrownBy{
      CarteReader.extractMap("wrong-carte-1.txt")
    }
  }

  it should "throw InvalidCarteParsingException if no Carte are present" in {
    a [InvalidCarteParsingException] should be thrownBy{
      CarteReader.extractMap("wrong-carte-2.txt")
    }
  }

  it should "throw InvalidCarteParsingException if wrong letters are used" in {
    a [InvalidCarteParsingException] should be thrownBy{
      CarteReader.extractMap("wrong-carte-3.txt")
    }
  }

  it should "throw InvalidCarteParsingException if no adventurer are present" in {
    a [InvalidCarteParsingException] should be thrownBy{
      CarteReader.extractMap("wrong-carte-4.txt")
    }
  }

  it should "throw InvalidCarteParsingException if no treasures are present" in {
    a [InvalidCarteParsingException] should be thrownBy{
      CarteReader.extractMap("wrong-carte-5.txt")
    }
  }

  it should "throw NoSuchElementException if file is empty" in {
    a [NoSuchElementException] should be thrownBy{
      CarteReader.extractMap("empty.txt")
    }
  }
}
