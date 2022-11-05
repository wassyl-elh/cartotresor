package fr.scala.carbonit.cartotresor.entity

import fr.scala.carbonit.cartotresor.entity.Cardinals.{East, North, South, West}
import fr.scala.carbonit.cartotresor.entity.Terrain.DIRECTION_ARRAY

class Adventurer(val name:String,
                 val x:Int,
                 val y:Int,
                 val initialDirection:Cardinals.Cardinal,
                 val sequence:Array[Directions.Direction]) {

  var currentPosX:Int = x
  var currentPosY:Int = y
  var currentDirection:Cardinals.Cardinal = initialDirection
  var indexCurrentDirection:Int = 0
  var done = false
  var treasuresFound:Int=0

  def getNextDirection : Directions.Direction = {
    sequence(indexCurrentDirection)
  }

  def getNewPosition: (Int, Int) = {
    sequence(indexCurrentDirection) match {
      case Directions.Forward => computeForward()
      case _ => (currentPosX, currentPosY)
    }
  }

  def updateNewPosition(previousCell:Terrain, nextCell:Terrain) : Unit = {
    sequence(indexCurrentDirection) match {
      case Directions.Forward   => forward(previousCell, nextCell)
      case Directions.TurnLeft  => turnLeft()
      case Directions.TurnRight => turnRight()
    }
    updateNextDirection()
  }

  private def forward(previousCell:Terrain, nextCell:Terrain): Unit = {
    if(!nextCell.canCross){
      println(name + " can not cross " + nextCell)
      return
    }
    // Get new coordinates if adventurer advances
    val (newX, newY) = computeForward()
    // TODO Limit movement Carte bounds
    updateCurrentPos(newX, newY)
    // Give treasure to adventurer
    if(nextCell.hasTreasure){
      nextCell.accept(this)
    }
    // Previous cell is noticed to be unoccupied
    previousCell.clear()
  }

  private def turnLeft() : Unit = {
//    println("turnLeft " + currentDirection.id)
    // If current direction is North
    if( currentDirection.id == 0){
      // Take value West
      currentDirection = West
    } else {
      currentDirection = DIRECTION_ARRAY(currentDirection.id - 1)
    }
  }

  private def turnRight() : Unit = {
//    println("turnRight " + currentDirection.id)
    // If current direction is West
    if( currentDirection.id == (DIRECTION_ARRAY.length - 1)){
      // Take value North
      currentDirection = North
    } else {
      currentDirection = DIRECTION_ARRAY(currentDirection.id + 1)
    }
  }

  private def computeForward() : (Int, Int) = {
    currentDirection match {
      case North => (currentPosX, currentPosY-1)
      case East  => (currentPosX+1, currentPosY)
      case South => (currentPosX, currentPosY+1)
      case West  => (currentPosX-1, currentPosY)
    }
  }

  private def updateCurrentPos(newX: Int, newY: Int) : Unit = {
    currentPosX = newX
    currentPosY = newY
  }

  private def updateNextDirection() : Unit = {
    indexCurrentDirection += 1
    if(indexCurrentDirection == sequence.length){
      done = true
    }
  }

  def result() : String = {
    name + " - Last position is ("+currentPosX+","+currentPosY+") and has found " + treasuresFound
  }
  override def toString: String = {
    "[" + name + ", initial direction : " + initialDirection.toString + ", Sequence " + sequence.mkString("Array(", ", ", ")") + "]"
  }

}