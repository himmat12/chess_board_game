package main.utils

import main.models.{Color, Piece}

import scala.collection.mutable.ArrayBuffer

object MoveSuggestion {
  /**
   * suggestMovePawn() function suggests all legal moves for pawn in its current position in pieces
   * */
  def suggestMovePawn(piece: Piece, board: Array[Array[Piece]]): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** initial pawn piece move condition, if the pawn has not been moved from it's initial position it can move 2 squares at a move */
    if ((positionX, positionY) == (initialX, initialY)) {
      if (piece.color == Color.White) {
        var count = 1
        while (board(positionX - count)(positionY).value == "" && count < 3) {
          suggestedMoves.addOne((positionX - count, positionY, false))
          count += 1
        }
      } else {
        var count = 1
        while (board(positionX + count)(positionY).value == "" && count < 3) {
          suggestedMoves.addOne((positionX + count, positionY, false))
          count += 1
        }
      }
    }

    /** froward move from piece current position when it is not anymore in its default position */
    if ((positionX, positionY) != (initialX, initialY) && piece.color == Color.White && positionX > 0) {
      if (board(positionX - 1)(positionY).value == "")
        suggestedMoves.addOne((positionX - 1, positionY, false))
    }
    if ((positionX, positionY) != (initialX, initialY) && piece.color == Color.Black && positionX < 7) {
      if (board(positionX + 1)(positionY).value == "")
        suggestedMoves.addOne((positionX + 1, positionY, false))
    }

    /** white piece diagonal move */
    if (piece.color == Color.White) {
      /** diagonal left-up (top & left square position from selected piece) */
      if (positionX > 0 && positionY > 0)
        if (board(positionX - 1)(positionY - 1).color != Color.None && board(positionX - 1)(positionY - 1).color != piece.color)
          suggestedMoves.addOne((positionX - 1, positionY - 1, true))

      /** diagonal right-up right (top & right square position from selected piece) */
      if (positionX > 0 && positionY < 7)
        if (board(positionX - 1)(positionY + 1).color != Color.None && board(positionX - 1)(positionY + 1).color != piece.color)
          suggestedMoves.addOne((positionX - 1, positionY + 1, true))
    }

    /** black piece diagonal move */
    if (piece.color == Color.Black) {
      /** diagonal left-down (bottom & left square position from selected piece) */
      if (positionX < 7 && positionY < 7)
        if (board(positionX + 1)(positionY + 1).color != Color.None && board(positionX + 1)(positionY + 1).color != piece.color)
          suggestedMoves.addOne((positionX + 1, positionY + 1, true))

      /** diagonal right-down (bottom & right square position from selected piece) */
      if (positionX < 7 && positionY > 0)
        if (board(positionX + 1)(positionY - 1).color != Color.None && board(positionX + 1)(positionY - 1).color != piece.color)
          suggestedMoves.addOne((positionX + 1, positionY - 1, true))
    }

    suggestedMoves
  }

  /**
   * suggestMoveRook() function suggests all legal moves for rook in its current position in pieces
   * */
  def suggestMoveRook(x: Int, y: Int): Array[(Int, Int)] = {
    /** TODO: Rook move generation algorithm */
    Array((0, 0))
  }

  /**
   * suggestMoveKnight() function suggests all legal moves for Knight in its current position in pieces
   * */
  def suggestMoveKnight(x: Int, y: Int): Array[(Int, Int)] = {

    Array((0, 0))
  }

  /**
   * suggestMoveBishop() function suggests all legal moves for bishop in its current position in pieces
   * */
  def suggestMoveBishop(x: Int, y: Int): Array[(Int, Int)] = {

    Array((0, 0))
  }

  /**
   * suggestMoveQueen() function suggests all legal moves for queen in its current position in pieces
   * */
  def suggestMoveQueen(x: Int, y: Int): Array[(Int, Int)] = {

    Array((0, 0))
  }

  /**
   * suggestMoveKing() function suggests all legal moves for king in its current position in pieces
   * */
  def suggestMoveKing(x: Int, y: Int): Array[(Int, Int)] = {

    Array((0, 0))
  }
}
