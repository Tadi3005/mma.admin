package org.helmo.mma.admin.presentations;

public interface View {
    void display(String message);
    void displayError(String message);
    String ask(String message);
    String getDirectory();
}
