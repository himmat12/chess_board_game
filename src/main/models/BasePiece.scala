package main.models

import main.enums.*
import scala.collection.mutable.ArrayBuffer

trait BasePiece {
  var initialX: Int
  var initialY: Int
  var value: String
  var color: Color
  var rank: Rank
  var positionX: Int
  var positionY: Int

  def genMove(): ArrayBuffer[(Int, Int, Boolean)]
}
