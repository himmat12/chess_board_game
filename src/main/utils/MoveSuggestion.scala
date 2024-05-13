package main.utils

import main.models.{Color, Piece}

import scala.collection.mutable.ArrayBuffer
import scala.util.boundary

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
  def suggestMoveRook(piece: Piece, board: Array[Array[Piece]]): ArrayBuffer[(Int, Int, Boolean)] = {
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
          val nextSquare = board(x)(positionY)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((x, positionY, false))
          }
          if (nextSquare.color == Color.Black) {
            suggestedMoves.addOne((x, positionY, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }

    /** right movement */
    if (positionY < 7 && piece.color == Color.White) {
      boundary {
        for (y <- (positionY + 1) to 7 by 1) {
          val nextSquare = board(positionX)(y)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((positionX, y, false))
          }
          if (nextSquare.color == Color.Black) {
            suggestedMoves.addOne((positionX, y, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }

    /** back movement */
    if (positionX < 7 && piece.color == Color.White) {
      boundary {
        for (x <- (positionX + 1) to 7 by 1) {
          val nextSquare = board(x)(positionY)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((x, positionY, false))
          }
          if (nextSquare.color == Color.Black) {
            suggestedMoves.addOne((x, positionY, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }

    /** left movement */
    if (positionY > 0 && piece.color == Color.White) {
      boundary {
        for (y <- (positionY - 1) to 0 by -1) {
          val nextSquare = board(positionX)(y)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((positionX, y, false))
          }
          if (nextSquare.color == Color.Black) {
            suggestedMoves.addOne((positionX, y, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }


    /** BLACK PIECE */

    /** front movement */
    if (positionX > 0 && piece.color == Color.Black) {
      boundary {
        for (x <- (positionX - 1) to 0 by -1) {
          val nextSquare = board(x)(positionY)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((x, positionY, false))
          }
          if (nextSquare.color == Color.White) {
            suggestedMoves.addOne((x, positionY, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }

    /** right movement */
    if (positionY < 7 && piece.color == Color.Black) {
      boundary {
        for (y <- (positionY + 1) to 7 by 1) {
          val nextSquare = board(positionX)(y)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((positionX, y, false))
          }
          if (nextSquare.color == Color.White) {
            suggestedMoves.addOne((positionX, y, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }

    /** back movement */
    if (positionX < 7 && piece.color == Color.Black) {
      boundary {
        for (x <- (positionX + 1) to 7 by 1) {
          val nextSquare = board(x)(positionY)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((x, positionY, false))
          }
          if (nextSquare.color == Color.White) {
            suggestedMoves.addOne((x, positionY, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }

    /** left movement */
    if (positionY > 0 && piece.color == Color.Black) {
      boundary {
        for (y <- (positionY - 1) to 0 by -1) {
          val nextSquare = board(positionX)(y)
          if (nextSquare.color == Color.None) {
            suggestedMoves.addOne((positionX, y, false))
          }
          if (nextSquare.color == Color.White) {
            suggestedMoves.addOne((positionX, y, true))
            boundary.break()
          }
          if (nextSquare.color == piece.color)
            boundary.break()
        }
      }
    }

    suggestedMoves
  }

  /**
   * suggestMoveKnight() function suggests all legal moves for Knight in its current position in pieces
   * */
  def suggestMoveKnight(piece: Piece, board: Array[Array[Piece]]): ArrayBuffer[(Int, Int, Boolean)] = {
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

        //    2 squares front 1 square right
        if (board(positionX - 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX - 2, positionY + 1, false))

        if (board(positionX - 2)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX - 2, positionY + 1, true))
      }

      /** right movement */
      if (positionY < 6 && positionX > 0 && positionX < 7) {
        //    2 squares right 1 square up
        if (board(positionX - 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY + 2, false))

        if (board(positionX - 1)(positionY + 2).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY + 2, true))

        //    2 squares right 1 square down
        if (board(positionX + 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY + 2, false))

        if (board(positionX + 1)(positionY + 2).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY + 2, true))
      }

      /** back movement */
      if (positionX < 6 && positionY > 0 && positionY < 7) {
        //    2 squares back 1 square left
        if (board(positionX + 2)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY - 1, false))

        if (board(positionX + 2)(positionY - 1).color == Color.Black)
          suggestedMoves.addOne((positionX + 2, positionY - 1, true))

        //    2 squares back 1 square right
        if (board(positionX + 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY + 1, false))

        if (board(positionX + 2)(positionY + 1).color == Color.Black)
          suggestedMoves.addOne((positionX + 2, positionY + 1, true))
      }

      /** left movement */
      if (positionY > 1 && positionX > 0 && positionX < 7) {
        //    2 squares left 1 square up
        if (board(positionX - 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY - 2, false))

        if (board(positionX - 1)(positionY - 2).color == Color.Black)
          suggestedMoves.addOne((positionX - 1, positionY - 2, true))

        //    2 squares left 1 square down
        if (board(positionX + 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY - 2, false))

        if (board(positionX + 1)(positionY - 2).color == Color.Black)
          suggestedMoves.addOne((positionX + 1, positionY - 2, true))
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

        //    2 squares front 1 square right
        if (board(positionX - 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX - 2, positionY + 1, false))

        if (board(positionX - 2)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX - 2, positionY + 1, true))
      }

      /** right movement */
      if (positionY < 6 && positionX > 0 && positionX < 7) {
        //    2 squares right 1 square up
        if (board(positionX - 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY + 2, false))

        if (board(positionX - 1)(positionY + 2).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY + 2, true))

        //    2 squares right 1 square down
        if (board(positionX + 1)(positionY + 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY + 2, false))

        if (board(positionX + 1)(positionY + 2).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY + 2, true))
      }

      /** back movement */
      if (positionX < 6 && positionY > 0 && positionY < 7) {
        //    2 squares back 1 square left
        if (board(positionX + 2)(positionY - 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY - 1, false))

        if (board(positionX + 2)(positionY - 1).color == Color.White)
          suggestedMoves.addOne((positionX + 2, positionY - 1, true))

        //    2 squares back 1 square right
        if (board(positionX + 2)(positionY + 1).color == Color.None)
          suggestedMoves.addOne((positionX + 2, positionY + 1, false))

        if (board(positionX + 2)(positionY + 1).color == Color.White)
          suggestedMoves.addOne((positionX + 2, positionY + 1, true))
      }

      /** left movement */
      if (positionY > 1 && positionX > 0 && positionX < 7) {
        //    2 squares left 1 square up
        if (board(positionX - 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX - 1, positionY - 2, false))

        if (board(positionX - 1)(positionY - 2).color == Color.White)
          suggestedMoves.addOne((positionX - 1, positionY - 2, true))

        //    2 squares left 1 square down
        if (board(positionX + 1)(positionY - 2).color == Color.None)
          suggestedMoves.addOne((positionX + 1, positionY - 2, false))

        if (board(positionX + 1)(positionY - 2).color == Color.White)
          suggestedMoves.addOne((positionX + 1, positionY - 2, true))
      }
    }

    suggestedMoves
  }

  /**
   * todo: suggestMoveBishop() function suggests all legal moves for bishop in its current position in pieces
   * */
  def suggestMoveBishop(piece: Piece, board: Array[Array[Piece]]): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    suggestedMoves
  }

  /**
   * todo: suggestMoveQueen() function suggests all legal moves for queen in its current position in pieces
   * */
  def suggestMoveQueen(piece: Piece, board: Array[Array[Piece]]): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    suggestedMoves
  }

  /**
   * todo: suggestMoveKing() function suggests all legal moves for king in its current position in pieces
   * */
  def suggestMoveKing(piece: Piece, board: Array[Array[Piece]]): ArrayBuffer[(Int, Int, Boolean)] = {
    val positionX = piece.positionX
    val positionY = piece.positionY
    val initialX = piece.initialX
    val initialY = piece.initialY

    /** the first and second Int element are (x, y) coordinates in 2D array respectively and the last boolean element is for opponent piece
     * check on square in board in that given x , y coordinates
     * */
    val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()

    suggestedMoves
  }
}
