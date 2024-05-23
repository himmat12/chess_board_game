package test.models

import main.enums.*
import main.models.{Game, Piece}
import main.utils.MoveSuggestion
import main.utils.Board.*
import org.junit.Test
import org.junit.Assert.*

import scala.collection.mutable.ArrayBuffer

class MoveSuggestionTest {

  /** testing initial move which allows pawn to move two squares initially if it has not been moved from it's default position
   * and testing pawn move when it has already been moved from its default position
   * */
  @Test def testSuggestMovePawn01(): Unit = {
    val game = new Game()
    game.initialiseGame()

    /** piece movement from bottom of board i.e, white piece side */
    val wp01 = new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 6, 3)
    val wp02 = new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 5, 3)

    assertEquals(ArrayBuffer((5, 3), (4, 3)), MoveSuggestion.suggestMovePawn(wp01))
    assertEquals(ArrayBuffer((4, 3)), MoveSuggestion.suggestMovePawn(wp02))

    /** piece movement from top of board i.e, black piece side */
    val bp01 = new Piece(1, 3, "BP3", Color.Black, Rank.Pawn, 1, 3)
    val bp02 = new Piece(1, 3, "BP3", Color.Black, Rank.Pawn, 2, 3)

    assertEquals(ArrayBuffer((2, 3), (3, 3)), MoveSuggestion.suggestMovePawn(bp01))
    assertEquals(ArrayBuffer((3, 3)), MoveSuggestion.suggestMovePawn(bp02))

  }

  /** testing blockage in next square while moving forward if there is opponent piece or own piece in it */
  @Test def testSuggestMovePawn02(): Unit = {
    val game = new Game()
    game.initialiseGame()

    /** piece movement from bottom of board i.e, white piece side */
    // when piece has not moved from its default position
    val wp01 = new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 6, 3)
    board(5)(3).value = "WP3"

    // when piece has already moved from its default position
    val wp02 = new Piece(6, 4, "WP4", Color.White, Rank.Pawn, 5, 4)
    board(4)(4).value = "WP4"

    assertEquals(ArrayBuffer(), MoveSuggestion.suggestMovePawn(wp01))
    assertEquals(ArrayBuffer(), MoveSuggestion.suggestMovePawn(wp02))

    /** piece movement from top of board i.e, black piece side */
    // when piece has not moved from its default position
    val bp01 = new Piece(1, 3, "BP3", Color.Black, Rank.Pawn, 1, 3)
    board(2)(3).value = "BP3"

    // when piece has already moved from its default position
    val bp02 = new Piece(1, 4, "BP4", Color.Black, Rank.Pawn, 2, 4)
    board(3)(4).value = "BP4"

    assertEquals(ArrayBuffer(), MoveSuggestion.suggestMovePawn(bp01))
    assertEquals(ArrayBuffer(), MoveSuggestion.suggestMovePawn(bp02))
  }

  /** testing diagonal attack move if there are any opponents pieces in the corresponding diagonal square from piece's current square */
  @Test def testSuggestMovePawn03(): Unit = {
    val game = new Game()
    game.initialiseGame()

    /** piece movement from bottom of board i.e, white piece side */
    // when piece has not moved from its default position
    val wp01 = new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 6, 3)
    val bp01 = new Piece(1, 2, "BP2", Color.Black, Rank.Pawn, 5, 2)
    val bp02 = new Piece(1, 4, "BP4", Color.Black, Rank.Pawn, 5, 4)

    board(5)(2) = bp01
    board(5)(4) = bp02

    // when piece has already moved from its default position
    val wp02 = new Piece(6, 4, "WP6", Color.White, Rank.Pawn, 5, 6)
    val bp03 = new Piece(1, 7, "BP7", Color.Black, Rank.Pawn, 4, 7)
    val bp04 = new Piece(1, 5, "BP5", Color.Black, Rank.Pawn, 4, 5)

    board(4)(7) = bp03
    board(4)(5) = bp04

    assertEquals(ArrayBuffer((5, 3), (4, 3), (5, 2), (5, 4)), MoveSuggestion.suggestMovePawn(wp01))
    assertEquals(ArrayBuffer((4, 6), (4, 5), (4, 7)), MoveSuggestion.suggestMovePawn(wp02))

    /** piece movement from top of board i.e, black piece side */
    // when piece has not moved from its default position
    val bp11 = new Piece(1, 3, "BP3", Color.Black, Rank.Pawn, 1, 3)
    val wp11 = new Piece(6, 2, "WP2", Color.White, Rank.Pawn, 2, 2)
    val wp22 = new Piece(6, 4, "WP4", Color.White, Rank.Pawn, 2, 4)

    board(2)(2) = wp11
    board(2)(4) = wp22

    // when piece has already moved from its default position
    val bp22 = new Piece(2, 4, "BP4", Color.Black, Rank.Pawn, 3, 4)
    val wp33 = new Piece(6, 3, "WP3", Color.White, Rank.Pawn, 4, 3)
    val wp44 = new Piece(6, 5, "WP5", Color.White, Rank.Pawn, 4, 5)

    board(4)(3) = wp33
    board(4)(5) = wp44

    assertEquals(ArrayBuffer((2, 3), (3, 3), (2, 2), (2, 4)), MoveSuggestion.suggestMovePawn(bp11))
    assertEquals(ArrayBuffer((4, 4), (4, 3), (4, 5)), MoveSuggestion.suggestMovePawn(bp22))
  }

  /** todo: test logic needs to be implemented (this tests always fails as of now) */
  @Test def testCheckDetector(): Unit = {
    val game = new Game()
    game.initialiseGame()

    assertEquals(true, false)
  }

}
