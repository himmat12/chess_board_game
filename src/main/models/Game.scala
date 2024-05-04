package main.models

import main.models.Piece
import main.utils.MoveSuggestion.*

import scala.collection.mutable.ArrayBuffer

class Game {

  // deafult string line for printing
  private val line: String = "   +------------------------+   +----------------------------------+";

  // placeholder for pieces square values
  private def placeholder(value: String): String = f"$value  "

  /**
   * playerTurn stores the state of each player turn to move the piece
   * which is by default white, white player always makes the first move in chess
   * */
  private var playerTurn = Color.White

  def getPlayerTurn: Color = playerTurn

  def setPlayerTurn(data: Color): Unit = playerTurn = data

  /**
   * board array stores the calculated value ('char' + Int) of each mapped squares position in the pieces
   * */
  var board: Array[Array[Piece]] = Array.ofDim[Piece](8, 8)

  /**
   * boardMap for moving pieces to squares in the board which stores the array of tuple which maps "a8" to its corresponding (x,y) coordinate like ("a8", 0, 0)
   * */
  private var boardMap: Array[Array[String]] = Array.ofDim[String](8, 8)

  // collection of total chess pieces in board
  private var totalPieces = ArrayBuffer[Piece]()

  def getTotalPieces: ArrayBuffer[Piece] = totalPieces

  /** this function sets default space in board by initialising dummy Piece object */
  private def defaultPiece(posX: Int, posY: Int): Piece = {
    val piece = new Piece(-1, -1, "", Color.None, Rank.None, posX, posY)
    piece
  }

  // initialising the game
  def initialiseGame(): Unit = {
    //    setupDefaultFormation()
    setupPawnFormation()

    var row = 8
    for (x <- 0 until 8) {
      var col = 97
      for (y <- 0 until 8) {
        // setting default pieces on board squares
        board(x)(y) = defaultPiece(x, y)
        // mapping squares (x,y) coordinates corresponding to its string value like ["a8" = (0,0)]
        val prfxChar = col.toChar
        boardMap(x)(y) = s"$prfxChar$row"
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
    //    for (x <- 0 to 7) {
    //      for (y <- 0 to 7) {
    //        print(s"${boardMap(x)(y)} ")
    //      }
    //      println()
    //    }

    println()

    /** printing the chess board with pieces on their setup positions */
    for (x <- 0 to 7) {
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
    }
    println()
  }


  /**
   * setup default chess pieces in the board
   * */
  private def setupDefaultFormation(): Unit = {
    /**
     * setting the black pieces position in pieces
     * */
    for (y <- 0 to 7)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(1, y, "BP" + y, Color.Black, Rank.Pawn, 1, y))

    for (y <- 0 to 7 by 7)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(0, y, "BR" + y, Color.Black, Rank.Rook, 0, y))

    for (y <- 1 to 6 by 5)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(0, y, "BN" + y, Color.Black, Rank.Knight, 0, y))

