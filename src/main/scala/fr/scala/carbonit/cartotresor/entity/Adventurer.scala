package fr.scala.carbonit.cartotresor.entity

import fr.scala.carbonit.cartotresor.entity.Cardinals.{East, North, South, West}
import fr.scala.carbonit.cartotresor.entity.Terrain.DIRECTION_ARRAY

class Adventurer(val name:String,
                 val x:Int,
                 val y:Int,
                 val initialDirection:Cardinals.Cardinal,
                 val sequence:Array[Directions.Direction],
                // maxWidth and maxHeight are used to limit the adventurer's movements
                 val maxWidth:Int,
                 val maxHeight:Int) {

  var currentPosX:Int = x
  var currentPosY:Int = y
  var currentDirection:Cardinals.Cardinal = initialDirection

  // Index used to parse the sequence of instructions (AAGAD ..)
  var indexCurrentDirection:Int = 0

  // If the adventurer still has moves to make
  var done = false
  var treasuresFound:Int=0

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
    if(isOutOfBounds(newX, newY)){
      println(name + " is blocked by map boundaries : ("+newX+","+newY+")")
      return
    }
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

  private def isOutOfBounds(x:Int, y:Int) : Boolean = {
    x < 0 || x >= maxWidth || y < 0 || y >= maxHeight
  }

  override def toString: String = {
    "A - " + name + " - " + currentPosX + " - " + currentPosY + " - " + currentDirection + " - " + treasuresFound
  }

}