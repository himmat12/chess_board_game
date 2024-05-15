# Scala Chess Board Game (still in development)

![00](https://github.com/himmat12/chess_board_game/assets/48753714/61640f7c-8905-4b47-ada1-0558ec52a66f)

Welcome to my Scala-based chess board game project!

This project is a simple implementation of chess in Scala. It provides a basic chess board, pieces, and rules to play the game.

## Features

- **Chess Board**: A standard 8x8 chess board.
- **Pieces**: Implementation of all standard chess pieces - King, Queen, Rook, Bishop, Knight, and Pawn.
- **Movement**: Valid movement logic for all pieces according to standard chess rules.
- **Check and Checkmate Detection**: Detection of check and checkmate conditions during gameplay.
- **Simple Text-based Interface**: The game is played via a simple text-based interface in the terminal.

## Requirements

- **Scala 3.3.1 SDK**: This project is built using Scala programming language with Scala 3.3.1 SDK.
- **IntelliJ**: The project was developed using IntelliJ IDE.
- **JUnit 4**: JUnit 4 library is used for unit testing in this project.

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
   ### Or, walkthrough this documentation to setup scala in intellij
   [Getting Started | Scala Documentation](https://docs.scala-lang.org/getting-started/index.html)

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


## Game screenshots
![000](https://github.com/himmat12/chess_board_game/assets/48753714/19cc776f-3638-49c2-af77-8a6286e05844)
![001](https://github.com/himmat12/chess_board_game/assets/48753714/491c9f3c-90a6-46c4-af9c-045e740886bf)
![002](https://github.com/himmat12/chess_board_game/assets/48753714/13e5e2a3-2ff9-49aa-a95d-cef31e6e3970)
![003](https://github.com/himmat12/chess_board_game/assets/48753714/29f1e314-69b4-429b-ae33-c6f92826895e)
![004](https://github.com/himmat12/chess_board_game/assets/48753714/3ec1a779-ecaf-4031-9a42-1505c8650e60)
![005](https://github.com/himmat12/chess_board_game/assets/48753714/4bb4d9e7-35ba-4797-9bca-b45868046162)

---
