package fr.scala.carbonit.cartotresor.entity

object Directions extends Enumeration {
  type Direction = Value

  val Forward: Directions.Value = Value("A")
  val TurnLeft: Directions.Value = Value("G")
  val TurnRight: Directions.Value = Value("D")

  def valueOf(c:Char) : Directions.Direction =  {
    c match {
      case 'A' => Forward
      case 'D' => TurnRight
      case 'G' => TurnLeft
    }
  }
}
