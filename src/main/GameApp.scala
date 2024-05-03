package main

import main.models.{Game, Move, Piece, Rank}

import scala.io.StdIn.*

object GameApp {
  val game = new Game()
  var selected: String = ""
  var moveTo: String = ""

  def main(args: Array[String]): Unit = {
    game.initialiseGame()

    /**
     * runs game infinitely until its game over or player quits the game
     * */
    while (!selected.toLowerCase.equals("q")) {
      //      game.printBoard(8, 8)
      game.getBoardState()
      selected = readLine("Please select the chess piece: ")

      game.suggestMove(selected)

//      moveTo = readLine("Please enter the value of square in board to move the piece: ")


    }

  }


}
