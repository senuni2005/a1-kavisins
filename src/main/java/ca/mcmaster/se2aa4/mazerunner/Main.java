package ca.mcmaster.se2aa4.mazerunner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Maze file path");
        options.addOption("p", true, "Maze path to verify");

        CommandLineParser parser = new DefaultParser();
        try {
            logger.debug("Parsing command line arguments.");
            CommandLine command_line = parser.parse(options, args);

            if (command_line.hasOption("i")) {
                String maze_file_path = command_line.getOptionValue("i");
                logger.info("Maze file path provided: " + maze_file_path);
                GenerateMaze maze = GenerateMaze.getInstance(maze_file_path);

                if (command_line.hasOption("p")) {
                    String user_path = command_line.getOptionValue("p");
                    logger.info("User path provided for verification.");
                    UserPathVerifier verifier = new UserPathVerifier(maze);
                    boolean is_correct = verifier.verifyPath(user_path); // User path verification
                    if (is_correct) {
                        System.out.println("correct path");
                    } else {
                        System.out.println("incorrect path");
                    }
                } else {
                    MazeSolver maze_solver = new RightHandRuleSolver(maze); // Computer algorithm solution
                    logger.info("No user path provided. Using algorithm to solve maze.");
                    String path = maze_solver.solveMaze();

                    ResultFormatter formatter = new ResultFormatter();
                    String factorized_result = formatter.factorizePath(path);
                    System.out.println(factorized_result);
                }
            } else {
                throw new ParseException("-i <Maze file path> is required.");
            }
        } catch (ParseException e) {
            logger.error("Command line parsing error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
