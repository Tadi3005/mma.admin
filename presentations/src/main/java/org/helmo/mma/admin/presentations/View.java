package org.helmo.mma.admin.presentations;

import java.util.List;
import java.util.Map;

/**
 * Represents the view of the application
 */
public interface View {
    /**
     * To display a message
     * @param message the message to display
     */
    void display(String message);

    void displayCalendar(String[] header, Map<String, Boolean[]> data);
    /**
     * To display an error message
     * @param message the error message to display
     */
    void displayError(String message);

    /**
     * To ask a question
     * @param message the question to ask
     * @return the answer
     */
    String ask(String message);

    /**
     * To get the directory of files
     * @return the directory of files
     */
    String getResource();
}
