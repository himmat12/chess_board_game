package main.utils

import main.enums.*
import main.models.{Game, Piece}

import scala.collection.mutable.ArrayBuffer
import scala.util.boundary
import main.utils.{LegalMoveSuggestion, MoveSuggestionAttack}
import main.utils.Board.*
import main.utils.CheckDetector.{canCaptureOpponent, isDefendingKing}

/** CheckDetector implementation */
object CheckDetector {
  /** default piece - empty space in board squares */
  val defaultPiece = new Piece(-1, -1, "", Color.None, Rank.None, -1, -1)

  /** opponent piece attacking king */
  private var opponentPiece = defaultPiece

  def getOpponentPiece: Piece = opponentPiece

  def setOpponentPiece(piece: Piece): Unit = {
    opponentPiece = piece
  }

  /**
   * takes in king as piece parma and matches king's position (x,y) with each opponent pieces possible next moves and
   * if king's position matches with any of the pieces moves then it will flag isCheck to true and
   * assign the checkedKing value to pieces (king) color
   * */
  def isChecked(king: Piece): Boolean = {
    val opponentsMoves = getOpponentMoves(king)
    //    var isChecked = false
    if (opponentsMoves.map(e => (e._1, e._2)).contains((king.positionX, king.positionY)))
      true
    else
      false
    //    opponentsMoves.foreach(e => boundary {
    //      if ((e._1, e._2) == (king.positionX, king.positionY))
    //        isChecked = true
    //        checkedKing = king
    //        boundary.break()
    //    })
    //    isChecked
  }

  /**
   * this function checks all the legal move options for king and determines weather it is checkmate or not
   * depending on the king piece have any moves left after check, if it does then it returns false else true i.e, checkmate
   * */
  def isCheckmate(king: Piece): Boolean = {
    val kingLegalMoves = getKingLegalMoves(king)
    val boardWithoutKing = totalPieces.filter(e => e != king)
    val moves = boardWithoutKing.map(e => LegalMoveSuggestion.getPiecesLegalMoves(king))
    val piecesLegalMoves = moves.flatten

    if (kingLegalMoves.isEmpty && piecesLegalMoves.isEmpty && isChecked(king))
      true
    else
      false
  }

