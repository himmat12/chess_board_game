package main.utils

import main.models.{Color, Game, Piece, Rank}

import scala.collection.mutable.ArrayBuffer
import scala.util.boundary
import main.utils.{MoveSuggestion, MoveSuggestionAttack}
import main.utils.Board.*

/** CheckDetector implementation */
object CheckDetector {
  /** default piece - empty space in board squares */
  private val defaultPiece = new Piece(-1, -1, "", Color.None, Rank.None, -1, -1)

  /** checkedKing stores checked king piece value */
  private var checkedKing = defaultPiece

  /**
   * takes in king as piece parma and matches king's position (x,y) with each opponent pieces possible next moves and
   * if king's position matches with any of the pieces moves then it will flag isCheck to true and
   * assign the checkedKing value to pieces (king) color
   * */
  private def isChecked(king: Piece): Boolean = {
    val opponentsMoves = getOpponentMoves(king)
    var isChecked = false
    opponentsMoves.foreach(e => boundary {
      if ((e._1, e._2) == (king.positionX, king.positionY))
        isChecked = true
        checkedKing = king
        boundary.break()
    })
    isChecked
  }

  /**
   * this function checks all the legal move options for king and determines weather it is checkmate or not
   * depending on the king piece have any moves left after check, if it does then it returns false else true i.e, checkmate
   * */
  private def isCheckmate(king: Piece): Boolean = {
    val kingLegalMoves = getKingLegalMoves(king)
    val boardWithoutKing = totalPieces.filter(e => e != king)
    val moves = boardWithoutKing.map(e => MoveSuggestion.getPiecesLegalMoves(king))
    val piecesLegalMoves = moves.flatten

    if (kingLegalMoves.isEmpty && piecesLegalMoves.isEmpty && isChecked(king))
      true
    else
      false
  }

  /**
   * this functions returns all opponents pieces attack range moves in a single array (ArrayBuffer[(Int, Int, Boolean)])
   * */
  private def getOpponentAttkMoves(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val opponentPieces = totalPieces.filter(e => e.color != piece.color)
    val opponentsMoves = ArrayBuffer[ArrayBuffer[(Int, Int, Boolean)]]()
    opponentPieces.foreach(e => opponentsMoves.addOne(MoveSuggestionAttack.genAttkRangeMoves(e)))

    val onlyMoves = opponentsMoves.flatten

    onlyMoves
  }

  /**
   * this functions returns all opponents pieces legal range moves
   * */
  private def getOpponentMoves(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val opponentPieces = totalPieces.filter(e => e.color != piece.color)
    val opponentsMoves = ArrayBuffer[ArrayBuffer[(Int, Int, Boolean)]]()
    opponentPieces.foreach(e => opponentsMoves.addOne(MoveSuggestion.getPiecesLegalMoves(e)))

    val onlyMoves = opponentsMoves.flatten

    onlyMoves
  }

  /** isLegalMove returns true if the next move for king is legal i.e, the next square is not under opponent piece move range else false */
  def getKingLegalMoves(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val opponentsMoves = getOpponentAttkMoves(piece).distinct
    val suggestedMoves = MoveSuggestion.suggestMoveKing(piece)

    /** opponentMoveCordnts and suggestedMoveCordnts only stores x and y coordinates values in the Array */
    val opponentMoveCordnts = opponentsMoves.map(e => (e._1, e._2))
    val suggestedMoveCordnts = suggestedMoves.map(e => (e._1, e._2))

    /** difference between suggested moves and opponents attack moves */
    val difference = (suggestedMoveCordnts.toSet -- opponentMoveCordnts.toSet).toArray

    /** legal moves stores final filtered legal moves for king */
    val kingLegalMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /**
     * here we are filtering suggestedMoves elements by comparing each element e._1 and e._2 which are x and y value respectively
     * with difference array list which stores filtered legal moves for king
     * */
    suggestedMoves.foreach(e => difference.foreach(x => if ((e._1, e._2) == x) kingLegalMoves.addOne(e)))

    kingLegalMoves
  }

  /**
   * isLegalMove functions matches other chess piece position and king position with the each opponent pieces attack range moves
   * and if both (normal piece & king) piece position coordinates (x, y) matches in the opponents attack range moves array
   * then it returns true else false
   * */
  def isLegalMove(piece: Piece): Boolean = {
    val x = piece.positionX
    val y = piece.positionY
    val index = totalPieces.indexWhere(e => e.color == piece.color && e.rank == Rank.King)
    val king = totalPieces(index)
    val opponentPieces = totalPieces.filter(e => e.color != piece.color)
    val oppntAttackmoves = ArrayBuffer[ArrayBuffer[(Int, Int, Boolean)]]()

    board(x)(y) = defaultPiece
    opponentPieces.foreach(e => oppntAttackmoves.addOne(MoveSuggestionAttack.genAttkRangeMoves(e)))

    val onlyOppntAttackmoves = oppntAttackmoves.flatten.map(e => (e._1, e._2))

    if (onlyOppntAttackmoves.contains((king.positionX, king.positionY)) && onlyOppntAttackmoves.contains((piece.positionX, piece.positionY)))
      board(x)(y) = piece
      false
    else
      board(x)(y) = piece
      true
  }

}
