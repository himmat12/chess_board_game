package main.utils


import main.models.{Piece}

import main.enums.*
import scala.collection.mutable.ArrayBuffer
import scala.util.boundary
import main.utils.Board.*

object MoveSuggestionAttack {
  /** suggestMovePawn() function generates pawn attack range squares - useful for detecting legal moves for king and check */
  def suggestMovePawn(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** white piece diagonal move */
    if (piece.color == Color.White) {
      /** diagonal left-up (top & left square position from selected piece) */
      if (positionX > 0 && positionY > 0)
        suggestedMoves.addOne((positionX - 1, positionY - 1, false))

      /** diagonal right-up right (top & right square position from selected piece) */
      if (positionX > 0 && positionY < 7)
        suggestedMoves.addOne((positionX - 1, positionY + 1, false))
    }

    /** black piece diagonal move */
    if (piece.color == Color.Black) {
      /** diagonal left-down (bottom & left square position from selected piece) */
      if (positionX < 7 && positionY < 7)
        suggestedMoves.addOne((positionX + 1, positionY + 1, false))

      /** diagonal right-down (bottom & right square position from selected piece) */
      if (positionX < 7 && positionY > 0)
        suggestedMoves.addOne((positionX + 1, positionY - 1, false))
    }

    suggestedMoves
  }

  /**
   * suggestMoveRook() function suggests all moves for rook in its current position in pieces
   * */
  def suggestMoveRook(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** WHITE PIECE */

    /** front movement */
    if (positionX > 0 && piece.color == Color.White) {
      boundary {
        for (x <- (positionX - 1) to 0 by -1) {

          suggestedMoves.addOne((x, positionY, false))
          //          val nextSquare = board(x)(positionY)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((x, positionY, false))
          //          }
          //          if (nextSquare.color == Color.Black) {
          //            suggestedMoves.addOne((x, positionY, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.White)
          //            suggestedMoves.addOne((x, positionY, false))
          //            boundary.break()
        }
      }
    }

    /** right movement */
    if (positionY < 7 && piece.color == Color.White) {
      boundary {
        for (y <- (positionY + 1) to 7 by 1) {

          suggestedMoves.addOne((positionX, y, false))
          //          val nextSquare = board(positionX)(y)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((positionX, y, false))
          //          }
          //          if (nextSquare.color == Color.Black) {
          //            suggestedMoves.addOne((positionX, y, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.White)
          //            suggestedMoves.addOne((positionX, y, false))
          //            boundary.break()
        }
      }
    }

    /** back movement */
    if (positionX < 7 && piece.color == Color.White) {
      boundary {
        for (x <- (positionX + 1) to 7 by 1) {

          suggestedMoves.addOne((x, positionY, false))
          //          val nextSquare = board(x)(positionY)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((x, positionY, false))
          //          }
          //          if (nextSquare.color == Color.Black) {
          //            suggestedMoves.addOne((x, positionY, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.White)
          //            suggestedMoves.addOne((x, positionY, false))
          //            boundary.break()
        }
      }
    }

    /** left movement */
    if (positionY > 0 && piece.color == Color.White) {
      boundary {
        for (y <- (positionY - 1) to 0 by -1) {
          suggestedMoves.addOne((positionX, y, false))
          //          val nextSquare = board(positionX)(y)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((positionX, y, false))
          //          }
          //          if (nextSquare.color == Color.Black) {
          //            suggestedMoves.addOne((positionX, y, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.White)
          //            suggestedMoves.addOne((positionX, y, false))
          //            boundary.break()
        }
      }
    }


    /** BLACK PIECE */

    /** front movement */
    if (positionX > 0 && piece.color == Color.Black) {
      boundary {
        for (x <- (positionX - 1) to 0 by -1) {
          suggestedMoves.addOne((x, positionY, false))
          //          val nextSquare = board(x)(positionY)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((x, positionY, false))
          //          }
          //          if (nextSquare.color == Color.White) {
          //            suggestedMoves.addOne((x, positionY, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.Black)
          //            suggestedMoves.addOne((x, positionY, false))
          //            boundary.break()
        }
      }
    }

    /** right movement */
    if (positionY < 7 && piece.color == Color.Black) {
      boundary {
        for (y <- (positionY + 1) to 7 by 1) {
          suggestedMoves.addOne((positionX, y, false))
          //          val nextSquare = board(positionX)(y)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((positionX, y, false))
          //          }
          //          if (nextSquare.color == Color.White) {
          //            suggestedMoves.addOne((positionX, y, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.Black)
          //            suggestedMoves.addOne((positionX, y, false))
          //            boundary.break()
        }
      }
    }

    /** back movement */
    if (positionX < 7 && piece.color == Color.Black) {
      boundary {
        for (x <- (positionX + 1) to 7 by 1) {
          suggestedMoves.addOne((x, positionY, false))
          //          val nextSquare = board(x)(positionY)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((x, positionY, false))
          //          }
          //          if (nextSquare.color == Color.White) {
          //            suggestedMoves.addOne((x, positionY, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.Black)
          //            suggestedMoves.addOne((x, positionY, false))
          //            boundary.break()
        }
      }
    }

    /** left movement */
    if (positionY > 0 && piece.color == Color.Black) {
      boundary {
        for (y <- (positionY - 1) to 0 by -1) {
          suggestedMoves.addOne((positionX, y, false))
          //          val nextSquare = board(positionX)(y)
          //          if (nextSquare.color == Color.None) {
          //            suggestedMoves.addOne((positionX, y, false))
          //          }
          //          if (nextSquare.color == Color.White) {
          //            suggestedMoves.addOne((positionX, y, true))
          //            boundary.break()
          //          }
          //          if (nextSquare.color == Color.Black)
          //            suggestedMoves.addOne((positionX, y, false))
          //            boundary.break()
        }
      }
    }

    suggestedMoves
  }

