package fr.scala.carbonit.cartotresor.entity

object Cardinals extends Enumeration {
  type Cardinal = Value

  val North = Value(0, "North")
  val East  = Value(1, "East")
  val South = Value(2, "South")
  val West  = Value(3, "West")

  def valueOf(c:Char) : Cardinals.Cardinal =  {
    c match {
      case 'W' => West
      case 'E' => East
      case 'N' => North
      case 'S' => South
    }
  }
}