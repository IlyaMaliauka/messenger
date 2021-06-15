package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.FileReader;
import com.epam.ld.module2.testing.utils.exceptions.NoProvidedValueException;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

public class MailServerTest {

     MailServer mailServer = new MailServer();
     TemplateEngine templateEngine = new TemplateEngine();
     Messenger messenger = new Messenger(mailServer, templateEngine);
     Client client = new Client();
     Template template = new Template();
     FileReader fileReader = new FileReader();

    @BeforeAll
    public static void init() {
        System.setProperty("inputFile", "input");
        System.setProperty("outputFile", "output");
    }

    @Test
    public void testMailServerFileMode() {
        client.setAddresses("testAddress@Gmail.com");
        messenger.sendMessage(client, template);
        String fileOutput = fileReader.getLinesFromFile("output").toString();
        assertTrue(fileOutput.contains(client.getAddresses()), "Output file does not contain client email");
    }

    @Test
    public void testMissedPlaceholderThrowsException() {
        System.setProperty("inputFile", "wrongInput");
        assertThrows(NoProvidedValueException.class, () -> messenger.sendMessage(client, template));
    }

    @Test
    public void testTemplateEngineIgnoresExcessiveValues() {
        String excessiveValue = fileReader.getLinesFromFile("input").get(8);
        String fileOutput = fileReader.getLinesFromFile("output").toString();
        assertFalse(fileOutput.contains(excessiveValue), "Output file contains excessive data");
    }

    @AfterAll
    public static void tearDown() {
        System.clearProperty("inputFile");
        System.clearProperty("outputFile");
    }
}
