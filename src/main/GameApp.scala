package main

import main.enums.*
import main.models.{Game, Piece}
import main.utils.InputValidator.*
import main.utils.{CheckDetector, PlayerTurn}
import main.utils.Board.*
import main.utils.CheckDetector.*

import scala.io.StdIn.*
import scala.util.matching.Regex

object GameApp {
  val game = new Game()
  private var selected: String = ""
  private var newPos: String = ""

  def main(args: Array[String]): Unit = {
    game.initialiseGame()

    /**
     * runs game infinitely until its game over or player quits the game
     * */
    while (!selected.toLowerCase.equals("q")) {
      game.getInitialBoardState()

      selected = readLine("Please select the chess piece: ")
      PlayerTurn.setSelectedPiece(selected)
      while (!isValidPieceStr(selected, totalPieces)) {
        println(s"Invalid piece: [$selected] piece do not exists on board!")
        selected = readLine("Please select the chess piece: ")
        PlayerTurn.setSelectedPiece(selected)
      }

      val index = totalPieces.indexWhere(e => e.value.toLowerCase == selected.toLowerCase)
      val selectedPiece = totalPieces(index)

      if (PlayerTurn.get == selectedPiece.color) {
        game.generateMove(selectedPiece)

        newPos = readLine("Enter the position value to move the piece or type 'switch' to select other piece: ")

        while (!isValidPoseStr(newPos)) {
          if (newPos != "switch")
            println(s"Invalid position: '$newPos' position do not exists in board!")
            newPos = readLine("Enter the position value to move the piece or type 'switch' to select other piece: ")
        }

        if (newPos != "switch") {
          game.moveTo(newPos, selectedPiece)
          val king = getOppntKing(selectedPiece)

          if (isChecked(king)) {
            // setting opponent piece which is checking the other king
            setOpponentPiece(selectedPiece)
            println()
            println(s"<<< ${king.color} ${king.rank} [${king.value}] is in Check!!! >>>")
            println(s"<<< Checked by: ${CheckDetector.getOpponentPiece.color} ${CheckDetector.getOpponentPiece.rank} [${CheckDetector.getOpponentPiece.value}] >>>")
            if (isCheckmate(king)) {
              println()
              println(s"Game Over: ${selectedPiece.color} Player wins.")
              sys.exit(0)
            }
          }
        }
      } else {
        PlayerTurn.resetSelectedPiece()
        println(s"[${PlayerTurn.get} Piece turn]: You cannot select [${selectedPiece.color} Piece]!")
      }
    }

  }


}
