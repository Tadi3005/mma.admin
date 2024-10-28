package org.helmo.mma.admin.domains;

public record User(String matricule, String fullName, String email) {

    public User(String email) {
        this("", "", email);
    }
}
