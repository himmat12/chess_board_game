package main.utils

import main.models.{Color, Piece}

import scala.collection.mutable.ArrayBuffer

object MoveSuggestion {
  /**
   * suggestMovePawn() function suggests all legal moves for pawn in its current position in pieces
   * */
  def suggestMovePawn(piece: Piece, board: Array[Array[Piece]]): ArrayBuffer[(Int, Int)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY

    val suggestedMoves = ArrayBuffer[(Int, Int)]()

    /** initial pawn piece move condition, if the pawn has not been moved from it's initial position it can move 2 squares at a move */
    if ((piece.positionX, piece.positionY) == (piece.initialX, piece.initialY)) {
      if (piece.color == Color.White)
        suggestedMoves.addOne((positionX - 1, positionY))
        suggestedMoves.addOne((positionX - 2, positionY))
      else
        suggestedMoves.addOne((positionX + 1, positionY))
        suggestedMoves.addOne((positionX + 2, positionY))
    } else {
      if (piece.color == Color.White) {
        if (board(positionX - 1)(positionY).value == "" && positionX > 0)
          suggestedMoves.addOne((positionX - 1, positionY))
      } else {
        if (board(positionX + 1)(positionY).value == "" && positionX < 7)
          suggestedMoves.addOne((positionX + 1, positionY))
      }

      /** diagonal forward left (top & left square position from selected piece) */
      if (board(positionX - 1)(positionY - 1).color != piece.color && (positionX > 0 && positionY > 0))
        suggestedMoves.addOne((positionX - 1, positionY - 1))

      /** diagonal forward right (top & right square position from selected piece) */
      if (board(positionX - 1)(positionY + 1).color != piece.color && (positionX > 0 && positionY < 7))
        suggestedMoves.addOne((positionX - 1, positionY + 1))
    }
    suggestedMoves
  }
  //  def suggestMovePawn(piece: Piece, pieces: ArrayBuffer[Piece]): ArrayBuffer[(Int, Int)] = {
  //    var possibleMoves = ArrayBuffer[(Int, Int)]()
  //    var suggestedMoves = ArrayBuffer[(Int, Int)]()
  //
  //    // initial pawn piece move condition, if the pawn has not been moved from it's initial position it can move 2 squares at a move
  //    if (piece.initialX == piece.positionX && piece.initialY == piece.positionY) {
  //      if (piece.color == Color.White)
  //        possibleMoves.addOne((piece.positionX, piece.positionY + 1))
  //        possibleMoves.addOne((piece.positionX, piece.positionY + 2))
  //      else
  //        possibleMoves.addOne((piece.positionX, piece.positionY - 1))
  //        possibleMoves.addOne((piece.positionX, piece.positionY - 2))
  //    }
  //
  //    // diagonal move if only there are any opponent piece in that square
  //    if (piece.color == Color.White) {
  //      possibleMoves.addOne(piece.positionX, piece.positionY + 1)
  //      pieces.foreach(e => {
  //        /** todo: bug fix required */
  //        //        if (piece.positionX + 1 && piece.positionY - 1 && e.color != piece.color) {
  //        //          suggestedMoves.addOne((piece.positionX + 1, piece.positionY - 1))
  //        //        }
  //      })
  //    } else {
  //      possibleMoves.addOne(piece.positionX, piece.positionY - 1)
  //      pieces.foreach(e => {
  //        /** todo: bug fix required */
  //        //        if (piece.positionX - 1 && piece.positionY - 1 && e.color != piece.color) {
  //        //          suggestedMoves.addOne((piece.positionX + 1, piece.positionY - 1))
  //        //        }
  //      })
  //    }
  //
  //    pieces.foreach(e => {
  //      possibleMoves.foreach(move => {
  //        if ((e.positionX, e.positionY) != move) {
  //          suggestedMoves.addOne((e.positionX, e.positionY))
  //        }
  //        return suggestedMoves
  //      })
  //      return suggestedMoves
  //    })
  //
  //    suggestedMoves
  //  }
  //


  /**
   * suggestMovePawn() function suggests all legal moves for rook in its current position in pieces
   * */
  def suggestMoveRook(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for Knight in its current position in pieces
   * */
  def suggestMoveKnight(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for bishop in its current position in pieces
   * */
  def suggestMoveBishop(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for queen in its current position in pieces
   * */
  def suggestMoveQueen(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for king in its current position in pieces
   * */
  def suggestMoveKing(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }
}
