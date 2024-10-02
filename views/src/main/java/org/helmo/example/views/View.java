/*
 * This source file is an example
 */
package org.helmo.example.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class View {
    private static final Logger LOGGER = LogManager.getLogger("org.helmo.example");

    //TODO supprime-moi, je suis un exemple
    public static void greets() {
        LOGGER.info("Bonjour de la vue");
    }
}
