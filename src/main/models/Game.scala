package main.models

import main.models.Piece
import main.utils.MoveSuggestion.*

import scala.collection.mutable.ArrayBuffer

class Game {

  // deafult string line for printing
  private val line: String = "   +------------------------+   +----------------------------------+";

  // placeholder for board square values
  private def placeholder(value: String): String = f"$value  "

  /**
   * playerTurn stores the state of each player turn to move the piece
   * which is by default white, white player always makes the first move in chess
   * */
  private var playerTurn = Color.White

  def getPlayerTurn: Color = playerTurn

  def setPlayerTurn(data: Color): Unit = playerTurn = data

  /**
   * board state stores the mapped positional values of squares in the board (a1, a2, a3 . . .)
   * which helps to move around the chess piece in the board
   * */
  //  var boardState: Array[Array[Int]] = Array.ofDim[Int](8, 8)

  /**
   * game state, which means this array stores the calculated value ('char' + Int) of each mapped squares position in the board
   * */
  var gameState: Array[Array[Int]] = Array.ofDim[Int](8, 8)

  // collection of total chess pieces in board
  private var board = ArrayBuffer[Piece]()

  def getBoard: ArrayBuffer[Piece] = board

  // initialising the board
  def initialiseGame(): Unit = {
    setUpChessPieces()

    /**
     * setting chess board squares values to converted integer digits of:
     *
     * combined (char + int) -> ('a' + 8) = 105 ~ a8
     *
     * a8 b8 C8 d8 e8 f8 g8 h8
     * a7 b7 C7 d7 e7 f7 g7 h7
     * a6 b6 C6 d6 e6 f6 g6 h6
     * a5 b5 C5 d5 e5 f5 g5 h5
     * a4 b4 C4 d4 e4 f4 g4 h4
     * a3 b3 C3 d3 e3 f3 g3 h3
     * a2 b2 C2 d2 e2 f2 g2 h2
     * a1 b1 C1 d1 e1 f1 g1 h1
     *
     * converted integer values:
     *
     * 105 106 107 108 109 110 111 112
     * 104 105 106 107 108 109 110 111
     * 103 104 105 106 107 108 109 110
     * 102 103 104 105 106 107 108 109
     * 101 102 103 104 105 106 107 108
     * 100 101 102 103 104 105 106 107
     * 99  100 101 102 103 104 105 106
     * 98  99  100 101 102 103 104 105
     * */

    var row = 8
    for (x <- 0 until 8) {
      var col = 97
      for (y <- 0 until 8) {
        gameState(x)(y) = col + row
        //        print(gameState(x)(y) + " ") TODO: UNBLOCK THIS TO SEE THE RESULT
        col += 1
      }
      //      println() TODO: UNBLOCK THIS TO SEE THE RESULT
      row -= 1
    }

    /**
     * converting integer values into (char + int) concatenate string value:
     *
     *
     * stored integer values:
     *
     * 105 106 107 108 109 110 111 112
     * 104 105 106 107 108 109 110 111
     * 103 104 105 106 107 108 109 110
     * 102 103 104 105 106 107 108 109
     * 101 102 103 104 105 106 107 108
     * 100 101 102 103 104 105 106 107
     * 99  100 101 102 103 104 105 106
     * 98  99  100 101 102 103 104 105
     *
     * 'a' = 97
     *
     * now,
     *
     * (105 - 97) = 8 ~ which is our integer value
     * then, we convert the 97 to char which is 'a' and we concatenate and make it "a8"
     *
     * converted values:
     *
     * a8 b8 C8 d8 e8 f8 g8 h8
     * a7 b7 C7 d7 e7 f7 g7 h7
     * a6 b6 C6 d6 e6 f6 g6 h6
     * a5 b5 C5 d5 e5 f5 g5 h5
     * a4 b4 C4 d4 e4 f4 g4 h4
     * a3 b3 C3 d3 e3 f3 g3 h3
     * a2 b2 C2 d2 e2 f2 g2 h2
     * a1 b1 C1 d1 e1 f1 g1 h1
     *
     * */
    /**
     * TODO: UNBLOCK THIS TO SEE THE RESULT
     * */
    //    row = 8
    //    for (x <- 0 until 8) {
    //      var col = 97
    //      for (y <- 0 until 8) {
    //        print(s"${col.toChar}${gameState(x)(y) - col} ")
    //        col += 1
    //      }
    //      println()
    //      row -= 1
    //    }


  }


