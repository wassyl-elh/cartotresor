package fr.scala.carbonit.cartotresor.entity

object Directions extends Enumeration {
  type Direction = Value
  val Forward, TurnLeft, TurnRight = Value

  def valueOf(c:Char) : Directions.Direction =  {
    c match {
      case 'A' => Forward
      case 'D' => TurnRight
      case 'G' => TurnLeft
    }
  }
}
