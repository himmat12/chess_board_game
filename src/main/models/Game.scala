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
  private def defaultPiece(x: Int, y: Int): Piece = {
    val piece = new Piece(-1, -1, "", Color.None, Rank.None, x, y)
    piece
  }

  // initialising the game
  def initialiseGame(): Unit = {
    setUpChessPieces()

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
  def getBoardState(): Unit = {

    /** printing the chess pieces mapping position value */
    //    for (x <- 0 to 7) {
    //      for (y <- 0 to 7) {
    //        print(s"${boardMap(x)(y)} ")
    //      }
    //      println()
    //    }

    println()

    /** printing the chess board with pieces on their initial positions */
    for (x <- 0 to 7) {
      for (y <- 0 to 7) {
        if (board(x)(y).value != "" && (board(x)(y).positionX, board(x)(y).positionY) == (x, y)) {
          print(placeholder(board(x)(y).value))
        }
        else {
          if ((board(x)(y).positionX + board(x)(y).positionY) % 2 == 0)
            print(placeholder(s"${boardMap(x)(y)} "))
          else
            print(placeholder(s"${boardMap(x)(y)} "))
        }
      }
      println()
    }
    println()
  }


  /**
   * setup initial chess pieces
   * */
  private def setUpChessPieces(): Unit = {
    /**
     * setting the black pieces position in pieces
     * */
    for (y <- 0 to 7)
      val prfx = (97 + y).toChar
      //      val value: String = s"$prfx" + 7
      totalPieces.addOne(new Piece(1, y, "BP" + y, Color.Black, Rank.Pawn, 1, y))

    for (y <- 0 to 7 by 7)
      val prfx = (97 + y).toChar
//      val value: String = s"$prfx" + 8
      totalPieces.addOne(new Piece(0, y, "BR" + y, Color.Black, Rank.Rook, 0, y))

    for (y <- 1 to 6 by 5)
      val prfx = (97 + y).toChar
//      val value: String = s"$prfx" + 8
      totalPieces.addOne(new Piece(0, y, "BN" + y, Color.Black, Rank.Knight, 0, y))

    for (y <- 2 to 5 by 3)
      val prfx = (97 + y).toChar
//      val value: String = s"$prfx" + 8
      totalPieces.addOne(new Piece(0, y, "BB" + y, Color.Black, Rank.Bishop, 0, y))

    totalPieces.addOne(new Piece(0, 3, "BQ3", Color.Black, Rank.Queen, 0, 3))

    totalPieces.addOne(new Piece(0, 4, "BK4", Color.Black, Rank.King, 0, 4))

    /**
     * setting the white pieces position in pieces
     * */
    for (y <- 0 to 7)
      val prfx = (97 + y).toChar
      //      val value: String = s"$prfx" + 2
      totalPieces.addOne(new Piece(6, y, "WP" + y, Color.White, Rank.Pawn, 6, y))

    for (y <- 0 to 7 by 7)
      val prfx = (97 + y).toChar
      //      val value: String = s"$prfx" + 1
      totalPieces.addOne(new Piece(7, y, "WR" + y, Color.White, Rank.Rook, 7, y))

    for (y <- 1 to 6 by 5)
      val prfx = (97 + y).toChar
      //      val value: String = s"$prfx" + 1
      totalPieces.addOne(new Piece(7, y, "WN" + y, Color.White, Rank.Knight, 7, y))

    for (y <- 2 to 5 by 3)
      val prfx = (97 + y).toChar
      //      val value: String = s"$prfx" + 1
      totalPieces.addOne(new Piece(7, y, "WB" + y, Color.White, Rank.Bishop, 7, y))

    totalPieces.addOne(new Piece(7, 3, "WQ3", Color.White, Rank.Queen, 7, 3))

    totalPieces.addOne(new Piece(7, 4, "WK4", Color.White, Rank.King, 7, 4))


  }


  /**
   * suggests all available moves options of selected chess piece
   * suggested moves are returned in an Array
   */
  def suggestMove(value: String): Unit = {
    val index = totalPieces.lastIndexWhere(e => e.value == value)
    val piece = totalPieces(index)

    if (piece.rank == Rank.Pawn) {
      val suggestedMoves = suggestMovePawn(piece, board)
      println()
      suggestedMoves.foreach(e => print(s"$e "))
      println()

      /** printing the chess pieces mapped position board with suggested move squares */
      for (x <- 0 to 7) {
        for (y <- 0 to 7) {
          var flag = false
          var count = 0
          suggestedMoves.foreach(e => {
            if ((x, y) == (piece.positionX, piece.positionY) && count == 0) {
              flag = true
              print(placeholder(s"[${boardMap(x)(y)}]"))
              count += 1
            }
            if ((x, y) == e) {
              flag = true
              print(placeholder(s"(${boardMap(x)(y)})"))
            }
          })
          if (flag == false)
            print(placeholder(s" ${boardMap(x)(y)} "))
        }
        println()
      }


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
