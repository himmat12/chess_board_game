package main.utils

import scala.collection.mutable.ArrayBuffer

/** this object primary objective is to record all the game logs */
object GameLog {
  private var logs = ArrayBuffer[String]()

  def add(log: String): Unit = logs.addOne(log)

  def getGameLogs: ArrayBuffer[String] = logs
}
