package main.utils

import main.models.{Color, Piece, Rank}

object CheckDetector {
  private var isCheck = false

  def setCheck(): Unit = {
    isCheck = true
  }

  def isChecked: Boolean = isCheck
}
