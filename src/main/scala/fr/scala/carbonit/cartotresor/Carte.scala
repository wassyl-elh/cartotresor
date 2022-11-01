package fr.scala.carbonit.cartotresor

class Carte(width:Int,
            height:Int,
            mountains:List[Mountain],
            treasures:List[Treasure]) {

  override def toString: String = {
    "Largeur : " + width + ", hauteur : " + height + "\nMontagnes : " + mountains.map(_.toString) + "\nTrésors : " + treasures.map(_.toString)
  }
}
