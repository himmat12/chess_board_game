package main.models

import main.enums.*
import main.models.*

import scala.collection.mutable.ArrayBuffer

class Pawn(var initialX: Int, var initialY: Int, var value: String, var color: Color, var rank: Rank, var positionX: Int, var positionY: Int) extends BasePiece {
  

  override def genMove(): ArrayBuffer[(Int, Int, Boolean)] = ???
}
