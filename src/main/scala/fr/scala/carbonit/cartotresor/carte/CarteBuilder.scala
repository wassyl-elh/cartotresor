package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity._

import scala.collection.mutable.ListBuffer

class CarteBuilder(width:Int,
                   height:Int,
                   mountains: ListBuffer[Mountain] = ListBuffer(),
                   treasures: ListBuffer[Treasure] = ListBuffer(),
                   adventurers: ListBuffer[Adventurer] = ListBuffer()) {

  def addMountain(x:Int, y:Int) : Unit = {
    mountains += Mountain(x, y)
  }

  def addTreasure(x:Int, y:Int, nbTreasures:Int) : Unit = {
    treasures += Treasure(x, y, nbTreasures)
  }

  def addAdventurer(name:String, x:Int, y:Int, initialDirection:Cardinals.Cardinal, sequence:Array[Directions.Direction]) : Unit = {
    adventurers += new Adventurer(name, x, y, initialDirection, sequence)
  }

  def build() : Carte = {
    new Carte(width, height, mountains.toList, treasures.toList, adventurers.toList)
  }

}
