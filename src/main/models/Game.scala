package main.models

import main.models.Piece
import main.utils.Board.*
import main.utils.{CheckDetector, GameBuilder, LegalMoveSuggestion, PlayerTurn}

import main.enums.*
import scala.collection.mutable.ArrayBuffer
import scala.util.boundary
import main.utils.Utils.*

class Game {

  /** initialising the game */
  def initialiseGame(): Unit = {
    //    totalPieces = GameBuilder.initialiseDefaultSetup()
    //    totalPieces = GameBuilder.initialisePawnSetup()
    //    totalPieces = GameBuilder.initialiseRookSetup()
    //    totalPieces = GameBuilder.initialiseKnightSetup()
    //    totalPieces = GameBuilder.initialiseBishopSetup()
    //    totalPieces = GameBuilder.initialiseQueenSetup()
    totalPieces = GameBuilder.initialiseKingSetup()

    var row = 8
    for (x <- 0 until 8) {
      var col = 97
      for (y <- 0 until 8) {
        // setting default pieces on board squares
        board(x)(y) = defaultPiece(x, y)
        // mapping squares (x,y) coordinates corresponding to its string value like ["a8" = (0,0)]
        val pfxChar = col.toChar
        boardMap(x)(y) = s"$pfxChar$row"
        col += 1
      }
      row -= 1
    }

    /**
     * synchronising and placing each chess piece on squares (x, y) coordinate points on chess board
     * */
    for (x <- 0 to 7) {
      for (y <- 0 to 7) {
        totalPieces.foreach(e => {
          if (e.positionX == x && e.positionY == y) {
            board(x)(y) = e
          }
        })
      }
    }

  }


  /**
   * getBoardState() prints the layout of board mapped squares position coordinates values,
   * and places all the chess pieces to their default position in the pieces
   * */
  def getInitialBoardState(): Unit = {

    /** printing the chess pieces mapping position value */
    //        for (x <- 0 to 7) {
    //          for (y <- 0 to 7) {
    //            print(s"${boardMap(x)(y)} ")
    //          }
    //          println()
    //        }

    printPlayerTurn()

    /** printing the chess board with pieces on their setup positions */
    for (x <- 0 to 7) {
      /** printing rows */
      printRows(x)

      for (y <- 0 to 7) {
        if (board(x)(y).value != "" && (board(x)(y).positionX, board(x)(y).positionY) == (x, y)) {
          print(placeholder(board(x)(y).value))
        }
        else {
          if ((board(x)(y).positionX + board(x)(y).positionY) % 2 == 0)
            //            print(placeholder(s"${boardMap(x)(y)} "))
            print(placeholder(" W "))
          else
            //            print(placeholder(s"${boardMap(x)(y)} "))
            print(placeholder(" B "))
        }
      }
      println()

      /** printing columns */
      printColumns(x, " ", 39)
    }
    println()
  }


  /**
   * moves to the given (position) eg: (x, y) coordinates square position in the board,
   * captures any opponent pieces if its in its destination position
   * */
  def moveTo(pos: String, piece: Piece): Unit = {
    val suggestedMoves = LegalMoveSuggestion.getPiecesLegalMoves(piece).map(e => (e._1, e._2))

    val posCoordinate = decryptFromPoseString(pos)
    val x = posCoordinate._1
    val y = posCoordinate._2

    // capturing previous position of piece in board
    val lastPosX = piece.positionX
    val lastPosY = piece.positionY

    if (suggestedMoves.contains((x, y)) && board(x)(y) != board(lastPosX)(lastPosY)) {

      /** if there is any other opponent piece in new position then it will remove it (capture it) */
      if (board(x)(y).color != piece.color && board(x)(y).color != Color.None) {
        if (board(x)(y).rank != Rank.King) {
          val opponentPieceIndex = totalPieces.indexWhere(e => e.value == board(x)(y).value)
          totalPieces.remove(opponentPieceIndex)
        }
      }

      /** updating piece current position to new destination (x, y) position */
      piece.positionX = x
      piece.positionY = y
      board(x)(y) = piece


      /** resetting previous piece position in board to default piece value */
      board(lastPosX)(lastPosY) = defaultPiece(lastPosX, lastPosY)

      PlayerTurn.toggle()
    } else {
      PlayerTurn.resetSelectedPiece()
      println(s"Illegal move: [${piece.value}] cannot move from position (${encryptToPoseString(lastPosX, lastPosY)}) to ($pos)*")
    }
  }

  /** pawn piece promotion checker */
  private def checkPawnPromotion(): Unit = {}

  /**
   * suggests all available legal move options of selected chess piece
   * suggested moves are returned in an Array
   */
  def generateMove(piece: Piece): Unit = {
    printPlayerTurn()
    if (CheckDetector.isLegalMove(piece)) {
      if (piece.rank == Rank.Pawn)
        val suggestedMoves = LegalMoveSuggestion.suggestMovePawn(piece)
        markSuggestedMoves(piece, suggestedMoves)

      if (piece.rank == Rank.Rook)
        val suggestedMoves = LegalMoveSuggestion.suggestMoveRook(piece)
        markSuggestedMoves(piece, suggestedMoves)

      if (piece.rank == Rank.Knight)
        val suggestedMoves = LegalMoveSuggestion.suggestMoveKnight(piece)
        markSuggestedMoves(piece, suggestedMoves)

      if (piece.rank == Rank.Bishop)
        val suggestedMoves = LegalMoveSuggestion.suggestMoveBishop(piece)
        markSuggestedMoves(piece, suggestedMoves)

      if (piece.rank == Rank.Queen)
        val suggestedMoves = LegalMoveSuggestion.suggestMoveQueen(piece)
        markSuggestedMoves(piece, suggestedMoves)

      if (piece.rank == Rank.King)
        val suggestedMoves = CheckDetector.getKingLegalMoves(piece)
        markSuggestedMoves(piece, suggestedMoves)
    } else {
      val suggestedMoves = ArrayBuffer[(Int, Int, Boolean)]()
      markSuggestedMoves(piece, suggestedMoves)
    }
    PlayerTurn.resetSelectedPiece()

  }


}

