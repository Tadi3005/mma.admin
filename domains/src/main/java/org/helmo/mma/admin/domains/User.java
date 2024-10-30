package org.helmo.mma.admin.domains;

public record User(String matricule, String fullName, String email) {

    public static final User NOT_FOUND = new User("NOT_FOUND", "NOT_FOUND", "NOT_FOUND");

    /**
     * Constructor with only the email.
     * @param email the email
     */
    public User(String email) {
        this("", "", email);
    }
}
