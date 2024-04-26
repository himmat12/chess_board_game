package main.models

import scala.Predef
import main.models.Piece


class Move {
  /**
   * withBrackets(value) takes string value and wraps it  inside bracket
   * */
  private def withBrackets(value: String): String = s"($value)"

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
  def front(): Unit = {}

  /**
   * move back in the board
   * */
  def back(): Unit = {}

  /**
   * move left in the board
   * */
  def left(): Unit = {}

  /**
   * move right in the board
   * */
  def right(): Unit = {}


  /** move n steps in straight line (horizontal/ vertical) */

  // move front n times in the board
  def front(n: Int): Unit = {
    //    rpt(n)(front())
  }

  // move back n times in the board
  def back(n: Int): Unit = {}

  // move left n times in the board
  def left(n: Int): Unit = {}

  // move right n times in the board
  def right(n: Int): Unit = {}


  /** move single step in diagonal line */

  //  moves up diagonally in left direction
  def leftUP(): Unit = {}

  //  moves down diagonally in left direction
  def leftDown(): Unit = {}

  //  moves up diagonally in right direction
  def rightUP(): Unit = {}

  //  moves down diagonally in right direction
  def rightDown(): Unit = {}


  /** move n steps in diagonal line */

  //  moves up n times diagonally in left direction
  def leftUP(n: Int): Unit = {}

  //  moves down n times diagonally in left direction
  def leftDown(n: Int): Unit = {}

  //  moves up n times diagonally in right direction
  def rightUP(n: Int): Unit = {}

  //  moves down n times diagonally in right direction
  def rightDown(n: Int): Unit = {}


  /**
   * moves to the given (x,y) coordinates cell position in the board,
   * captures any opponent pieces if its in its destination position,
   * or if the opponent piece is King then it checks the King
   * */
  def moveTo(x: Char, y: Int): Unit = {}

  /**
   * suggests all available moves options of selected chess piece by encasing the values of cells in board inside bracket "(a1)".
   * to encase cells value inside bracket you can use withBrackets(value) function
   */
  def suggestMove(x: Char, y: Int): Unit = {}


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


  /**
   * capture(rank) removes the opponent chess piece from the board and moves the attacking chess piece to captured chess piece position
   * except opponent's king for which it checks it
   * */
  def capture(rank: Rank): Unit = {
  }

  /**
   * check() function checks the opponent chess piece King when it is in attack range
   * */
  def check(): Unit = {}

}