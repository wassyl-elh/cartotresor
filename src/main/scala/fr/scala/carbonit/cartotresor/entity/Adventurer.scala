package fr.scala.carbonit.cartotresor.entity

object Cardinals extends Enumeration {
  type Cardinal = Value
  val West, East, North, South = Value

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


  override def toString: String = {
    "[" + name + ", initial direction : " + initialDirection.toString + ", Sequence " + sequence.mkString("Array(", ", ", ")") + "]"
  }

}