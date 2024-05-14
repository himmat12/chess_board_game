# Scala Chess Board Game

![01](https://github.com/himmat12/chess_board_game/assets/48753714/2d16cd58-12ae-4050-b03e-56f371deeb76)

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
![02](https://github.com/himmat12/chess_board_game/assets/48753714/545c3d1c-13eb-4d9e-b513-c1b5e32259aa)
![03](https://github.com/himmat12/chess_board_game/assets/48753714/20ec6813-bd86-462b-a9ed-474e3311a01b)
![04](https://github.com/himmat12/chess_board_game/assets/48753714/7e0277c1-8ded-4e00-a9f4-9bbb355bdf53)
![05](https://github.com/himmat12/chess_board_game/assets/48753714/5c4d613b-dbe7-4694-9523-5db2f15dbaa5)
![06](https://github.com/himmat12/chess_board_game/assets/48753714/51bf01f2-46c0-4e2c-98bc-9237e2a22b4d)
---
