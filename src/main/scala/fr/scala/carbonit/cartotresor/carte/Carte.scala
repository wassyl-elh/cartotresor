package fr.scala.carbonit.cartotresor.carte

import fr.scala.carbonit.cartotresor.entity.{Adventurer, Cardinals, Directions, Mountain, Plains, Terrain}

import java.io.{BufferedWriter, File, FileWriter, IOException}
import scala.util.Properties

class Carte(width:Int,
            height:Int,
            grid: Array[Array[Terrain]],
            mountains: List[Mountain],
            treasures: List[Plains],
            adventurers:List[Adventurer]
           ) {

  // Computes all adventurers travel
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

  // Print adventurers result
  def results() : Unit = {
    adventurers
      .foreach( a => println(a.result()))
  }

  // Will write all results into a output file
  def writeInto(output:String) : Boolean = {
    val contentFile = new StringBuilder()
    contentFile.append(this.toString()).append(Properties.lineSeparator)
    mountains.foreach(m => contentFile.append(m.toString()).append(Properties.lineSeparator))
    treasures
      .filter(t => t.hasTreasure)
      .foreach(t => contentFile.append(t.toString()).append(Properties.lineSeparator))
    adventurers.foreach(a => contentFile.append(a).append(Properties.lineSeparator))
    try {
      val file = new File(output)
      val bw = new BufferedWriter(new FileWriter(file))
      bw.write(contentFile.result())
      bw.close()
    } catch {
      case e: IOException => println("Cannot write into output file : " + e.printStackTrace())
      case _: Throwable => println("Unknown exception thrown")
      return false
    }
    true
  }

  override def toString: String = {
    "C - "+ width + " - " + height
  }

}
