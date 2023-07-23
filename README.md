# Game of Life

## Description

This implementation of Conway's Game of Life as a web service using Kotlin and Spring Boot for backend and React for
frontend. Game of Life is a zero-player game, meaning that its evolution
is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an
initial configuration and observing how it evolves.

## Rules

1. Any live cell with fewer than two live neighbors dies, as if by underpopulation.
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

## Implementation

All game logic calculations are done on the backend side. The frontend is only responsible for displaying the game and
controlling its state. Communication between frontend and backend is done using websockets. For better performance,
backend only returns cells that are alive. Grid of cells is represented
as 1D set of integers, where each integer is a cell position in the grid. For example, a grid with 3x3 dimensions will
be
represented as a set of integers: {0, 1, 2, 3, 4, 5, 6, 7, 8}. Where cell index starts in the top left corner and ends
in the bottom right corner. This representation allows for better performance when calculating next generation, as we do
not need to iterate over all cells in the grid, but only over living cells, and also we are not using an object to
represent a
cell.

## Demo

You can check application [here](https://game-of-life-production-caf7.up.railway.app/index.html)

## How to use it

You can place cells on the grid by clicking on them. You can also remove cells by clicking on them again. When you are
ready, you can start the game by clicking on the "Start" button. You can stop the game by clicking on the "Stop"
button. You can also reset the game by clicking on the "Reset" button. You can change the speed of the game by using the
slider. You can also generate a random universe by
clicking on the "Random" button. You can also place pixels while the game is running.

## How to run it locally

```shell
   git clone https://github.com/Thoroldvix/game-of-life.git
   cd repo_directory
   ./mvnw spring-boot:run -Pprod
```

After this you can access the application at http://localhost:8080/index.html







    
