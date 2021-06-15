package com.epam.ld.module2.testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Mail server class.
 */
public class MailServer {

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        String content = "Just sent message to the following addresses: " + addresses +
                ". Message content: \n" + messageContent;
        try {
            if (System.getProperty("runMode").equals("file")) {
                Files.write(Paths.get("src/test/resources/").resolve(System.getProperty("outputFile")), content.getBytes());
            }
        } catch (NullPointerException e) {
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + e.getLocalizedMessage());
        }
    }
}
