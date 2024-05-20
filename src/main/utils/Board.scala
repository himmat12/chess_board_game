package main.utils

import main.models.Piece

import scala.collection.mutable.ArrayBuffer


/** Board object acts as global state of chess board and it's squares mapping (a1, a2, ...) along with the state of total number of pieces available in the board at the moment */
object Board {
  /**
   * board array stores the calculated value ('char' + Int) of each mapped squares position in the pieces
   * */
  var board: Array[Array[Piece]] = Array.ofDim[Piece](8, 8)

  /**
   * boardMap for moving pieces to squares in the board which stores the array of tuple which maps "a8" to its corresponding (x,y) coordinate like ("a8", 0, 0)
   * */
  var boardMap: Array[Array[String]] = Array.ofDim[String](8, 8)

  /** collection of total chess pieces in board */
  var totalPieces = ArrayBuffer[Piece]()

}
