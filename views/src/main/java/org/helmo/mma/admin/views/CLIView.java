package org.helmo.mma.admin.views;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.presentations.View;

import java.util.Map;
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
    public CLIView(String[] args, String option) {
        parseArgs(args, option);
    }

    private void parseArgs(String[] args, String option) {
        OptionParser parser = new OptionParser();
        try {
            parser.accepts(option).withRequiredArg().ofType(String.class).describedAs("The directory containing the calendar file");
            OptionSet options = parser.parse(args);

            if (options.has(option)) {
                this.directory = (String) options.valueOf(option);
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
    public String getResource() {
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

    @Override
    public void displayCalendar(String[] hours, Map<String, Boolean[]> roomsOccupation) {
        System.out.print("|Salle|");
        for (String hour: hours) {
            String hourFormat = "%-3s";
            String formattedHour = String.format(hourFormat, hour.length() == 1 ? "0" + hour : hour);
            System.out.print(formattedHour + "|   |");
        }
        System.out.println();
        for (Map.Entry<String, Boolean[]> entry: roomsOccupation.entrySet()) {
            System.out.print("|" + formatRoomId(entry.getKey()) + "|");
            for (Boolean value: entry.getValue()) {
                System.out.print(value ? "   |" : " X |");
            }
            System.out.println();
        }
    }

    private String formatRoomId(String roomId) {
        return String.format("%-" + 5 + "s", roomId.substring(0, Math.min(roomId.length(), 5)));
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
