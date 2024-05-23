package main.utils

import main.enums.*

/**
 * playerTurn stores the state of each player turn to move the piece
 * which is by default white, white player always makes the first move in chess
 * */
object PlayerTurn {
  private var player = Color.White
  private var selectedPiece = "None"

  def get: Color = player

  /** toggle() function toggles the player turn state from white to black and vice versa */
  def toggle(): Unit = {
    if (player == Color.White)
      player = Color.Black
    else
      player = Color.White
  }

  def set(playerColor: Color): Unit = player = playerColor

  def getSelectedPiece: String = selectedPiece

  def setSelectedPiece(str: String): Unit = {
    selectedPiece = str
  }

  def resetSelectedPiece(): Unit = {
    selectedPiece = "None"
  }
}