  /**
   * this functions returns all opponents pieces legal range moves
   * */
  private def getOpponentMoves(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val opponentPieces = totalPieces.filter(e => e.color != piece.color)
    val opponentsMoves = ArrayBuffer[ArrayBuffer[(Int, Int, Boolean)]]()
    opponentPieces.foreach(e => opponentsMoves.addOne(LegalMoveSuggestion.getPiecesLegalMoves(e)))

    val onlyMoves = opponentsMoves.flatten

    onlyMoves
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

  /** this function returns array of opponents pieces positions except opponent king */
  private def getOppntPiecesPos(piece: Piece): Array[(Int, Int)] = {
    val opponentPieces = totalPieces.filter(e => e.color != piece.color && e.rank != Rank.King)
    val opponentPiecesPos = opponentPieces.map(e => (e.positionX, e.positionY)).toArray
    opponentPiecesPos
  }

  /** isLegalMove returns true if the next move for king is legal i.e, the next square is not under opponent piece move range else false */
  def getKingLegalMoves(piece: Piece): ArrayBuffer[(Int, Int, Boolean)] = {
    val opponentsMoves = getOpponentAttkMoves(piece).distinct
    val suggestedMoves = LegalMoveSuggestion.suggestMoveKing(piece)

    /** opponentMoveCordnts and suggestedMoveCordnts only stores x and y coordinates values in the Array */
    val opponentMoveCordnts: Array[(Int, Int)] = opponentsMoves.map(e => (e._1, e._2)).toArray
    val suggestedMoveCordnts: Array[(Int, Int)] = suggestedMoves.map(e => (e._1, e._2)).toArray
    val oppntPiecesPosCordnts: Array[(Int, Int)] = getOppntPiecesPos(piece)

    /**
     * compliment set operation between suggested moves and opponents attack moves (this operation implements A compliment B set
     * operation between suggestedMoveCordnts and opponentMoveCordnts, and )
     * */
    val compliment = (suggestedMoveCordnts.toSet &~ opponentMoveCordnts.toSet).toArray

    /**
     * intersection set operation between suggestedMoveCordnts  and oppntPiecesPosCordnts (this operation implements A intersection B set
     * operation between suggestedMoveCordnts and oppntPiecesPosCordnts, and returns the tuples that are common to both A and B.)
     * */
    val intersection = opponentMoveCordnts.intersect(oppntPiecesPosCordnts)

    /** union set operation between compliment and intersection collections (this will collects every elements from both collections into single distinct collection of elements) */
    val union = (compliment.toSet | intersection.toSet).toArray

    /** legal moves stores final filtered legal moves for king */
    val kingLegalMoves = ArrayBuffer[(Int, Int, Boolean)]()

    /**
     * here we are filtering suggestedMoves elements by comparing each element e._1 and e._2 which are x and y value respectively
     * with compliment array list which stores filtered legal moves for king
     * */
    //    suggestedMoves.foreach(e => union.foreach(x => if ((e._1, e._2) == x) kingLegalMoves.addOne(e)))
    suggestedMoves.foreach(e => compliment.foreach(x => if ((e._1, e._2) == x) kingLegalMoves.addOne(e)))

    kingLegalMoves
  }

  /**
   * isLegalMove functions matches other chess piece position and king position with the each opponent pieces attack range moves
   * and if both (normal piece & king) piece position coordinates (x, y) matches in the opponents attack range moves array
   * then it returns true else false
   * */
  def isLegalMove(piece: Piece): Boolean = {
    val king = getKing(piece)
    val isCheck = isChecked(king)
    val isDfndngKing = isDefendingKing(piece)
    val canCptrOppnt = canCaptureOpponent(piece)

    if (!isCheck && !isDfndngKing || piece.rank == Rank.King) {
      true
    } else {
      if (canCptrOppnt)
        true
      else
        false
    }
  }

  /** isDefendingKing function checks weather the selected piece is blocking the check from opponent pieces, if it's blocking then returns true else false */
  private def isDefendingKing(piece: Piece): Boolean = {
    val x = piece.positionX
    val y = piece.positionY
    val king = getKing(piece)
    val opponentPieces = totalPieces.filter(e => e.color != piece.color)
    val opponentMoves = ArrayBuffer[ArrayBuffer[(Int, Int, Boolean)]]()

    // removing selected piece from its current position on board
    board(x)(y) = defaultPiece

    // and checking weather other pieces can check the kin in absence of selected piece
    opponentPieces.foreach(e => opponentMoves.addOne(LegalMoveSuggestion.getPiecesLegalMoves(e)))

    val onlyOppntMoves = opponentMoves.flatten.map(e => (e._1, e._2))

    if (onlyOppntMoves.contains((king.positionX, king.positionY)) && onlyOppntMoves.contains((piece.positionX, piece.positionY)))
      // putting back selected piece after checking "check" when it is not in the board by removing it before
      board(x)(y) = piece
      true
    else
      // putting back selected piece after checking "check" when it is not in the board by removing it before
      board(x)(y) = piece
      false
  }

  /** canCaptureOpponent function checks weather the selected piece can capture the opponent piece that is checking the king, if it can capture then returns true else false */
  def canCaptureOpponent(piece: Piece): Boolean = {
    //    if (isChecked(getKing(piece))) {
    val pieces = totalPieces.filter(e => e.color == piece.color)
    val candidatePieces = ArrayBuffer[Piece](defaultPiece) // default piece is added to candidate pieces array just to make it even when there are no any opponent pieces that are checking the king
    pieces.foreach(piece => {
      val legalMoves = LegalMoveSuggestion.getPiecesLegalMoves(piece).distinct
      legalMoves.foreach(e => {
        if ((e._1, e._2) == (getOpponentPiece.positionX, getOpponentPiece.positionY)) {
          candidatePieces.addOne(piece)
        }
      })
    })

    if (candidatePieces.contains(piece))
      true
    else
      false
    //    } else {
    //      false
    //    }
  }

  /** this functions checks weather the piece can capture the opponent piece which is checking the player king and returns true if it can capture else false */
  //  def canCaptureOpponent(piece: Piece): Boolean = {
  //    val king = getKing(piece)
  //    val x = king.positionX
  //    val y = king.positionY
  //    val oppntX = getOpponentPiece.positionX
  //    val oppntY = getOpponentPiece.positionY
  //
  //    board(x)(y) = defaultPiece
  //    val rookMoves = suggestMoveRook(piece)
  //    val checkingPiecePos = (oppntX, oppntY)
  //    var canCapture = false
  //
  //    rookMoves.foreach(e => boundary {
  //      if ((e._1, e._2) == checkingPiecePos) {
  //        canCapture = true
  //        boundary.break()
  //      }
  //    })
  //    canCapture
  //  }

  /** this function locates opponent king of the given piece on board and returns it */
  def getOppntKing(piece: Piece): Piece = {
    val index = totalPieces.indexWhere(e => e.color != piece.color && e.rank == Rank.King)
    val king = totalPieces(index)
    king
  }


  /** this function locates king of the given piece on board and returns it */
  def getKing(piece: Piece): Piece = {
    val index = totalPieces.indexWhere(e => e.color == piece.color && e.rank == Rank.King)
    val king = totalPieces(index)
    king
  }

}
