package fr.scala.carbonit.cartotresor.entity

import fr.scala.carbonit.cartotresor.entity.Cardinals.{East, North, South, West}

sealed trait Terrain {

  def canCross: Boolean
  def hasTreasure: Boolean
  def pickTreasure(a: Adventurer) : Boolean

}

object Terrain {
  val DIRECTION_ARRAY: Array[Cardinals.Value] = Array(North, East, South, West)
}

case class Mountain(x: Int, y: Int) extends Terrain {
  override def canCross: Boolean = false
  override def hasTreasure: Boolean = false
  override def pickTreasure(a: Adventurer): Boolean = false
}

case class Plains(x: Int, y: Int, nbTreasures: Int) extends Terrain{

  private var currentTreasures = nbTreasures

  override def canCross: Boolean = true
  override def hasTreasure: Boolean = nbTreasures > 0
  override def pickTreasure(a: Adventurer): Boolean = {
    if( currentTreasures <= 0 ) {
      return false
    }
    currentTreasures -= 1
    a.treasuresFound += 1
    true
  }
}