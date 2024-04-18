package main

import main.models.Move

object Game {
  val movement = new Move()

  def main(args: Array[String]): Unit = {
    movement.rpt(2)(println("Hello world"))

  }
}
