package main.utils

import main.models.Piece

import scala.collection.mutable.ArrayBuffer

object MoveSuggestion {

  /**
   * suggestMovePawn() function suggests all legal moves for pawn in its current position in board
   * */
  def suggestMovePawn(x: Int, y: Int, board: ArrayBuffer[Piece]): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for rook in its current position in board
   * */
  def suggestMoveRook(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for Knight in its current position in board
   * */
  def suggestMoveKnight(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for bishop in its current position in board
   * */
  def suggestMoveBishop(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for queen in its current position in board
   * */
  def suggestMoveQueen(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }

  /**
   * suggestMovePawn() function suggests all legal moves for king in its current position in board
   * */
  def suggestMoveKing(x: Int, y: Int): Array[(Int)] = {

    Array(0)
  }
}
