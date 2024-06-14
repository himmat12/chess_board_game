package main.utils

import main.models.Piece
import main.enums.*
import scala.collection.mutable.ArrayBuffer
import scala.util.boundary
import main.utils.Board.*
import main.utils.Utils.*

object LegalMoveSuggestion {
  /**
   * suggestMovePawn() function suggests all legal moves for pawn in its current position in pieces
   * */
  def suggestMovePawn(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** legal moves validation */
    //    if (!CheckDetector.isLegalMove(piece)) {
    //    if (CheckDetector.canCaptureOpponent(piece)) {
    /** White piece */
    if (piece.color == Color.White) {
      // diagonal moves - attack moves
      /** diagonal left-up (top & left square position from selected piece) */
      if (positionX > 0 && positionY > 0)
        if (board(positionX - 1)(positionY - 1).color != Color.None && board(positionX - 1)(positionY - 1).color != piece.color)
          suggestedMoves.addOne((positionX - 1, positionY - 1, true))

      /** diagonal right-up right (top & right square position from selected piece) */
      if (positionX > 0 && positionY < 7)
        if (board(positionX - 1)(positionY + 1).color != Color.None && board(positionX - 1)(positionY + 1).color != piece.color)
          suggestedMoves.addOne((positionX - 1, positionY + 1, true))
    }

    /** Black piece */
    if (piece.color == Color.Black) {
      //diagonal moves - attack moves
      /** diagonal left-down (bottom & left square position from selected piece) */
      if (positionX < 7 && positionY < 7)
        if (board(positionX + 1)(positionY + 1).color != Color.None && board(positionX + 1)(positionY + 1).color != piece.color)
          suggestedMoves.addOne((positionX + 1, positionY + 1, true))

      /** diagonal right-down (bottom & right square position from selected piece) */
      if (positionX < 7 && positionY > 0)
        if (board(positionX + 1)(positionY - 1).color != Color.None && board(positionX + 1)(positionY - 1).color != piece.color)
          suggestedMoves.addOne((positionX + 1, positionY - 1, true))
    }
    //    }
    //    } else {
    //    /** White piece */
    //    if (piece.color == Color.White) {
    //      /** initial pawn piece move condition, if the pawn has not been moved from it's initial position it can move 2 squares at a move */
    //      if ((positionX, positionY) == (initialX, initialY)) {
    //        var count = 1
    //        while (board(positionX - count)(positionY).value == "" && count < 3) {
    //          suggestedMoves.addOne((positionX - count, positionY, false))
    //          count += 1
    //        }
    //      }
    //
    //      /** froward move from piece current position when it is not anymore in its default position */
    //      if ((positionX, positionY) != (initialX, initialY) && positionX > 0) {
    //        if (board(positionX - 1)(positionY).value == "")
    //          suggestedMoves.addOne((positionX - 1, positionY, false))
    //      }
    //
    //      // diagonal moves - attack moves
    //      /** diagonal left-up (top & left square position from selected piece) */
    //      if (positionX > 0 && positionY > 0)
    //        if (board(positionX - 1)(positionY - 1).color != Color.None && board(positionX - 1)(positionY - 1).color != piece.color)
    //          suggestedMoves.addOne((positionX - 1, positionY - 1, true))
    //
    //      /** diagonal right-up right (top & right square position from selected piece) */
    //      if (positionX > 0 && positionY < 7)
    //        if (board(positionX - 1)(positionY + 1).color != Color.None && board(positionX - 1)(positionY + 1).color != piece.color)
    //          suggestedMoves.addOne((positionX - 1, positionY + 1, true))
    //    }
    //
    //    /** Black piece */
    //    if (piece.color == Color.Black) {
    //      /** initial pawn piece move condition, if the pawn has not been moved from it's initial position it can move 2 squares at a move */
    //      if ((positionX, positionY) == (initialX, initialY)) {
    //        var count = 1
    //        while (board(positionX + count)(positionY).value == "" && count < 3) {
    //          suggestedMoves.addOne((positionX + count, positionY, false))
    //          count += 1
    //        }
    //      }
    //
    //      /** froward move from piece current position when it is not anymore in its default position */
    //      if ((positionX, positionY) != (initialX, initialY) && positionX < 7) {
    //        if (board(positionX + 1)(positionY).value == "")
    //          suggestedMoves.addOne((positionX + 1, positionY, false))
    //      }
    //
    //      //diagonal moves - attack moves
    //      /** diagonal left-down (bottom & left square position from selected piece) */
    //      if (positionX < 7 && positionY < 7)
    //        if (board(positionX + 1)(positionY + 1).color != Color.None && board(positionX + 1)(positionY + 1).color != piece.color)
    //          suggestedMoves.addOne((positionX + 1, positionY + 1, true))
    //
    //      /** diagonal right-down (bottom & right square position from selected piece) */
    //      if (positionX < 7 && positionY > 0)
    //        if (board(positionX + 1)(positionY - 1).color != Color.None && board(positionX + 1)(positionY - 1).color != piece.color)
    //          suggestedMoves.addOne((positionX + 1, positionY - 1, true))
    //    }
    //    }
    suggestedMoves
  }

