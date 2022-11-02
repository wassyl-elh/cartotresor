package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity.{Adventurer, Mountain, Treasure}

class Carte(width:Int,
            height:Int,
            mountains:List[Mountain],
            treasures:List[Treasure],
            adventurers:List[Adventurer]
           ) {

  def process() : Unit = {
    val grid = Array.ofDim[2](width, height)

    // TODO

  }

  override def toString: String = {
    "Largeur : " + width + ", hauteur : " + height + "\nMontagnes : " + mountains.map(_.toString) + "\nTrésors : " + treasures.map(_.toString) + "\nAventuriers : " + adventurers.map(_.toString)
  }

}
