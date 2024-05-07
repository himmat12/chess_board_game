package main.utils

import main.models.{Game, Piece}

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.*
import scala.util.matching.Regex.*

object InputValidator {

  /** read input string for position string like "a1", "b5" and so on and validates weather the user input is in the same format
   * which is first char in string should be character between 'a' to 'h' and second char in string should be Integers ranging from 1 to 8
   * */
  def isValidPoseStr(positionStr: String): Boolean = {
    val strFormat = "[a-h][1-8]".r
    val isMatch = strFormat.pattern.matcher(positionStr).matches()
    if (isMatch || positionStr == "switch")
      true
    else
      false
  }

  /** todo: function comment description and validation logic implementation */
  def isValidPieceStr(pieceStr: String, pieces: ArrayBuffer[Piece]): Boolean = {
    val filteredList = pieces.filter(e => e.value.toLowerCase == pieceStr.toLowerCase)
    if (filteredList.isEmpty)
      false
    else
      true
  }
}