  /**
   * getBoardState() prints the layout of board the, and sets the board corresponding squares values,
   * and places all the chess pieces to their default position in the board
   * */
  def getBoardState(): Unit = {

    /** printing the chess board mapping position */
    var row = 8
    for (x <- 0 until 8) {
      var col = 97
      for (y <- 0 until 8) {
        print(s"${col.toChar}${gameState(x)(y) - col} ")
        col += 1
      }
      println()
      row -= 1
    }
    println()

    /** printing the chess board mapping position value */
    gameState.foreach(e => {
      e.foreach(e => print(e + " "))
      println()
    })

    println()


    /**
     * printing the initial state of chess board with black and white pieces in their default positions in the board
     * */
    for (x <- 0 to 7) {
      for (y <- 0 to 7) {
        board.foreach(e => {
          if (e.positionX == x && e.positionY == y) {
            if (e.rank == Rank.Pawn && e.color == Color.Black)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Rook && e.color == Color.Black)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Knight && e.color == Color.Black)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Bishop && e.color == Color.Black)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Queen && e.color == Color.Black)
              print(placeholder(e.symbol))
            if (e.rank == Rank.King && e.color == Color.Black)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Pawn && e.color == Color.White)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Rook && e.color == Color.White)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Knight && e.color == Color.White)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Bishop && e.color == Color.White)
              print(placeholder(e.symbol))
            if (e.rank == Rank.Queen && e.color == Color.White)
              print(placeholder(e.symbol))
            if (e.rank == Rank.King && e.color == Color.White)
              print(placeholder(e.symbol))

          }
        })
        if (x > 1 && x < 6) {
          if ((x + y) % 2 != 0) print(placeholder(" B ")) else print(placeholder(" W "))
        }
      }
      println()
    }

  }


  /**
   * setup initial chess pieces
   * */
  private def setUpChessPieces(): Unit = {
    /**
     * setting the black pieces position in board
     * */
    for (y <- 0 to 7)
      val value: Int = 97 + y + 7
      board.addOne(new Piece("BP" + y, value, Color.Black, Rank.Pawn, Array(MoveOption.StraightLine), 1, y))

    for (y <- 0 to 7 by 7)
      val value: Int = 97 + y + 8
      board.addOne(new Piece("BR" + y, value, Color.Black, Rank.Rook, Array(MoveOption.StraightLine), 0, y))

    for (y <- 1 to 6 by 5)
      val value: Int = 97 + y + 8
      board.addOne(new Piece("BN" + y, value, Color.Black, Rank.Knight, Array(MoveOption.L), 0, y))

    for (y <- 2 to 5 by 3)
      val value: Int = 97 + y + 8
      board.addOne(new Piece("BB" + y, value, Color.Black, Rank.Bishop, Array(MoveOption.Diagonal), 0, y))

    board.addOne(new Piece("BQ3", ('d' + 8), Color.Black, Rank.Queen, Array(MoveOption.Diagonal, MoveOption.StraightLine), 0, 3))

    board.addOne(new Piece("BK4", ('e' + 8), Color.Black, Rank.King, Array(MoveOption.StraightLine), 0, 4))

    /**
     * setting the white pieces position in board
     * */
    for (y <- 0 to 7)
      val value: Int = 97 + y + 2
      board.addOne(new Piece("WP" + y, value, Color.White, Rank.Pawn, Array(MoveOption.StraightLine), 6, y))

    for (y <- 0 to 7 by 7)
      val value: Int = 97 + y + 1
      board.addOne(new Piece("WR" + y, value, Color.White, Rank.Rook, Array(MoveOption.StraightLine), 7, y))

    for (y <- 1 to 6 by 5)
      val value: Int = 97 + y + 1
      board.addOne(new Piece("WN" + y, value, Color.White, Rank.Knight, Array(MoveOption.L), 7, y))

    for (y <- 2 to 5 by 3)
      val value: Int = 97 + y + 1
      board.addOne(new Piece("WB" + y, value, Color.White, Rank.Bishop, Array(MoveOption.Diagonal), 7, y))

    board.addOne(new Piece("WQ3", ('d' + 1), Color.White, Rank.Queen, Array(MoveOption.Diagonal, MoveOption.StraightLine), 7, 3))

    board.addOne(new Piece("WK4", ('e' + 1), Color.White, Rank.King, Array(MoveOption.StraightLine), 7, 4))
  }


  /**
   * suggests all available moves options of selected chess piece
   * suggested moves are returned in an Array
   */
  def suggestMove(piece: Piece): Unit = {

    if (piece.rank == Rank.Pawn) {
      println(gameState(piece.positionX)(piece.positionY))
      val suggestedMoves = suggestMovePawn(piece.positionX, piece.positionY, board)

      /**
       * todo: after we get list of suggested moves we iterate the boardState and match the suggested moves list each element
       * todo: and print the positional mapping board with suggested squares wrapped with bracket
       * */
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

    println(piece.symbol)
    println(piece.color)
    println(piece.rank)
    println(piece.positionValue)
    println(s"position: (${piece.positionX}, ${piece.positionY})")

  }

  /**
   * printBoard() prints the layout of board the the board corresponding squares values,
   * and places all the chess pieces to their current positions in the board
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
