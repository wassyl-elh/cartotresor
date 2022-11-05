package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity.Cardinals._
import fr.scala.carbonit.cartotresor.entity.{Adventurer, Cardinals, Directions, Terrain}

class Carte(width:Int,
            height:Int,
            grid: Array[Array[Terrain]],
            adventurers:List[Adventurer]
           ) {

  val directionArray: Array[Cardinals.Value] = Array(North, East, South, West)

  def getNewPositionForAdventurer(a: Adventurer) : (Int, Int) = {
    a.sequence(a.indexCurrentMove) match {
      case Directions.Forward   => {
        a.currentDirection match {
          case North => (a.currentPosX, a.currentPosY-1)
          case East  => (a.currentPosX+1, a.currentPosY)
          case South => (a.currentPosX, a.currentPosY+1)
          case West  => (a.currentPosX-1, a.currentPosY)
        }
      }
      case Directions.TurnLeft  => {
        a.currentDirection = directionArray(a.currentDirection.id - 1)
        (a.currentPosX, a.currentPosY)
      }
      case Directions.TurnRight => {
        a.currentDirection = directionArray(a.currentDirection.id + 1)
        (a.currentPosX, a.currentPosY)
      }
    }
  }

  def process() : Unit = {
    var i = 0
    while(true){

      adventurers.foreach( a => {
        val (newX, newY) = getNewPositionForAdventurer(a)
        val cell = grid(newX)(newY)
        if( !cell.canCross ){
          return
        }
        a.currentPosX = newX
        a.currentPosY = newY
        if(cell.hasTreasure){
          cell.pickTreasure(a)
        }
      })

      i += 1
    }


  }

  override def toString: String = {
    "Largeur : " + width + ", hauteur : " + height + "\nAventuriers : " + adventurers.map(_.toString)
  }

}
