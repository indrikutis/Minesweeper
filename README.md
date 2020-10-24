# Minesweeper

## Table of Contents

* [About the Project](#about-the-project)
* [Getting Started](#getting-started)
  * [Installation](#installation)
  * [Running](#running)
* [How to play](#how-to-play)
  * [Rules](#rules)
  * [Controllers](#controllers)
* [Running environments](#running-environments)



## About The Project

Minesweeper is one of the most popular online puzzle games which involves logical thinking and a bit of guessing to win it.

## Getting Started

Here you can find how to set up the project locally just by following these simple steps.

### Installation

1. Clone this repository to your local machine using: git clone https://github.com/indrikutis/Minesweeper.git

### Running 

Game can be run by using the command line.

1. Go to Minesweeper/dist folder
2. Compile by using: javac -d ../target -cp ../target *.java
3. Run by using: java -cp ../target Driver

## How to play

### Rules

The aim of the game is simple - clear the board which contains bombs without detonating any of them. 

Start the game by clicking any of the squares on a board. Once a player does that, he/she can get a clue with a number of bombs that surrounds that square, a blank square which indicates that there aren't any bombs around or a bomb which would indicate the end of the game.


### Controllers

Left mouse click reveals the square.
Right mouse click puts/removes a flag, which can be used to mark squares where the player thinks a bomb is.


## Running environments

Game is supported by most machines (Windows, Linux, Mac included) running java.

Game was tested on Windows with java version "1.8.0_161".

