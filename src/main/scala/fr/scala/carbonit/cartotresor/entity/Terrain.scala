package fr.scala.carbonit.cartotresor.entity

sealed trait Terrain
case class Mountain(x: Int, y: Int) extends Terrain
case class Plains(x: Int, y: Int) extends Terrain
case class Treasure(x: Int, y: Int, nbTreasures: Int) extends Terrain