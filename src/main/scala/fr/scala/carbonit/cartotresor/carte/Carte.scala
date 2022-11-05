package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity.{Adventurer, Cardinals, Directions, Terrain}

class Carte(width:Int,
            height:Int,
            grid: Array[Array[Terrain]],
            adventurers:List[Adventurer]
           ) {

  def process() : Unit = {
    while(adventurers.forall(a => !a.done)){
      adventurers
        .filter(a => !a.done)
        .foreach( a => {
          val previousCell = grid(a.currentPosX)(a.currentPosY)
          val (newX, newY) = a.getNewPosition
          val nextCell = grid(newX)(newY)
          a.updateNewPosition(previousCell, nextCell)
      })
    }
  }

  override def toString: String = {
    "Largeur : " + width + ", hauteur : " + height + "\nAventuriers : " + adventurers.map(_.toString)
  }

}