  /**
   * suggestMoveKnight() function suggests all moves for Knight in its current position in pieces
   * */
  def suggestMoveKnight(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** white piece movement */
    if (piece.color == Color.White) {
      /** front movement */
      if (positionX > 1 && positionY > 0 && positionY < 7) {
        //    2 squares front 1 square left
        if (board(positionX - 2)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX - 2, positionY - 1, false))

        if (board(positionX - 2)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX - 2, positionY - 1, true))

        if (board(positionX - 2)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX - 2, positionY - 1, false))

        //    2 squares front 1 square right
        if (board(positionX - 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX - 2, positionY + 1, false))

        if (board(positionX - 2)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX - 2, positionY + 1, true))

        if (board(positionX - 2)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX - 2, positionY + 1, false))
      }

      /** right movement */
      if (positionY < 6 && positionX > 0 && positionX < 7) {
        //    2 squares right 1 square up
        if (board(positionX - 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY + 2, false))

        if (board(positionX - 1)(positionY + 2).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY + 2, true))

        if (board(positionX - 1)(positionY + 2).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY + 2, false))

        //    2 squares right 1 square down
        if (board(positionX + 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY + 2, false))

        if (board(positionX + 1)(positionY + 2).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY + 2, true))

        if (board(positionX + 1)(positionY + 2).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY + 2, false))
      }

      /** back movement */
      if (positionX < 6 && positionY > 0 && positionY < 7) {
        //    2 squares back 1 square left
        if (board(positionX + 2)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY - 1, false))

        if (board(positionX + 2)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX + 2, positionY - 1, true))

        if (board(positionX + 2)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX + 2, positionY - 1, false))

        //    2 squares back 1 square right
        if (board(positionX + 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY + 1, false))

        if (board(positionX + 2)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX + 2, positionY + 1, true))

        if (board(positionX + 2)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX + 2, positionY + 1, false))
      }

      /** left movement */
      if (positionY > 1 && positionX > 0 && positionX < 7) {
        //    2 squares left 1 square up
        if (board(positionX - 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY - 2, false))

        if (board(positionX - 1)(positionY - 2).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY - 2, true))

        if (board(positionX - 1)(positionY - 2).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY - 2, false))

        //    2 squares left 1 square down
        if (board(positionX + 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY - 2, false))

        if (board(positionX + 1)(positionY - 2).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY - 2, true))

        if (board(positionX + 1)(positionY - 2).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY - 2, false))
      }
    }


    /** black piece movement */
    if (piece.color == Color.Black) {
      /** front movement */
      if (positionX > 1 && positionY > 0 && positionY < 7) {
        //    2 squares front 1 square left
        if (board(positionX - 2)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX - 2, positionY - 1, false))

        if (board(positionX - 2)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX - 2, positionY - 1, true))

        if (board(positionX - 2)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX - 2, positionY - 1, false))

        //    2 squares front 1 square right
        if (board(positionX - 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX - 2, positionY + 1, false))

        if (board(positionX - 2)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX - 2, positionY + 1, true))

        if (board(positionX - 2)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX - 2, positionY + 1, false))
      }

      /** right movement */
      if (positionY < 6 && positionX > 0 && positionX < 7) {
        //    2 squares right 1 square up
        if (board(positionX - 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY + 2, false))

        if (board(positionX - 1)(positionY + 2).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY + 2, true))

        if (board(positionX - 1)(positionY + 2).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY + 2, false))

        //    2 squares right 1 square down
        if (board(positionX + 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY + 2, false))

        if (board(positionX + 1)(positionY + 2).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY + 2, true))

        if (board(positionX + 1)(positionY + 2).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY + 2, false))
      }

      /** back movement */
      if (positionX < 6 && positionY > 0 && positionY < 7) {
        //    2 squares back 1 square left
        if (board(positionX + 2)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY - 1, false))

        if (board(positionX + 2)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX + 2, positionY - 1, true))

        if (board(positionX + 2)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX + 2, positionY - 1, false))

        //    2 squares back 1 square right
        if (board(positionX + 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY + 1, false))

        if (board(positionX + 2)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX + 2, positionY + 1, true))

        if (board(positionX + 2)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX + 2, positionY + 1, false))
      }

      /** left movement */
      if (positionY > 1 && positionX > 0 && positionX < 7) {
        //    2 squares left 1 square up
        if (board(positionX - 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY - 2, false))

        if (board(positionX - 1)(positionY - 2).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY - 2, true))

        if (board(positionX - 1)(positionY - 2).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY - 2, false))

        //    2 squares left 1 square down
        if (board(positionX + 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY - 2, false))

        if (board(positionX + 1)(positionY - 2).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY - 2, true))

        if (board(positionX + 1)(positionY - 2).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY - 2, true))
      }
    }

    suggestedMoves
  }

  /**
   * suggestMoveBishop() function suggests all moves for bishop in its current position in pieces
   * */
  def suggestMoveBishop(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** white piece diagonal move */
    if (piece.color == Color.White) {
      /** diagonal left-up (top & left square position from selected piece) */
      boundary {
        var count = 1
        while (positionX > 0 && positionY > 0) {
          if (positionX - count >= 0 && positionY - count >= 0) {
            suggestedMoves.addOne((positionX - count, positionY - count, false))
            //            if (board(positionX - count)(positionY - count).color == Color.None)
            //              suggestedMoves.addOne((positionX - count, positionY - count, false))
            //
            //            if (board(positionX - count)(positionY - count).color == Color.Black)
            //              suggestedMoves.addOne((positionX - count, positionY - count, true))
            //              boundary.break()
            //
            //            if (board(positionX - count)(positionY - count).color == Color.White) {
            //              suggestedMoves.addOne((positionX - count, positionY - count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }

      /** diagonal right-up right (top & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX > 0 && positionY < 7) {
          if (positionX - count >= 0 && positionY + count <= 7) {
            suggestedMoves.addOne((positionX - count, positionY + count, false))
            //            if (board(positionX - count)(positionY + count).color == Color.None)
            //              suggestedMoves.addOne((positionX - count, positionY + count, false))
            //
            //            if (board(positionX - count)(positionY + count).color == Color.Black)
            //              suggestedMoves.addOne((positionX - count, positionY + count, true))
            //              boundary.break()
            //
            //            if (board(positionX - count)(positionY + count).color == Color.White) {
            //              suggestedMoves.addOne((positionX - count, positionY + count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }

      /** diagonal right-down (bottom & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY < 7) {
          if (positionX + count <= 7 && positionY + count <= 7) {
            suggestedMoves.addOne((positionX + count, positionY + count, false))
            //            if (board(positionX + count)(positionY + count).color == Color.None)
            //              suggestedMoves.addOne((positionX + count, positionY + count, false))
            //
            //            if (board(positionX + count)(positionY + count).color == Color.Black)
            //              suggestedMoves.addOne((positionX + count, positionY + count, true))
            //              boundary.break()
            //
            //            if (board(positionX + count)(positionY + count).color == Color.White) {
            //              suggestedMoves.addOne((positionX + count, positionY + count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }

      /** diagonal left-down (bottom & left square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY > 0) {
          if (positionX + count <= 7 && positionY - count >= 0) {
            suggestedMoves.addOne((positionX + count, positionY - count, false))
            //            if (board(positionX + count)(positionY - count).color == Color.None)
            //              suggestedMoves.addOne((positionX + count, positionY - count, false))
            //
            //            if (board(positionX + count)(positionY - count).color == Color.Black)
            //              suggestedMoves.addOne((positionX + count, positionY - count, true))
            //              boundary.break()
            //
            //            if (board(positionX + count)(positionY - count).color == Color.White) {
            //              suggestedMoves.addOne((positionX + count, positionY - count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }

    }

    /** black piece diagonal move */
    if (piece.color == Color.Black) {
      /** diagonal left-up (top & left square position from selected piece) */
      boundary {
        var count = 1
        while (positionX > 0 && positionY > 0) {
          if (positionX - count >= 0 && positionY - count >= 0) {
            suggestedMoves.addOne((positionX - count, positionY - count, false))
            //            if (board(positionX - count)(positionY - count).color == Color.None)
            //              suggestedMoves.addOne((positionX - count, positionY - count, false))
            //
            //            if (board(positionX - count)(positionY - count).color == Color.White)
            //              suggestedMoves.addOne((positionX - count, positionY - count, true))
            //              boundary.break()
            //
            //            if (board(positionX - count)(positionY - count).color == Color.Black) {
            //              suggestedMoves.addOne((positionX - count, positionY - count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }

      /** diagonal right-up right (top & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX > 0 && positionY < 7) {
          if (positionX - count >= 0 && positionY + count <= 7) {
            suggestedMoves.addOne((positionX - count, positionY + count, false))
            //            if (board(positionX - count)(positionY + count).color == Color.None)
            //              suggestedMoves.addOne((positionX - count, positionY + count, false))
            //
            //            if (board(positionX - count)(positionY + count).color == Color.White)
            //              suggestedMoves.addOne((positionX - count, positionY + count, true))
            //              boundary.break()
            //
            //            if (board(positionX - count)(positionY + count).color == Color.Black) {
            //              suggestedMoves.addOne((positionX - count, positionY + count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }

      /** diagonal right-down (bottom & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY < 7) {
          if (positionX + count <= 7 && positionY + count <= 7) {
            suggestedMoves.addOne((positionX + count, positionY + count, false))
            //            if (board(positionX + count)(positionY + count).color == Color.None)
            //              suggestedMoves.addOne((positionX + count, positionY + count, false))
            //
            //            if (board(positionX + count)(positionY + count).color == Color.White)
            //              suggestedMoves.addOne((positionX + count, positionY + count, true))
            //              boundary.break()
            //
            //            if (board(positionX + count)(positionY + count).color == Color.Black) {
            //              suggestedMoves.addOne((positionX + count, positionY + count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }

      /** diagonal left-down (bottom & left square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY > 0) {
          if (positionX + count <= 7 && positionY - count >= 0) {
            suggestedMoves.addOne((positionX + count, positionY - count, false))
            //            if (board(positionX + count)(positionY - count).color == Color.None)
            //              suggestedMoves.addOne((positionX + count, positionY - count, false))
            //
            //            if (board(positionX + count)(positionY - count).color == Color.White)
            //              suggestedMoves.addOne((positionX + count, positionY - count, true))
            //              boundary.break()
            //
            //            if (board(positionX + count)(positionY - count).color == Color.Black) {
            //              suggestedMoves.addOne((positionX + count, positionY - count, false))
            //              boundary.break()
            //            }
            count += 1
          } else {
            boundary.break()
          }
        }
      }
    }

    suggestedMoves
  }

  /**
   * suggestMoveQueen() function suggests all moves for queen in its current position in pieces
   * */
  def suggestMoveQueen(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val rookMoves = suggestMoveRook(piece)
    val bishopMoves = suggestMoveBishop(piece)

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = (rookMoves ++ bishopMoves).distinct

    suggestedMoves
  }

  /**
   * suggestMoveKing() function suggests all moves for king in its current position in pieces
   * */
  def suggestMoveKing(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()


    /** white piece movement */
    if (piece.color == Color.White) {
      /** front movement */
      if (positionX > 0) {
        if (board(positionX - 1)(positionY).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY, false))

        if (board(positionX - 1)(positionY).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY, true))

        if (board(positionX - 1)(positionY).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY, false))

      }

      /** right movement */
      if (positionY < 7) {
        if (board(positionX)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY + 1, false))

        if (board(positionX)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX, positionY + 1, true))

        if (board(positionX)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX, positionY + 1, false))
      }

      /** back movement */
      if (positionX < 7) {
        if (board(positionX + 1)(positionY).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY, false))

        if (board(positionX + 1)(positionY).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY, true))

        if (board(positionX + 1)(positionY).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY, false))

      }

      /** left movement */
      if (positionY > 0) {
        if (board(positionX)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY - 1, false))

        if (board(positionX)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX, positionY - 1, true))

        if (board(positionX)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX, positionY - 1, false))
      }
    }

    /** black piece movement */
    if (piece.color == Color.Black) {
      /** front movement */
      if (positionX > 0) {
        if (board(positionX - 1)(positionY).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY, false))

        if (board(positionX - 1)(positionY).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY, true))

        if (board(positionX - 1)(positionY).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY, false))
      }

      /** right movement */
      if (positionY < 7) {
        if (board(positionX)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY + 1, false))

        if (board(positionX)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX, positionY + 1, true))

        if (board(positionX)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX, positionY + 1, false))
      }

      /** back movement */
      if (positionX < 7) {
        if (board(positionX + 1)(positionY).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY, false))

        if (board(positionX + 1)(positionY).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY, true))

        if (board(positionX + 1)(positionY).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY, false))
      }

      /** left movement */
      if (positionY > 0) {
        if (board(positionX)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY - 1, false))

        if (board(positionX)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX, positionY - 1, true))

        if (board(positionX)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX, positionY - 1, false))
      }
    }

    suggestedMoves
  }

  /** generates attack moves for selected piece and returns the array of moves */
  def genAttkRangeMoves(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    var suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    if (piece.rank == Rank.Pawn)
      suggestedMoves = suggestMovePawn(piece)

    if (piece.rank == Rank.Rook)
      suggestedMoves = suggestMoveRook(piece)

    if (piece.rank == Rank.Knight)
      suggestedMoves = suggestMoveKnight(piece)

    if (piece.rank == Rank.Bishop)
      suggestedMoves = suggestMoveBishop(piece)

    if (piece.rank == Rank.Queen)
      suggestedMoves = suggestMoveQueen(piece)

    if (piece.rank == Rank.King)
      suggestedMoves = suggestMoveKing(piece)

    suggestedMoves
  }

}
