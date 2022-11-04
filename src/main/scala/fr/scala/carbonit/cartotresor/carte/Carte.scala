package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity.{Adventurer, Terrain}

class Carte(width:Int,
            height:Int,
            grid: Array[Terrain],
            adventurers:List[Adventurer]
           ) {

  def process() : Unit = {






  }

  override def toString: String = {
    "Largeur : " + width + ", hauteur : " + height + "\nAventuriers : " + adventurers.map(_.toString)
  }

}
