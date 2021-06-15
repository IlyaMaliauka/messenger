package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.FileReader;
import org.junit.jupiter.api.*;

public class MailServerTest {

    private final MailServer mailServer = new MailServer();
    private final TemplateEngine templateEngine = new TemplateEngine();
    private final Messenger messenger = new Messenger(mailServer, templateEngine);
    private final Client client = new Client();
    private final Template template = new Template();
    private final FileReader fileReader = new FileReader();

    @BeforeAll
    public static void init() {
        System.setProperty("runMode", "file");
        System.setProperty("inputFile", "input");
        System.setProperty("outputFile", "output");
    }

    @Test
    public void testMailServerFileMode() {
        client.setAddresses("testAddress@Gmail.com");
        messenger.sendMessage(client, template);
        String fileOutput = fileReader.getTextFromFile("output");
        Assertions.assertTrue(fileOutput.contains(client.getAddresses()), "Output file does not contain client email");
    }

    @AfterAll
    public static void tearDown() {
        System.clearProperty("runMode");
        System.clearProperty("inputFile");
        System.clearProperty("outputFile");
    }
}
