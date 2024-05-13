package main.models

import scala.Predef
import main.models.Piece

import scala.collection.mutable.ArrayBuffer


class Move {

  /**
   * repeats the given command n times
   * */
  private def rpt(n: Int)(command: => Unit): Unit = {
    for (i <- 1 to n)
      command
  }

  /** single step straight line moves (horizontal/ vertical) */

  /**
   * move forward in the board
   * */
  def front(piece: Piece): (Int, Int, Boolean) = {
    var positionX = piece.positionX
    val positionY = piece.positionY

    if (positionX > 0)
      positionX -= 1

    (positionX, positionY, false)
  }

  /**
   * move back in the board
   * */
  def back(piece: Piece): (Int, Int, Boolean) = {
    var positionX = piece.positionX
    val positionY = piece.positionY

    if (positionX < 7)
      positionX += 1

    (positionX, positionY, false)
  }

  /**
   * move left in the board
   * */
  def left(piece: Piece): (Int, Int, Boolean) = {
    val positionX = piece.positionX
    var positionY = piece.positionY

    if (positionY > 0)
      positionY -= 1

    (positionX, positionY, false)
  }

  /**
   * move right in the board
   * */
  def right(piece: Piece): (Int, Int, Boolean) = {
    val positionX = piece.positionX
    var positionY = piece.positionY

    if (positionY > 0)
      positionY += 1

    (positionX, positionY, false)
  }

  /** move single step in diagonal line */

  //  moves up diagonally in left direction
  def leftUP(piece: Piece): (Int, Int, Boolean) = {
    var positionX = piece.positionX
    var positionY = piece.positionY

    if (positionX > 0 && positionY > 0)
      positionX -= 1
      positionY -= 1

    (positionX, positionY, false)
  }

  //  moves down diagonally in left direction
  def leftDown(piece: Piece): (Int, Int, Boolean) = {
    var positionX = piece.positionX
    var positionY = piece.positionY

    if (positionX < 0 && positionY > 0)
      positionX += 1
      positionY -= 1

    (positionX, positionY, false)
  }

  //  moves up diagonally in right direction
  def rightUP(piece: Piece): (Int, Int, Boolean) = {
    var positionX = piece.positionX
    var positionY = piece.positionY

    if (positionX > 0 && positionY < 0)
      positionX -= 1
      positionY += 1

    (positionX, positionY, false)
  }

  //  moves down diagonally in right direction
  def rightDown(piece: Piece): (Int, Int, Boolean) = {
    var positionX = piece.positionX
    var positionY = piece.positionY

    if (positionX < 7 && positionY < 7)
      positionX += 1
      positionY += 1

    (positionX, positionY, false)
  }
  
  /**
   * suggest straight line moves of the chess piece from its current position
   * */
  def suggestStraightLineMove(x: Char, y: Int): (Char, Int) = ('a', 1)

  /**
   * suggest a diagonal move of the chess piece from its current position (for "pawn" piece)
   * */
  def suggestDiagonalMove(x: Char, y: Int): (Char, Int) = ('a', 1)

  /**
   * suggest diagonal moves of the chess piece from its current position
   * */
  def suggestDiagonalMoves(x: Char, y: Int): Array[(Char, Int)] = {
    Array(('a', 1))
  }

  /**
   * isLegalMove(x,y) function takes X and Y coordinate of piece's current position
   * and checks weather the next move the piece is about make is legal or not and returns true or false accordingly
   * */
  def isLegalMove(x: Char, y: Int): Boolean = false


}
