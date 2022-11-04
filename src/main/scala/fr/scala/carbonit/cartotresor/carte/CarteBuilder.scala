package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity._

import scala.collection.mutable.ListBuffer

class CarteBuilder(width:Int,
                   height:Int,
                   mountains: ListBuffer[Mountain] = ListBuffer(),
                   treasures: ListBuffer[Treasure] = ListBuffer(),
                   adventurers: ListBuffer[Adventurer] = ListBuffer()) {

  private val grid = initGrid()

  private def initGrid() = {
    val tmpGrid = Array.ofDim[Terrain](width, height)

    for(i<-0 until width; j<-0 until height)
    {
      if(tmpGrid(i)(j) == null){
        tmpGrid(i)(j) = Plains()
      }
    }

//    val entities = mountains ++ treasures
//    mountains.foreach(m => {
//      print("hey")
//      tmpGrid(m.x)(m.y) = m
//    })
//    treasures.foreach(t => tmpGrid(t.x)(t.y) = t)

//    for(i<-0 until width; j<-0 until height)
//    {
//      if(tmpGrid(i)(j) == null){
//        tmpGrid(i)(j) = Plains()
//      }
//    }

//    for(i<-0 until width; j<-0 until height)
//    {
//      print(i, j, tmpGrid(i)(j))
//    }

    tmpGrid
  }

  private def cellIsEmpty(x:Int, y:Int) : Boolean = {
    grid(x)(y).isInstanceOf[Plains]
  }

  def addMountain(x:Int, y:Int) : Unit = {
    val m = Mountain(x, y)
    if(cellIsEmpty(x,y)){
      grid(x)(y) = m
    }
    mountains += m
  }

  def addTreasure(x:Int, y:Int, nbTreasures:Int) : Unit = {
    val t = Treasure(x, y, nbTreasures)
    if(cellIsEmpty(x,y)){
      grid(x)(y) = t
    }
    treasures += Treasure(x, y, nbTreasures)
  }

  def addAdventurer(name:String, x:Int, y:Int, initialDirection:Cardinals.Cardinal, sequence:Array[Directions.Direction]) : Unit = {
    adventurers += new Adventurer(name, x, y, initialDirection, sequence)
  }

  def build() : Carte = {

//    for(i<-0 until width; j<-0 until height)
//    {
//      print(i, j, grid(i)(j))
//    }

    new Carte(width, height, grid, adventurers.toList)
  }

}
