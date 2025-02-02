package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// MVP for Maze Runner: Handle single commands for maze movements with -i <file path> support.

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Error, " + e.getMessage());
            return;
        }

        String maze_file_path = cmd.getOptionValue("input");
        logger.info("**** Reading the maze from file: " + maze_file_path);
        GenerateMaze maze_generator = new GenerateMaze();
        String[][] maze;
        try {
            maze = maze_generator.generateMazeFromFile(maze_file_path);
        } catch (IOException e) {
            logger.error("Error, " + e.getMessage());
            return;
        }

        int[] start_position = maze_generator.getStartPosition(maze);
        Player player = new Player(start_position[0], start_position[1], "UP");
        maze[start_position[0]][start_position[1]] = player.getDirectionSymbol();

        PrintMaze printMaze = new PrintMaze();
        Scanner sc = new Scanner(System.in);
        String player_move;

        while (true) {
            logger.info("**** Maze with Player:");
            printMaze.printMaze(maze);
            System.out.println("Enter move (L - turn left, F - move forward, R - turn right): ");
            player_move = sc.nextLine().toUpperCase();

            if (player_move.equals("L")) {
                player.turnLeft();
                maze[player.current_row][player.current_col] = player.getDirectionSymbol();
            } else if (player_move.equals("R")) {
                player.turnRight();
                maze[player.current_row][player.current_col] = player.getDirectionSymbol();
            } else if (player_move.equals("F")) {
                player.moveForward(maze);

                if (player.hasSucceeded(maze)) {
                    System.out.println("Congratulations! You have won the Maze Runner!");
                    break;
                }
            } else {
                logger.error("Invalid move entered: " + player_move);
                System.out.println("Invalid move.");
            }
        }

        logger.info("** End of Maze Runner");
    }
}
