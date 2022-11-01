package fr.scala.carbonit.cartotresor

import scala.collection.mutable.ListBuffer

class CarteBuilder(width:Int,
                   height:Int,
                   mountains: ListBuffer[Mountain] = ListBuffer(),
                   treasures: ListBuffer[Treasure] = ListBuffer()) {

  def addMountain(x:Int, y:Int) : Unit = {
    mountains += Mountain(x, y)
  }

  def addTreasure(x:Int, y:Int, nbTreasures:Int) : Unit = {
    treasures += Treasure(x, y, nbTreasures)
  }

  def build() : Carte = {
    new Carte(width, height, mountains.toList, treasures.toList)
  }

}
