package main.utils

import main.models.{Color, Piece, Rank}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object GameBuilder {

  /** helper function which generated random position value for king and queen */
  private def genRandKingQueenPos(): (Int, Int) = {
    var randKingPos = Random.between(3, 5)
    var randQueenPos = Random.between(3, 5)

    while (randKingPos == randQueenPos)
      randKingPos = Random.between(3, 5)
      randQueenPos = Random.between(3, 5)

    (randKingPos, randQueenPos)
  }

  /**
   * setup default chess pieces in the board
   * */
  def initialiseDefaultSetup(): ArrayBuffer[Piece] = {
    val pieces = ArrayBuffer[Piece]()
    val randPos = genRandKingQueenPos()

    /**
     * setting the black pieces default position in board
     * */
    for (y <- 0 to 7)
      pieces.addOne(new Piece(1, y, "BP" + y, Color.Black, Rank.Pawn, 1, y))

    for (y <- 0 to 7 by 7)
      pieces.addOne(new Piece(0, y, "BR" + y, Color.Black, Rank.Rook, 0, y))

    for (y <- 1 to 6 by 5)
      pieces.addOne(new Piece(0, y, "BN" + y, Color.Black, Rank.Knight, 0, y))

    for (y <- 2 to 5 by 3)
      pieces.addOne(new Piece(0, y, "BB" + y, Color.Black, Rank.Bishop, 0, y))

    pieces.addOne(new Piece(0, randPos._2, s"BQ${randPos._2}", Color.Black, Rank.Queen, 0, randPos._2))

    pieces.addOne(new Piece(0, randPos._1, s"BK${randPos._1}", Color.Black, Rank.King, 0, randPos._1))

    /**
     * setting the white pieces default position in board
     * */
    for (y <- 0 to 7)
      pieces.addOne(new Piece(6, y, "WP" + y, Color.White, Rank.Pawn, 6, y))

    for (y <- 0 to 7 by 7)
      pieces.addOne(new Piece(7, y, "WR" + y, Color.White, Rank.Rook, 7, y))

    for (y <- 1 to 6 by 5)
      pieces.addOne(new Piece(7, y, "WN" + y, Color.White, Rank.Knight, 7, y))

    for (y <- 2 to 5 by 3)
      pieces.addOne(new Piece(7, y, "WB" + y, Color.White, Rank.Bishop, 7, y))

    pieces.addOne(new Piece(7, randPos._2, s"WQ${randPos._2}", Color.White, Rank.Queen, 7, randPos._2))

    pieces.addOne(new Piece(7, randPos._1, s"WK${randPos._1}", Color.White, Rank.King, 7, randPos._1))

    pieces
  }

  def initialisePawnSetup(): ArrayBuffer[Piece] = {
    val pieces = ArrayBuffer[Piece]()

    /**
     * setting the black pieces position in board
     * */
    pieces.addOne(new Piece(1, 3, "BP3", Color.Black, Rank.Pawn, 1, 3))
    pieces.addOne(new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 2, 5))
    pieces.addOne(new Piece(1, 7, "BP7", Color.Black, Rank.Pawn, 5, 7))
    pieces.addOne(new Piece(1, 6, "BP6", Color.Black, Rank.Pawn, 5, 6))

    /**
     * setting the white pieces position in board
     * */
    pieces.addOne(new Piece(6, 2, "WP2", Color.White, Rank.Pawn, 2, 2))
    pieces.addOne(new Piece(6, 1, "WP1", Color.White, Rank.Pawn, 1, 2))
    pieces.addOne(new Piece(6, 6, "WP6", Color.White, Rank.Pawn, 6, 6))
  }

  def initialiseRookSetup(): ArrayBuffer[Piece] = {
    val pieces = ArrayBuffer[Piece]()

    /**
     * setting the black pieces position in pieces
     * */
    pieces.addOne(new Piece(0, 0, "BR0", Color.Black, Rank.Rook, 0, 0))
    pieces.addOne(new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 2, 5))
    pieces.addOne(new Piece(1, 7, "BP7", Color.Black, Rank.Pawn, 5, 7))
    pieces.addOne(new Piece(1, 6, "BP6", Color.Black, Rank.Pawn, 5, 6))

    /**
     * setting the white pieces position in pieces
     * */
    pieces.addOne(new Piece(7, 7, "WR7", Color.White, Rank.Rook, 7, 7))
    pieces.addOne(new Piece(7, 0, "WR0", Color.White, Rank.Rook, 3, 4))
    pieces.addOne(new Piece(6, 1, "WP1", Color.White, Rank.Pawn, 1, 2))
    pieces.addOne(new Piece(6, 6, "WP6", Color.White, Rank.Pawn, 6, 6))

    pieces
  }

  def initialiseKnightSetup(): ArrayBuffer[Piece] = {
    val pieces = ArrayBuffer[Piece]()

    /**
     * setting the black pieces position in board
     * */
    pieces.addOne(new Piece(0, 1, "BN1", Color.Black, Rank.Knight, 0, 1))
    pieces.addOne(new Piece(0, 6, "BN6", Color.Black, Rank.Knight, 0, 6))
    pieces.addOne(new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 2, 5))
    pieces.addOne(new Piece(1, 7, "BP7", Color.Black, Rank.Pawn, 5, 7))
    pieces.addOne(new Piece(1, 6, "BP6", Color.Black, Rank.Pawn, 5, 6))

    /**
     * setting the white pieces position in board
     * */
    pieces.addOne(new Piece(7, 1, "WN1", Color.White, Rank.Knight, 4, 4))
    pieces.addOne(new Piece(7, 6, "WN6", Color.White, Rank.Knight, 7, 6))
    pieces.addOne(new Piece(6, 1, "WP1", Color.White, Rank.Pawn, 1, 2))
    pieces.addOne(new Piece(6, 2, "WP2", Color.White, Rank.Pawn, 5, 2))
    pieces.addOne(new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 6, 3))
    pieces.addOne(new Piece(6, 6, "WP6", Color.White, Rank.Pawn, 6, 6))

    pieces
  }

  def initialiseBishopSetup(): ArrayBuffer[Piece] = {
    val pieces = ArrayBuffer[Piece]()

    /**
     * setting the black pieces position in board
     * */
    pieces.addOne(new Piece(0, 2, "BB2", Color.Black, Rank.Bishop, 0, 2))
    pieces.addOne(new Piece(0, 6, "BN6", Color.Black, Rank.Knight, 0, 6))
    pieces.addOne(new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 2, 5))
    pieces.addOne(new Piece(1, 7, "BP7", Color.Black, Rank.Pawn, 5, 7))
    pieces.addOne(new Piece(1, 6, "BP6", Color.Black, Rank.Pawn, 5, 6))

    /**
     * setting the white pieces position in board
     * */
    pieces.addOne(new Piece(7, 2, "WB2", Color.White, Rank.Bishop, 4, 4))
    pieces.addOne(new Piece(7, 6, "WN6", Color.White, Rank.Knight, 7, 6))
    pieces.addOne(new Piece(7, 5, "WB5", Color.White, Rank.Bishop, 1, 2))
    pieces.addOne(new Piece(6, 2, "WP2", Color.White, Rank.Pawn, 5, 2))
    pieces.addOne(new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 6, 3))
    pieces.addOne(new Piece(6, 6, "WP6", Color.White, Rank.Pawn, 6, 6))

    pieces
  }

  def initialiseQueenSetup(): ArrayBuffer[Piece] = {
    val pieces = ArrayBuffer[Piece]()

    /**
     * setting the black pieces position in board
     * */
    pieces.addOne(new Piece(0, 4, "BQ4", Color.Black, Rank.Queen, 0, 4))
    pieces.addOne(new Piece(0, 6, "BN6", Color.Black, Rank.Knight, 0, 6))
    pieces.addOne(new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 2, 5))
    pieces.addOne(new Piece(1, 7, "BP7", Color.Black, Rank.Pawn, 5, 7))
    pieces.addOne(new Piece(1, 6, "BP6", Color.Black, Rank.Pawn, 5, 6))

    /**
     * setting the white pieces position in board
     * */
    pieces.addOne(new Piece(7, 4, "WQ4", Color.White, Rank.Queen, 4, 4))
    pieces.addOne(new Piece(7, 6, "WN6", Color.White, Rank.Knight, 7, 6))
    pieces.addOne(new Piece(7, 5, "WB5", Color.White, Rank.Bishop, 1, 2))
    pieces.addOne(new Piece(6, 2, "WP2", Color.White, Rank.Pawn, 5, 2))
    pieces.addOne(new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 6, 3))
    pieces.addOne(new Piece(6, 6, "WP6", Color.White, Rank.Pawn, 6, 6))

    pieces
  }

  def initialiseKingSetup(): ArrayBuffer[Piece] = {
    val pieces = ArrayBuffer[Piece]()

    /**
     * setting the black pieces position in board
     * */
    pieces.addOne(new Piece(0, 3, "BK3", Color.Black, Rank.King, 1, 2))
    pieces.addOne(new Piece(1, 2, "BP2", Color.Black, Rank.Pawn, 4, 2))
    pieces.addOne(new Piece(1, 4, "BP4", Color.Black, Rank.Pawn, 4, 4))
    pieces.addOne(new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 3, 5))

    /**
     * setting the white pieces position in board
     * */
    pieces.addOne(new Piece(7, 4, "WQ4", Color.White, Rank.Queen, 0, 4))
    pieces.addOne(new Piece(7, 3, "WK3", Color.White, Rank.King, 5, 4))
    pieces.addOne(new Piece(7, 2, "WB2", Color.White, Rank.Bishop, 2, 0))
    pieces.addOne(new Piece(6, 1, "WP1", Color.White, Rank.Pawn, 1, 1))

    pieces
  }

}
