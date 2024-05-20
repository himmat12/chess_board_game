package main.models

import main.models.Piece
import main.utils.Board.*
import main.utils.{CheckDetector, GameBuilder, MoveSuggestion, MoveSuggestionAttack, PlayerTurn}

import scala.collection.mutable.ArrayBuffer
import scala.util.boundary

class Game {

  /** placeholder for pieces square values */
  private def placeholder(value: String): String = f"$value  "

  /**
   * withBrackets(value) takes string value and wraps it  inside bracket
   * */
  private def withBrackets(value: String): String = s"($value)"

  /** this function sets default space in board by initialising dummy Piece object */
  private def defaultPiece(posX: Int, posY: Int): Piece = {
    val piece = new Piece(-1, -1, "", Color.None, Rank.None, posX, posY)
    piece
  }

  // initialising the game
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

  /** helper function for printing board columns */
  private def printColumns(index: Int, space: String, lineCount: Int): Unit = {
    /** printing columns */
    if (index == 7) {
      print("    ")
      for (i <- 1 to lineCount) print("-")
      println()
      print("     ")
      for (i <- 0 to 7)
        print(placeholder(s" ${(97 + i).toChar}" + space))
    }
  }


  /** helper function for printing board bottom rows */
  private def printRows(index: Int): Unit = print(placeholder(s"${8 - index} |"))

  /** helper function which prints the current player turn */
  private def printPlayerTurn(): Unit = {
    println()
    println(s"Player: [${PlayerTurn.get}]\nSelected: [${PlayerTurn.getSelectedPiece}]")
  }

  /**
   * moves to the given (position) eg: (x, y) coordinates square position in the board,
   * captures any opponent pieces if its in its destination position
   * */
  def moveTo(pos: String, piece: Piece): Unit = {
    val suggestedMoves = MoveSuggestion.getPiecesLegalMoves(piece).map(e => (e._1, e._2))

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
        } else {
          /** todo: invoke check detector here */
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

  /** decrypts (converts) the board position string value "a8" and so on to (x,y) tuple (Int, Int) */
  private def decryptFromPoseString(pos: String): (Int, Int) = {
    val x = 8 - s"${pos.reverse.head}".toInt
    val y = pos.head.toLower.toInt - 97
    (x, y)
  }

  /** encrypts (revert) the board position (x,y) coordinates value to "a8" string value */
  private def encryptToPoseString(x: Int, y: Int): String = {
    val str1 = (97 + y).toChar
    val str2 = 8 - x
    s"$str1$str2"
  }

  /** pawn piece promotion checker */
  private def checkPawnPromotion(): Unit = {}

  /**
   * suggests all available legal move options of selected chess piece
   * suggested moves are returned in an Array
   */
  def generateMove(piece: Piece): Unit = {
    printPlayerTurn()

    if (piece.rank == Rank.Pawn)
      val suggestedMoves = MoveSuggestion.suggestMovePawn(piece)
      markSuggestedMoves(piece, suggestedMoves)

    if (piece.rank == Rank.Rook)
      val suggestedMoves = MoveSuggestion.suggestMoveRook(piece)
      markSuggestedMoves(piece, suggestedMoves)

    if (piece.rank == Rank.Knight)
      val suggestedMoves = MoveSuggestion.suggestMoveKnight(piece)
      markSuggestedMoves(piece, suggestedMoves)

    if (piece.rank == Rank.Bishop)
      val suggestedMoves = MoveSuggestion.suggestMoveBishop(piece)
      markSuggestedMoves(piece, suggestedMoves)

    if (piece.rank == Rank.Queen)
      val suggestedMoves = MoveSuggestion.suggestMoveQueen(piece)
      markSuggestedMoves(piece, suggestedMoves)

    if (piece.rank == Rank.King)
      val suggestedMoves = CheckDetector.getKingLegalMoves(piece)
      markSuggestedMoves(piece, suggestedMoves)

    PlayerTurn.resetSelectedPiece()
  }

  /** markSuggestedMoves() is a helper function for generateMove() function, which marks the suggested moves squares in board
   *
   * "(P)":  all available squares
   * "[P]":  selected piece square
   * "{P}":  squares where opponent pieces are
   *
   * */
  private def markSuggestedMoves(piece: Piece, moves: ArrayBuffer[(Int, Int, Boolean)]): Unit = {
    /** printing the chess pieces mapped position board with suggested move squares */
    var isPiecePosMarked = false
    for (x <- 0 to 7) {

      /** printing rows */
      printRows(x)
      for (y <- 0 to 7) {
        var flag = false
        var count = 0

        if (moves.isEmpty && !isPiecePosMarked && (x, y) == (piece.positionX, piece.positionY)) {
          print(placeholder(s"[${boardMap(x)(y)}]"))
          isPiecePosMarked = true
        }
        else {
          moves.foreach(e => {
            if ((x, y) == (piece.positionX, piece.positionY) && count == 0) {
              flag = true
              print(placeholder(s"[${boardMap(x)(y)}]"))
              count += 1
            }
            if ((x, y, false) == e) {
              flag = true
              print(placeholder(s"(${boardMap(x)(y)})"))
            }

            if ((x, y, true) == e) {
              flag = true
              print(placeholder(s"{${boardMap(x)(y)}}"))
            }
          })
          if (!flag)
            print(placeholder(s" ${boardMap(x)(y)} "))
        }
      }
      println()

      /** printing columns */
      printColumns(x, "  ", 47)
    }
    println()
  }

}

