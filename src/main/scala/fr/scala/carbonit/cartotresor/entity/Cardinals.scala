package fr.scala.carbonit.cartotresor.entity

object Cardinals extends Enumeration {
  type Cardinal = Value

  val North: Cardinals.Value = Value(0, "N")
  val East: Cardinals.Value  = Value(1, "E")
  val South: Cardinals.Value = Value(2, "S")
  val West: Cardinals.Value  = Value(3, "W")

  def valueOf(c:Char) : Cardinals.Cardinal =  {
    c match {
      case 'W' => West
      case 'E' => East
      case 'N' => North
      case 'S' => South
    }
  }
}