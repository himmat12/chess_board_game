package main

import main.models.{Game, Move, Piece, Rank}

import scala.io.StdIn.*

object GameApp {
  val game = new Game()
  var selectedPiece: String = ""
  var moveTo: String = ""

  def main(args: Array[String]): Unit = {
    println(Rank.King)

    /**
     * runs game infinitely until its game over or player quits the game
     * */
    while (!selectedPiece.toLowerCase.equals("q")) {
      game.initialiseGame()
      game.printBoard(8, 8)
      

      selectedPiece = readLine("Please select the chess piece: ")
      moveTo = readLine("Please enter the value of square in board to move the piece: ")


    }

  }


}
