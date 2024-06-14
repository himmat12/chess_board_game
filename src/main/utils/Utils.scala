package main.utils

import main.models.Piece
import main.enums.*
import main.utils.Board.*
import scala.collection.mutable.ArrayBuffer

object Utils {

  /** placeholder for pieces square values */
  def placeholder(value: String): String = f"$value  "

  /**
   * withBrackets(value) takes string value and wraps it  inside bracket
   * */
  def withBrackets(value: String): String = s"($value)"

  /** this function sets default space in board by initialising dummy Piece object */
  def defaultPiece(posX: Int, posY: Int): Piece = {
    val piece = new Piece(-1, -1, "", Color.None, Rank.None, posX, posY)
    piece
  }

  /** helper function for printing board columns */
  def printColumns(index: Int, space: String, lineCount: Int): Unit = {
    /** printing columns */
    if (index == 7) {
      print("    ")
      for (i <- 1 to lineCount) print("-")
      println()
      print("     ")
      for (i <- 0 to 7)
        print(placeholder(s" ${(97 + i).toChar}" + space))
    }
  }

  /** helper function for printing board bottom rows */
  def printRows(index: Int): Unit = print(placeholder(s"${8 - index} |"))

  /** helper function which prints the current player turn */
  def printPlayerTurn(): Unit = {
    println()
    println(s"Player: [${PlayerTurn.get}]\nSelected: [${PlayerTurn.getSelectedPiece}]")
  }

  /** decrypts (converts) the board position string value "a8" and so on to (x,y) tuple (Int, Int) */
  def decryptFromPoseString(pos: String): (Int, Int) = {
    val x = 8 - s"${pos.reverse.head}".toInt
    val y = pos.head.toLower.toInt - 97
    (x, y)
  }

  /** encrypts (revert) the board position (x,y) coordinates value to "a8" string value */
  def encryptToPoseString(x: Int, y: Int): String = {
    val str1 = (97 + y).toChar
    val str2 = 8 - x
    s"$str1$str2"
  }

  /** markSuggestedMoves() is a helper function for generateMove() function, which marks the suggested moves squares in board
   *
   * "(P)":  all available squares
   * "[P]":  selected piece square
   * "{P}":  squares where opponent pieces are
   *
   * */
  def markSuggestedMoves(piece: Piece, moves: ArrayBuffer[(Int, Int, Boolean)]): Unit = {
    /** printing the chess pieces mapped position board with suggested move squares */
    var isPiecePosMarked = false
    for (x <- 0 to 7) {

      /** printing rows */
      printRows(x)
      for (y <- 0 to 7) {
        var flag = false
        var count = 0

        if (moves.isEmpty && !isPiecePosMarked && (x, y) == (piece.positionX, piece.positionY)) {
          print(placeholder(s"[${boardMap(x)(y)}]"))
          isPiecePosMarked = true
        }
        else {
          moves.foreach(e => {
            if ((x, y) == (piece.positionX, piece.positionY) && count == 0) {
              flag = true
              print(placeholder(s"[${boardMap(x)(y)}]"))
              count += 1
            }
            if ((x, y, false) == e) {
              flag = true
              print(placeholder(s"(${boardMap(x)(y)})"))
            }

            if ((x, y, true) == e) {
              flag = true
              print(placeholder(s"{${boardMap(x)(y)}}"))
            }
          })
          if (!flag)
            print(placeholder(s" ${boardMap(x)(y)} "))
        }
      }
      println()

      /** printing columns */
      printColumns(x, "  ", 47)
    }
    println()
  }

}
