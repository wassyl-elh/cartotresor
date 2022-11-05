package fr.scala.carbonit.cartotresor.entity

import fr.scala.carbonit.cartotresor.entity.Cardinals.{East, North, South, West}

sealed trait Terrain {

  def canCross: Boolean
  def hasTreasure: Boolean
  def accept(a: Adventurer) : Boolean
  def clear() : Unit

}

object Terrain {
  val DIRECTION_ARRAY: Array[Cardinals.Value] = Array(North, East, South, West)
}

case class Mountain(x: Int, y: Int) extends Terrain {
  override def canCross: Boolean = false
  override def hasTreasure: Boolean = false
  override def accept(a: Adventurer): Boolean = false
  override def clear(): Unit = {}
}

case class Plains(x: Int, y: Int, nbTreasures: Int) extends Terrain{

  private var currentTreasures = nbTreasures
  var adventurerPresent = false

  // Adventurer can cross
  override def canCross: Boolean = !adventurerPresent
  override def hasTreasure: Boolean = nbTreasures > 0
  override def accept(a: Adventurer): Boolean = {
    // Adventurer came to this cell
    adventurerPresent = true
    if( currentTreasures <= 0 ) {
      return false
    }
    currentTreasures -= 1
    a.treasuresFound += 1
    true
  }

  override def clear(): Unit = {
    adventurerPresent = false
  }

}