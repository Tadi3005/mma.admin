package org.helmo.mma.admin.views;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.presentations.View;

import java.util.Scanner;

/**
 * Command Line Interface view.
 */
public class CLIView implements View {
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();
    private String directory;

    /**
     * Create a CLI view with the given arguments.
     * @param args the arguments
     */
    public CLIView(String[] args) {
        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        OptionParser parser = new OptionParser();
        try {
            parser.accepts("dir").withRequiredArg().ofType(String.class).describedAs("The directory containing the calendar file");
            OptionSet options = parser.parse(args);

            if (options.has("dir")) {
                this.directory = (String) options.valueOf("dir");
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while parsing the arguments: {}", e.getMessage());
        }
    }

    /**
     * Get the directory containing files
     * @return the directory
     */
    @Override
    public String getDirectory() {
        return directory;
    }

    /**
     * Display a message.
     * @param message the message to display
     */
    @Override
    public void display(String message) {
        System.out.println(message);
    }

    /**
     * Display an error message.
     * @param message the error message to display
     */
    @Override
    public void displayError(String message) {
        System.err.println(message);
    }

    /**
     * Ask a question.
     * @param message the question to ask
     * @return the answer
     */
    @Override
    public String ask(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
