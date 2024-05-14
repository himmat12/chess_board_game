# Scala Chess Board Game

Welcome to my Scala-based chess board game project!

This project is a simple implementation of chess in Scala. It provides a basic chess board, pieces, and rules to play the game.

## Features

- **Chess Board**: A standard 8x8 chess board.
- **Pieces**: Implementation of all standard chess pieces - King, Queen, Rook, Bishop, Knight, and Pawn.
- **Movement**: Valid movement logic for all pieces according to standard chess rules.
- **Check and Checkmate Detection**: Detection of check and checkmate conditions during gameplay.
- **Simple Text-based Interface**: The game is played via a simple text-based interface in the terminal.

## Requirements

- **Scala**: This project is built using Scala programming language.
- **SBT (Scala Build Tool)**: SBT is used for building and managing dependencies.

## Getting Started

1. **Clone the Repository**: Clone this repository to your local machine.
   ```bash
   https://github.com/himmat12/chess_board_game.git
   ```
2. **Build the Project**: Navigate to the project directory and use SBT to build the project.
   ```bash
   cd chess_board_game
   sbt compile
   ```
3. **Run the Game**: Once the project is compiled successfully, you can run the game.
   ```bash
   sbt run
   ```

## How to Play

- **Game Setup**: The game starts with a standard chess board setup with pieces placed in their initial positions.
- **Move Input**: To make a move, enter the chess piece value and then destination position of the piece you want to move. For example, move "wp5" white pawn to "f3" from its initial position "f2".
- **Game Progression**: The game alternates between players, allowing each player to make a move until checkmate or stalemate is reached.
- **Check and Checkmate**: The game will notify players if a king is in check or if a checkmate has occurred (checkmate and stalemate are still in development).

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- This project was inspired by the love for chess and the desire to learn Scala.
- Special thanks to the Scala community and Chess programming community for providing valuable resources and support.

---
