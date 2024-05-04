package main

import main.models.{Game, Move, Piece, Rank}
import main.utils.InputValidator.*

import scala.io.StdIn.*
import scala.util.matching.Regex

object GameApp {
  val game = new Game()
  var selected: String = ""
  var newPos: String = ""

  def main(args: Array[String]): Unit = {
    game.initialiseGame()

    /**
     * runs game infinitely until its game over or player quits the game
     * */
    while (!selected.toLowerCase.equals("q")) {
      //      game.printBoard(8, 8)
      game.getInitialBoardState()

      selected = readLine("Please select the chess piece: ")
      val index = game.getTotalPieces.indexWhere(e => e.value.toLowerCase == selected.toLowerCase)
      val selectedPiece = game.getTotalPieces(index)
      game.suggestMove(selectedPiece)

      newPos = readLine("Enter the position value to move the piece: ")

      while (!isValidPoseStr(newPos)) {
        newPos = readLine("Enter the position value to move the piece: ")
      }

      game.moveTo(newPos, selectedPiece)

    }

  }


}
