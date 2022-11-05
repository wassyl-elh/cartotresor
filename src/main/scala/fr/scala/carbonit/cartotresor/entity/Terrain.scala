package fr.scala.carbonit.cartotresor.entity

sealed trait Terrain {

  def canCross: Boolean
  def hasTreasure: Boolean
  def pickTreasure(a: Adventurer) : Boolean

}

case class Mountain(x: Int, y: Int) extends Terrain {
  override def canCross: Boolean = false
  override def hasTreasure: Boolean = false
  override def pickTreasure(a: Adventurer): Boolean = false
}

case class Plains(x: Int, y: Int, nbTreasures: Int) extends Terrain{
  override def canCross: Boolean = true
  override def hasTreasure: Boolean = nbTreasures > 0
  override def pickTreasure(a: Adventurer): Boolean = {
    if( nbTreasures <= 0 ) {
      return false
    }
    nbTreasures -= 1
    a.treasuresFound += 1
    true
  }
}