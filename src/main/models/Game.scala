package main.models

import main.models.Piece

import scala.collection.mutable.ArrayBuffer

class Game {

  // deafult string line for printing
  private val line: String = "   +------------------------+   +----------------------------------+";

  // placeholder for board square values
  private def placeholder(value: String): String = s" $value "

  // chess board
  var board: Array[Array[Int]] = Array.ofDim[Int](8, 8)

  // collection of total black pieces in board
  private var blackPieces = ArrayBuffer[Piece]()

  // collection of total white pieces in board
  private var whitePieces = Array[Piece]()

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
        board(x)(y) = col + row
        print(board(x)(y) + " ")
        col += 1
      }
      println()
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
    row = 8
    for (x <- 0 until 8)
      var col = 97
      for (y <- 0 until 8)
        print(s"${col.toChar}${board(x)(y) - col} ")
        col += 1
      println()
      row -= 1

    // setting black and white squares value 0 for white and 1 for black in board
    for (x <- 0 until 8; y <- 0 until 8)
      if ((x + y) % 2 == 0)
        board(x)(y) = 0
      else
        board(x)(y) = 1

    // setting black pieces values in the board, integer values starting from 100 and so on is the value for black chess pieces
    // which indicates the piece rank hierarchy 200 as Pawn and 205 as King
    blackPieces.foreach(e => {
      for (x <- 'a' to 'h'; y <- 8 to 1 by -1)
        if ((e.positionX + e.positionY) == (x + y))
          if (e.rank == Rank.Pawn)
            board(x)(y) = 100
          if (e.rank == Rank.Rook)
            board(x)(y) = 101
          if (e.rank == Rank.Knight)
            board(x)(y) = 102
          if (e.rank == Rank.Bishop)
            board(x)(y) = 103
          if (e.rank == Rank.Queen)
            board(x)(y) = 104
          if (e.rank == Rank.King)
            board(x)(y) = 105
    })

    // setting white pieces values in the board, integer values starting from 200 and so on is the value for black chess pieces
    // which indicates the piece rank hierarchy 200 as Pawn and 205 as King
    whitePieces.foreach(e => {
      for (x <- 'a' to 'h'; y <- 8 to 1 by -1)
        if ((e.positionX + e.positionY) == (x + y))
          if (e.rank == Rank.Pawn)
            board(x)(y) = 200
          if (e.rank == Rank.Rook)
            board(x)(y) = 201
          if (e.rank == Rank.Knight)
            board(x)(y) = 202
          if (e.rank == Rank.Bishop)
            board(x)(y) = 203
          if (e.rank == Rank.Queen)
            board(x)(y) = 204
          if (e.rank == Rank.King)
            board(x)(y) = 205
    })
  }

  /**
   * setup initial chess pieces
   * */
  def setUpChessPieces(): Unit = {
    /**
     * setting the black pieces in board
     * */
    for (i <- 'a' to 'h')
      blackPieces.addOne(new Piece(Color.Black, Rank.Pawn, Array(MoveOption.StraightLine), i, 7))

    for (i <- 'a' to 'h' by 7)
      blackPieces.addOne(new Piece(Color.Black, Rank.Rook, Array(MoveOption.StraightLine), i, 8))

    for (i <- 'b' to 'g' by 5)
      blackPieces.addOne(new Piece(Color.Black, Rank.Knight, Array(MoveOption.L), i, 8))

    for (i <- 'c' to 'f' by 3)
      blackPieces.addOne(new Piece(Color.Black, Rank.Bishop, Array(MoveOption.Diagonal), i, 8))

    blackPieces.addOne(new Piece(Color.Black, Rank.Queen, Array(MoveOption.Diagonal, MoveOption.StraightLine), 'd', 8))

    blackPieces.addOne(new Piece(Color.Black, Rank.King, Array(MoveOption.StraightLine), 'e', 8))

    /**
     * setting the black pieces in board
     * */
    for (i <- 'a' to 'h')
      blackPieces.addOne(new Piece(Color.White, Rank.Pawn, Array(MoveOption.StraightLine), i, 2))

    for (i <- 'a' to 'h' by 7)
      blackPieces.addOne(new Piece(Color.White, Rank.Rook, Array(MoveOption.StraightLine), i, 1))

    for (i <- 'b' to 'g' by 5)
      blackPieces.addOne(new Piece(Color.White, Rank.Knight, Array(MoveOption.L), i, 1))

    for (i <- 'c' to 'f' by 3)
      blackPieces.addOne(new Piece(Color.White, Rank.Bishop, Array(MoveOption.Diagonal), i, 1))

    blackPieces.addOne(new Piece(Color.White, Rank.Queen, Array(MoveOption.Diagonal, MoveOption.StraightLine), 'd', 1))

    blackPieces.addOne(new Piece(Color.White, Rank.King, Array(MoveOption.StraightLine), 'e', 1))
  }

  /**
   * printBoard() prints the layout of board the the board corresponding squares values,
   * and places all the chess pieces to their current positions in the board
   * */

  def setUpBoard(): Unit = {


  }

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
