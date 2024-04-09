# chess_board_game

#### project structure (will be refractored)
```
chess-board-game/
|-- src/
|   |-- main/
|   |   |-- chess/
|   |       |-- models/
|   |       |   |-- Piece.scala          # Define piece classes (e.g., King, Queen, Pawn, etc.)
|   |       |   |-- Board.scala          # Define the chess board
|   |       |   |-- Move.scala           # Define move representation
|   |       |-- utils/
|   |           |-- InputHandler.scala   # Handle user input
|   |           |-- OutputHandler.scala  # Handle game output
|   |           |-- GameLogic.scala      # Implement game logic
|   |-- test/
|       |-- chess/
|           |-- models/
|               |-- PieceSpec.scala      # Unit tests for Piece class
|               |-- BoardSpec.scala      # Unit tests for Board class
|               |-- MoveSpec.scala       # Unit tests for Move class
|           |-- utils/
|               |-- InputHandlerSpec.scala  # Unit tests for InputHandler
|               |-- OutputHandlerSpec.scala # Unit tests for OutputHandler
|               |-- GameLogicSpec.scala     # Unit tests for GameLogic
|-- Chess game.iml                         # Scala build configuration file
|-- README.md                              # Project README file

