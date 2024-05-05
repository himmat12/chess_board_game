package main.utils

import main.models.Color

/**
 * playerTurn stores the state of each player turn to move the piece
 * which is by default white, white player always makes the first move in chess
 * */
object PlayerTurn {
  private var player = Color.White
  private var isWhite = true

  def get: Color = player

  def toggle(): Unit = {
    if (player == Color.White)
      player = Color.Black
    else
      player = Color.White
  }

  def set(playerColor: Color): Unit = player = playerColor
}
