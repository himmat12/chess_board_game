package main.models


import main.enums.*
/**
 * @positionX is the X coordinate of piece position in board
 * @positionY is the Y coordinate of piece position in board
 * @rank is the rank hierarchy of piece
 * */
class Piece(var initialX: Int, var initialY: Int, var value: String, var color: Color, var rank: Rank, var positionX: Int, var positionY: Int) {

  /**
   * promote(piece, rank) function promotes the rank of the given chess piece from its current rank to new rank
   * chess piece cannot be promoted into king
   * */
//  def promoteTo(newRank: Rank): Unit = {
//    rank = newRank
//  }
}