    for (y <- 2 to 5 by 3)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(0, y, "BB" + y, Color.Black, Rank.Bishop, 0, y))

    totalPieces.addOne(new Piece(0, 3, "BQ3", Color.Black, Rank.Queen, 0, 3))

    totalPieces.addOne(new Piece(0, 4, "BK4", Color.Black, Rank.King, 0, 4))

    /**
     * setting the white pieces position in pieces
     * */
    for (y <- 0 to 7)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(6, y, "WP" + y, Color.White, Rank.Pawn, 6, y))

    for (y <- 0 to 7 by 7)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(7, y, "WR" + y, Color.White, Rank.Rook, 7, y))

    for (y <- 1 to 6 by 5)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(7, y, "WN" + y, Color.White, Rank.Knight, 7, y))

    for (y <- 2 to 5 by 3)
      val prfx = (97 + y).toChar
      totalPieces.addOne(new Piece(7, y, "WB" + y, Color.White, Rank.Bishop, 7, y))

    totalPieces.addOne(new Piece(7, 3, "WQ3", Color.White, Rank.Queen, 7, 3))

    totalPieces.addOne(new Piece(7, 4, "WK4", Color.White, Rank.King, 7, 4))


  }


  /** setup pawns formation */

  private def setupPawnFormation(): Unit = {
    /**
     * setting the black pieces position in pieces
     * */
    totalPieces.addOne(new Piece(1, 3, "BP3", Color.Black, Rank.Pawn, 1, 3))
    totalPieces.addOne(new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 2, 5))
    totalPieces.addOne(new Piece(1, 7, "BP7", Color.Black, Rank.Pawn, 5, 7))

    /**
     * setting the black pieces position in pieces
     * */
    totalPieces.addOne(new Piece(6, 2, "WP2", Color.White, Rank.Pawn, 2, 2))
    totalPieces.addOne(new Piece(6, 6, "WP6", Color.White, Rank.Pawn, 6, 6))
  }


  /**
   * moves to the given (position) eg: (x, y) coordinates square position in the board,
   * captures any opponent pieces if its in its destination position
   * */
  def moveTo(pos: String, piece: Piece): Unit = {
    val suggestedMoves = suggestMovePawn(piece, board).map(e => (e._1, e._2))

    val posCoordinate = decryptFromPoseString(pos)
    val x = posCoordinate._1
    val y = posCoordinate._2

    // capturing previous position of piece in board
    val lastPosX = piece.positionX
    val lastPosY = piece.positionY

    if (suggestedMoves.contains((x, y))) {

      // if there is any other opponent piece in new position then it will remove it (capture it)
      //    println(board(x)(y).color)
      //    println(board(x)(y).value)
      if (board(x)(y).color != piece.color && board(x)(y).color != Color.None)
        val opponentPieceIndex = totalPieces.indexWhere(e => e.value == board(x)(y).value)
        totalPieces.remove(opponentPieceIndex)

      // updating piece current position to new destination (x, y) position
      piece.positionX = x
      piece.positionY = y
      board(x)(y) = piece


      // resetting previous piece position in board to default piece value
      board(lastPosX)(lastPosY) = defaultPiece(lastPosX, lastPosY)

      //    println()
      //    println(board(lastPosX)(lastPosY).initialX)
      //    println(totalPieces.length)
      //    totalPieces.foreach(e => print(e.value + " "))
      //    println()
    } else {
      println(s"Invalid move: [${piece.value}] from (${encryptToPoseString(lastPosX, lastPosY)}) to ($pos)*")
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
   * suggests all available moves options of selected chess piece
   * suggested moves are returned in an Array
   */
  def suggestMove(piece: Piece): Unit = {
    if (piece.rank == Rank.Pawn) {
      val suggestedMoves = suggestMovePawn(piece, board)
      //      println()
      //      suggestedMoves.foreach(e => print(s"$e "))
      println()

      /** printing the chess pieces mapped position board with suggested move squares */
      var isPiecePosMarked = false
      for (x <- 0 to 7) {
        for (y <- 0 to 7) {
          var flag = false
          var count = 0

          if (suggestedMoves.isEmpty && !isPiecePosMarked && (x, y) == (piece.positionX, piece.positionY)) {
            print(placeholder(s"[${boardMap(x)(y)}]"))
            isPiecePosMarked = true
          }
          else {
            suggestedMoves.foreach(e => {
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
      }
      println()


    }
    //
    //    if (piece.rank == Rank.Rook) {
    //      suggestMoveRook(piece.positionX, piece.positionY)
    //    }
    //
    //    if (piece.rank == Rank.Knight) {
    //      suggestMoveKnight(piece.positionX, piece.positionY)
    //    }
    //
    //    if (piece.rank == Rank.Bishop) {
    //      suggestMoveBishop(piece.positionX, piece.positionY)
    //    }
    //
    //    if (piece.rank == Rank.Queen) {
    //      suggestMoveQueen(piece.positionX, piece.positionY)
    //    }
    //
    //    if (piece.rank == Rank.King) {
    //      suggestMoveKing(piece.positionX, piece.positionY)
    //    }

    //    println()
    //    println(piece.value)
    //    println(piece.color)
    //    println(piece.rank)
    //    println(piece.positionValue)
    //    println(s"initial position: (${piece.initialX}, ${piece.initialY})")
    //    println(s"current position: (${piece.positionX}, ${piece.positionY})")

  }

  /**
   * capture(rank) removes the opponent chess piece from the pieces and moves the attacking chess piece to captured chess piece position
   * except opponent's king for which it checks it
   * */
  def capture(rank: Rank): Unit = {
  }

  /**
   * check() function checks the opponent chess piece King when it is in attack range
   * */
  def check(): Unit = {}

  /**
   * printBoard() prints the layout of pieces the the pieces corresponding squares values,
   * and places all the chess pieces to their current positions in the pieces
   * */
  def printBoard(rows: Int, cols: Int): Unit = {
    //    println(line)
    for (y <- 8 to 1 by -1) {
      print(s" $y |")
      for (x <- 'a' until ('a' + cols).toChar) {
        if ((x + y - 'a') % 2 == 0) print(placeholder("■")) else print(placeholder("□"))
      }
      print("|   ")
      print(s"$y |")
      for (x <- 'a' until ('a' + cols).toChar) {
        if ((x + y) % 2 == 0)
          print(s"($x$y)")
        else
          print(placeholder(s"$x$y"))
      }
      println("|")
    }
    //    println(line)
    print("    ")
    for (c <- 'a' to 'h') {
      print(placeholder(s"$c"))
    }
    print("        ")
    for (c <- 'a' to 'h') {
      print(placeholder(s"$c "))
    }
    println()
  }

}

