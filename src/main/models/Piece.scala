package main.models

import main.models.Move

/**
 * @positionX is the X coordinate of piece position in board
 * @positionY is the Y coordinate of piece position in board
 * @rank is the rank hierarchy of piece
 * */
class Piece(var symbol: String, var positionValue: Int, var color: Color, var rank: Rank, var moves: Array[MoveOption], var positionX: Int, var positionY: Int) {

  /**
   * promote(piece, rank) function promotes the rank of the given chess piece from its current rank to new rank
   * chess piece cannot be promoted into king
   * */
  def promoteTo(newRank: Rank): Unit = {
    rank = newRank
  }

  /**
   * moves to the given (position) eg: "b4" coordinates cell position in the board,
   * captures any opponent pieces if its in its destination position,
   * or if the opponent piece is King then it checks the King
   * */
  def moveTo(position: String): Unit = {}


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

//val king = new Piece(Color.Black, Rank.King, Array(MoveOption.StraightLine), 'e', 1)
//val queen = new Piece(Color.Black, Rank.Queen, Array(MoveOption.StraightLine, MoveOption.Diagonal), 'd', 1)


/**
 * Enums values for chess piece ranks
 * */
enum Rank:
  case Pawn, Rook, Knight, Bishop, King, Queen


/**
 * Enums values for chess piece color
 * */
enum Color:
  case Black, White


/**
 * Enums values for chess piece move options
 * */
enum MoveOption:
  case Diagonal, StraightLine, L
