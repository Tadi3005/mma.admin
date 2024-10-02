/*
 * This source file is an example
 */
package org.helmo.example.infrastructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Repository {
    private static final Logger LOGGER = LogManager.getLogger("org.helmo.example");

    //TODO supprime-moi, je suis un exemple
    public static void greetsFromInfra() {
        LOGGER.warn("Coucou depuis l'infrastructure");
    }
}
