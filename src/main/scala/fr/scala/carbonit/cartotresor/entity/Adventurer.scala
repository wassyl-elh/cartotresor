package fr.scala.carbonit.cartotresor.entity

object Cardinals extends Enumeration {
  type Cardinal = Value

  val North = Value(0, "North")
  val East  = Value(1, "East")
  val South = Value(2, "South")
  val West  = Value(4, "West")

  def valueOf(c:Char) : Cardinals.Cardinal =  {
    c match {
      case 'W' => West
      case 'E' => East
      case 'N' => North
      case 'S' => South
    }
  }
}

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

class Adventurer(val name:String,
                 val x:Int,
                 val y:Int,
                 val initialDirection:Cardinals.Cardinal,
                 val sequence:Array[Directions.Direction]) {

  var currentPosX:Int = x
  var currentPosY:Int = y
  var currentDirection:Cardinals.Cardinal = initialDirection
  var indexCurrentMove:Int = 0

  var treasuresFound:Int=0

  def getNextMove() : Directions.Direction = {
    val direction = sequence(indexCurrentMove)
    indexCurrentMove += 1
    direction
  }

  override def toString: String = {
    "[" + name + ", initial direction : " + initialDirection.toString + ", Sequence " + sequence.mkString("Array(", ", ", ")") + "]"
  }

}