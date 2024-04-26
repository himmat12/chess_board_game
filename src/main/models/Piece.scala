package main.models

import main.models.Move

/**
 * @positionX is the X coordinate of piece position in board
 * @positionY is the Y coordinate of piece position in board
 * @rank is the rank hierarchy of piece
 * */
class Piece(var color: Color, var rank: Rank, var moves: Array[MoveOption], var positionX: Char, var positionY: Int) {

  /**
   * promote(piece, rank) function promotes the rank of the given chess piece from its current rank to new rank
   * chess piece cannot be promoted into king
   * */
  def promoteTo(newRank: Rank): Unit = {
    rank = newRank
  }
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
