package fr.scala.carbonit.cartotresor.entity

import fr.scala.carbonit.cartotresor.entity.Cardinals.{East, North, South, West}

// Any cell of the grid implements this trait
sealed trait Terrain {

  // if the cell is passable
  def canCross: Boolean

  // if the cell contains treasures
  def hasTreasure: Boolean

  // the cell receives the adventurer and gives a treasure to the adventurer if the cell still has some of them
  def accept(a: Adventurer) : Boolean

  // notices the cell that an adventurer joins another
  def clear() : Unit

}

// Terrain object having an array useful for adventurers navigation
object Terrain {
  val DIRECTION_ARRAY: Array[Cardinals.Value] = Array(North, East, South, West)
}

case class Mountain(x: Int, y: Int) extends Terrain {
  override def canCross: Boolean = false
  override def hasTreasure: Boolean = false
  override def accept(a: Adventurer): Boolean = false
  override def clear(): Unit = {}

  override def toString: String = {
    "M - " + x + " - " + y
  }
}

case class Plains(x: Int, y: Int, nbTreasures: Int) extends Terrain {

  private var currentTreasures = nbTreasures
  var adventurerPresent = false

  // Adventurer can cross
  override def canCross: Boolean = !adventurerPresent
  override def hasTreasure: Boolean = currentTreasures > 0
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

  override def toString: String = {
    "T - " + x + " - " + y + " - " + currentTreasures
  }

}