  /**
   * suggestMoveRook() function suggests all legal moves for rook in its current position in pieces
   * */
  def suggestMoveRook(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val frontMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val backMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val leftMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val rightMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** WHITE PIECE */
    if (piece.color == Color.White) {

      /** front movement */
      if (positionX > 0) {
        boundary {
          for (x <- (positionX - 1) to 0 by -1) {
            val nextSquare = board(x)(positionY)

            if (nextSquare.color == Color.None)
              frontMoves.addOne((x, positionY, false))

            if (nextSquare.color == Color.Black)
              frontMoves.addOne((x, positionY, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()
          }
        }

      }

      /** right movement */
      if (positionY < 7) {
        boundary {
          for (y <- (positionY + 1) to 7 by 1) {
            val nextSquare = board(positionX)(y)

            if (nextSquare.color == Color.None)
              rightMoves.addOne((positionX, y, false))

            if (nextSquare.color == Color.Black)
              rightMoves.addOne((positionX, y, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()
          }
        }

      }

      /** back movement */
      if (positionX < 7) {
        boundary {
          for (x <- (positionX + 1) to 7 by 1) {
            val nextSquare = board(x)(positionY)

            if (nextSquare.color == Color.None)
              backMoves.addOne((x, positionY, false))

            if (nextSquare.color == Color.Black)
              backMoves.addOne((x, positionY, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()
          }
        }

      }

      /** left movement */
      if (positionY > 0) {
        boundary {
          for (y <- (positionY - 1) to 0 by -1) {
            val nextSquare = board(positionX)(y)

            if (nextSquare.color == Color.None)
              leftMoves.addOne((positionX, y, false))

            if (nextSquare.color == Color.Black)
              leftMoves.addOne((positionX, y, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()
          }
        }

      }
    }

    /** BLACK PIECE */
    if (piece.color == Color.Black) {

      /** front movement */
      if (positionX > 0) {
        boundary {
          for (x <- (positionX - 1) to 0 by -1) {
            val nextSquare = board(x)(positionY)

            if (nextSquare.color == Color.None)
              frontMoves.addOne((x, positionY, false))

            if (nextSquare.color == Color.White)
              frontMoves.addOne((x, positionY, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()

          }
        }

      }

      /** right movement */
      if (positionY < 7) {
        boundary {
          for (y <- (positionY + 1) to 7 by 1) {
            val nextSquare = board(positionX)(y)

            if (nextSquare.color == Color.None)
              rightMoves.addOne((positionX, y, false))

            if (nextSquare.color == Color.White)
              rightMoves.addOne((positionX, y, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()
          }
        }

      }

      /** back movement */
      if (positionX < 7) {
        boundary {
          for (x <- (positionX + 1) to 7 by 1) {
            val nextSquare = board(x)(positionY)

            if (nextSquare.color == Color.None)
              backMoves.addOne((x, positionY, false))

            if (nextSquare.color == Color.White)
              backMoves.addOne((x, positionY, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()
          }
        }

      }

      /** left movement */
      if (positionY > 0) {
        boundary {
          for (y <- (positionY - 1) to 0 by -1) {
            val nextSquare = board(positionX)(y)

            if (nextSquare.color == Color.None)
              leftMoves.addOne((positionX, y, false))

            if (nextSquare.color == Color.White)
              leftMoves.addOne((positionX, y, true))
              boundary.break()

            if (nextSquare.color == piece.color)
              boundary.break()
          }
        }

      }
    }

    /** legal moves validation */
    //    if (!CheckDetector.isLegalMove(piece)) {
    //      if (CheckDetector.canCaptureOpponent(piece)) {
    //        val movesList = Array[ArrayBuffer[(Int, Int, Boolean)]](frontMoves, rightMoves, backMoves, leftMoves)
    //        var suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()
    //
    //        boundary {
    //          movesList.foreach(moves => {
    //            moves.foreach(x => {
    //              if (x._3)
    //                suggestedMoves = moves
    //                boundary.break()
    //            })
    //          })
    //        }
    //        suggestedMoves
    //      } else {
    //        ArrayBuffer[(Int, Int, Boolean)]()
    //      }
    //    } else {
    //      val suggestedMoves = frontMoves ++ rightMoves ++ backMoves ++ leftMoves
    //      suggestedMoves
    //    }

    val suggestedMoves = frontMoves ++ rightMoves ++ backMoves ++ leftMoves
    suggestedMoves
  }

  /**
   * suggestMoveKnight() function suggests all legal moves for Knight in its current position in pieces
   * */
  def suggestMoveKnight(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val frontMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val rightMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val backMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val leftMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** white piece movement */
    if (piece.color == Color.White) {

      /** front movement */
      if (positionX > 1 && positionY > 0 && positionY < 7) {
        //    2 squares front 1 square left
        if (board(positionX - 2)(positionY - 1).color == Color.None)
          frontMoves.addOne((positionX - 2, positionY - 1, false))

        if (board(positionX - 2)(positionY - 1).color == Color.Black)
          frontMoves.addOne((positionX - 2, positionY - 1, true))

        //    2 squares front 1 square right
        if (board(positionX - 2)(positionY + 1).color == Color.None)
          frontMoves.addOne((positionX - 2, positionY + 1, false))

        if (board(positionX - 2)(positionY + 1).color == Color.Black)
          frontMoves.addOne((positionX - 2, positionY + 1, true))

      }

      /** right movement */
      if (positionY < 6 && positionX > 0 && positionX < 7) {
        //    2 squares right 1 square up
        if (board(positionX - 1)(positionY + 2).color == Color.None)
          rightMoves.addOne((positionX - 1, positionY + 2, false))

        if (board(positionX - 1)(positionY + 2).color == Color.Black)
          rightMoves.addOne((positionX - 1, positionY + 2, true))

        //    2 squares right 1 square down
        if (board(positionX + 1)(positionY + 2).color == Color.None)
          rightMoves.addOne((positionX + 1, positionY + 2, false))

        if (board(positionX + 1)(positionY + 2).color == Color.Black)
          rightMoves.addOne((positionX + 1, positionY + 2, true))

      }

      /** back movement */
      if (positionX < 6 && positionY > 0 && positionY < 7) {
        //    2 squares back 1 square left
        if (board(positionX + 2)(positionY - 1).color == Color.None)
          backMoves.addOne((positionX + 2, positionY - 1, false))

        if (board(positionX + 2)(positionY - 1).color == Color.Black)
          backMoves.addOne((positionX + 2, positionY - 1, true))

        //    2 squares back 1 square right
        if (board(positionX + 2)(positionY + 1).color == Color.None)
          backMoves.addOne((positionX + 2, positionY + 1, false))

        if (board(positionX + 2)(positionY + 1).color == Color.Black)
          backMoves.addOne((positionX + 2, positionY + 1, true))

      }

      /** left movement */
      if (positionY > 1 && positionX > 0 && positionX < 7) {
        //    2 squares left 1 square up
        if (board(positionX - 1)(positionY - 2).color == Color.None)
          leftMoves.addOne((positionX - 1, positionY - 2, false))

        if (board(positionX - 1)(positionY - 2).color == Color.Black)
          leftMoves.addOne((positionX - 1, positionY - 2, true))

        //    2 squares left 1 square down
        if (board(positionX + 1)(positionY - 2).color == Color.None)
          leftMoves.addOne((positionX + 1, positionY - 2, false))

        if (board(positionX + 1)(positionY - 2).color == Color.Black)
          leftMoves.addOne((positionX + 1, positionY - 2, true))

      }
    }


    /** black piece movement */
    if (piece.color == Color.Black) {
      /** front movement */
      if (positionX > 1 && positionY > 0 && positionY < 7) {
        //    2 squares front 1 square left
        if (board(positionX - 2)(positionY - 1).color == Color.None)
          frontMoves.addOne((positionX - 2, positionY - 1, false))

        if (board(positionX - 2)(positionY - 1).color == Color.White)
          frontMoves.addOne((positionX - 2, positionY - 1, true))

        //    2 squares front 1 square right
        if (board(positionX - 2)(positionY + 1).color == Color.None)
          frontMoves.addOne((positionX - 2, positionY + 1, false))

        if (board(positionX - 2)(positionY + 1).color == Color.White)
          frontMoves.addOne((positionX - 2, positionY + 1, true))

      }

      /** right movement */
      if (positionY < 6 && positionX > 0 && positionX < 7) {
        //    2 squares right 1 square up
        if (board(positionX - 1)(positionY + 2).color == Color.None)
          rightMoves.addOne((positionX - 1, positionY + 2, false))

        if (board(positionX - 1)(positionY + 2).color == Color.White)
          rightMoves.addOne((positionX - 1, positionY + 2, true))

        //    2 squares right 1 square down
        if (board(positionX + 1)(positionY + 2).color == Color.None)
          rightMoves.addOne((positionX + 1, positionY + 2, false))

        if (board(positionX + 1)(positionY + 2).color == Color.White)
          rightMoves.addOne((positionX + 1, positionY + 2, true))

      }

      /** back movement */
      if (positionX < 6 && positionY > 0 && positionY < 7) {
        //    2 squares back 1 square left
        if (board(positionX + 2)(positionY - 1).color == Color.None)
          backMoves.addOne((positionX + 2, positionY - 1, false))

        if (board(positionX + 2)(positionY - 1).color == Color.White)
          backMoves.addOne((positionX + 2, positionY - 1, true))

        //    2 squares back 1 square right
        if (board(positionX + 2)(positionY + 1).color == Color.None)
          backMoves.addOne((positionX + 2, positionY + 1, false))

        if (board(positionX + 2)(positionY + 1).color == Color.White)
          backMoves.addOne((positionX + 2, positionY + 1, true))

      }

      /** left movement */
      if (positionY > 1 && positionX > 0 && positionX < 7) {
        //    2 squares left 1 square up
        if (board(positionX - 1)(positionY - 2).color == Color.None)
          leftMoves.addOne((positionX - 1, positionY - 2, false))

        if (board(positionX - 1)(positionY - 2).color == Color.White)
          leftMoves.addOne((positionX - 1, positionY - 2, true))

        //    2 squares left 1 square down
        if (board(positionX + 1)(positionY - 2).color == Color.None)
          leftMoves.addOne((positionX + 1, positionY - 2, false))

        if (board(positionX + 1)(positionY - 2).color == Color.White)
          leftMoves.addOne((positionX + 1, positionY - 2, true))

      }
    }

    /** legal moves validation */
    //    if (!CheckDetector.isLegalMove(piece)) {
    //      if (CheckDetector.canCaptureOpponent(piece)) {
    //        val movesList = Array[ArrayBuffer[(Int, Int, Boolean)]](frontMoves, rightMoves, backMoves, leftMoves)
    //        var suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()
    //
    //        boundary {
    //          movesList.foreach(moves => {
    //            moves.foreach(x => {
    //              if (x._3)
    //                suggestedMoves.addOne(x)
    //                boundary.break()
    //            })
    //          })
    //        }
    //        suggestedMoves
    //      } else {
    //        ArrayBuffer[(Int, Int, Boolean)]()
    //      }
    //    } else {
    //      val suggestedMoves = frontMoves ++ rightMoves ++ backMoves ++ leftMoves
    //      suggestedMoves
    //    }

    val suggestedMoves = frontMoves ++ rightMoves ++ backMoves ++ leftMoves
    suggestedMoves
  }

  /**
   * suggestMoveBishop() function suggests all legal moves for bishop in its current position in pieces
   * */
  def suggestMoveBishop(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val leftUpMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val rightUpMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val rightDownMoves = ArrayBuffer[(Int, Int, Boolean)]()
    val leftDownMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /** white piece diagonal move */
    if (piece.color == Color.White) {

      /** diagonal left-up (top & left square position from selected piece) */
      boundary {
        var count = 1
        while (positionX > 0 && positionY > 0) {
          if (positionX - count >= 0 && positionY - count >= 0) {
            {
              if (board(positionX - count)(positionY - count).color == Color.None)
                leftUpMoves.addOne((positionX - count, positionY - count, false))

              if (board(positionX - count)(positionY - count).color == Color.Black)
                leftUpMoves.addOne((positionX - count, positionY - count, true))
                boundary.break()

              if (board(positionX - count)(positionY - count).color == Color.White) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
            boundary.break()
          }
        }
      }

      /** diagonal right-up right (top & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX > 0 && positionY < 7) {
          if (positionX - count >= 0 && positionY + count <= 7) {
            {
              if (board(positionX - count)(positionY + count).color == Color.None)
                rightUpMoves.addOne((positionX - count, positionY + count, false))

              if (board(positionX - count)(positionY + count).color == Color.Black)
                rightUpMoves.addOne((positionX + count, positionY + count, true))
                boundary.break()

              if (board(positionX - count)(positionY + count).color == Color.White) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
            boundary.break()
          }
        }
      }

      /** diagonal right-down (bottom & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY < 7) {
          if (positionX + count <= 7 && positionY + count <= 7) {
            {
              if (board(positionX + count)(positionY + count).color == Color.None)
                rightDownMoves.addOne((positionX + count, positionY + count, false))

              if (board(positionX + count)(positionY + count).color == Color.Black)
                rightDownMoves.addOne((positionX + count, positionY + count, true))
                boundary.break()

              if (board(positionX + count)(positionY + count).color == Color.White) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
            boundary.break()
          }
        }
      }

      /** diagonal left-down (bottom & left square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY > 0) {
          if (positionX + count <= 7 && positionY - count >= 0) {
            {
              if (board(positionX + count)(positionY - count).color == Color.None)
                leftDownMoves.addOne((positionX + count, positionY - count, false))

              if (board(positionX + count)(positionY - count).color == Color.Black)
                leftDownMoves.addOne((positionX + count, positionY - count, true))
                boundary.break()

              if (board(positionX + count)(positionY - count).color == Color.White) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
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
            {
              if (board(positionX - count)(positionY - count).color == Color.None)
                leftUpMoves.addOne((positionX - count, positionY - count, false))

              if (board(positionX - count)(positionY - count).color == Color.White)
                leftUpMoves.addOne((positionX - count, positionY - count, true))
                boundary.break()

              if (board(positionX - count)(positionY - count).color == Color.Black) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
            boundary.break()
          }
        }
      }

      /** diagonal right-up right (top & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX > 0 && positionY < 7) {
          if (positionX - count >= 0 && positionY + count <= 7) {
            {
              if (board(positionX - count)(positionY + count).color == Color.None)
                rightUpMoves.addOne((positionX - count, positionY + count, false))

              if (board(positionX - count)(positionY + count).color == Color.White)
                rightUpMoves.addOne((positionX - count, positionY + count, true))
                boundary.break()

              if (board(positionX - count)(positionY + count).color == Color.Black) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
            boundary.break()
          }
        }
      }

      /** diagonal right-down (bottom & right square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY < 7) {
          if (positionX + count <= 7 && positionY + count <= 7) {
            {
              if (board(positionX + count)(positionY + count).color == Color.None)
                rightDownMoves.addOne((positionX + count, positionY + count, false))

              if (board(positionX + count)(positionY + count).color == Color.White)
                rightDownMoves.addOne((positionX + count, positionY + count, true))
                boundary.break()

              if (board(positionX + count)(positionY + count).color == Color.Black) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
            boundary.break()
          }
        }
      }

      /** diagonal left-down (bottom & left square position from selected piece) */
      boundary {
        var count = 1
        while (positionX < 7 && positionY > 0) {
          if (positionX + count <= 7 && positionY - count >= 0) {
            {
              if (board(positionX + count)(positionY - count).color == Color.None)
                leftDownMoves.addOne((positionX + count, positionY - count, false))

              if (board(positionX + count)(positionY - count).color == Color.White)
                leftDownMoves.addOne((positionX + count, positionY - count, true))
                boundary.break()

              if (board(positionX + count)(positionY - count).color == Color.Black) {
                boundary.break()
              }
              count += 1
            }
          }
          else {
            boundary.break()
          }
        }
      }
    }

    /** legal moves validation */
    //    if (!CheckDetector.isLegalMove(piece)) {
    //      if (CheckDetector.canCaptureOpponent(piece)) {
    //        val movesList = Array[ArrayBuffer[(Int, Int, Boolean)]](leftUpMoves, rightUpMoves, rightDownMoves, leftDownMoves)
    //        var suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()
    //
    //        boundary {
    //          movesList.foreach(moves => {
    //            moves.foreach(x => {
    //              if (x._3)
    //                suggestedMoves = moves
    //                boundary.break()
    //            })
    //          })
    //        }
    //        suggestedMoves
    //      } else {
    //        ArrayBuffer[(Int, Int, Boolean)]()
    //      }
    //    } else {
    //      val suggestedMoves = leftUpMoves ++ rightUpMoves ++ rightDownMoves ++ leftDownMoves
    //      suggestedMoves
    //    }
    val suggestedMoves = leftUpMoves ++ rightUpMoves ++ rightDownMoves ++ leftDownMoves
    suggestedMoves
  }

  /**
   * suggestMoveQueen() function suggests all legal moves for queen in its current position in pieces
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
   * suggestMoveKing() function suggests all legal moves for king in its current position in pieces
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
      }

      /** right movement */
      if (positionY < 7) {
        if (board(positionX)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY + 1, false))

        if (board(positionX)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX, positionY + 1, true))
      }

      /** back movement */
      if (positionX < 7) {
        if (board(positionX + 1)(positionY).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY, false))

        if (board(positionX + 1)(positionY).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY, true))
      }

      /** left movement */
      if (positionY > 0) {
        if (board(positionX)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY - 1, false))

        if (board(positionX)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX, positionY - 1, true))
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
      }

      /** right movement */
      if (positionY < 7) {
        if (board(positionX)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY + 1, false))

        if (board(positionX)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX, positionY + 1, true))
      }

      /** back movement */
      if (positionX < 7) {
        if (board(positionX + 1)(positionY).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY, false))

        if (board(positionX + 1)(positionY).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY, true))
      }

      /** left movement */
      if (positionY > 0) {
        if (board(positionX)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX, positionY - 1, false))

        if (board(positionX)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX, positionY - 1, true))
      }
    }

    suggestedMoves
  }

  /** generates legal moves for selected piece and returns the array of moves */
  def getPiecesLegalMoves(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
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
      suggestedMoves = CheckDetector.getKingLegalMoves(piece)

    suggestedMoves
  }

}

