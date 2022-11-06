package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity._

import scala.collection.mutable.ListBuffer

// Object dedicated to build Carte objects
class CarteBuilder(width:Int,
                   height:Int,
                   mountains: ListBuffer[Mountain] = ListBuffer(),
                   treasures: ListBuffer[Plains] = ListBuffer(),
                   adventurers: ListBuffer[Adventurer] = ListBuffer()) {

  // Preparing grid that will be passed to Carte instance
  private val grid = initGrid()

  // Creates the grid and sets all cells to a Plains instance with no treasure.
  private def initGrid() : Array[Array[Terrain]] = {
    val tmpGrid = Array.ofDim[Terrain](width, height)

    for(i<-0 until width; j<-0 until height)
    {
      if(tmpGrid(i)(j) == null){
        tmpGrid(i)(j) = Plains(i, j, 0)
      }
    }

    tmpGrid
  }

  // Returns true if cell is a Plains with no treasure
  private def cellIsEmpty(x:Int, y:Int) : Boolean = {
    grid(x)(y).isInstanceOf[Plains] && !grid(x)(y).hasTreasure
  }

  def addMountain(x:Int, y:Int) : Unit = {
    val m = Mountain(x, y)
    if(cellIsEmpty(x,y)){
      grid(x)(y) = m
    }
    mountains += m
  }

  def addTreasure(x:Int, y:Int, nbTreasures:Int) : Unit = {
    val t = Plains(x, y, nbTreasures)
    if(cellIsEmpty(x,y)){
      grid(x)(y) = t
    }
    treasures += t
  }

  def addAdventurer(name:String, x:Int, y:Int, initialDirection:Cardinals.Cardinal, sequence:Array[Directions.Direction]) : Unit = {
    adventurers += new Adventurer(name, x, y, initialDirection, sequence, width, height)
  }

  // Finally builds the Carte object. Passes all informations to it, including the grid.
  def build() : Carte = {
    new Carte(width, height, grid, mountains.toList, treasures.toList, adventurers.toList)
  }

}
