package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TemplateEngineTest {

    TemplateEngine testTemplateEngine = new TemplateEngine();
    Client testClient = new Client();
    Template testTemplate = new Template();

    @Test
    public void testTemplateEngineReplacesPlaceholders() {
        testTemplateEngine.generateMessage(testTemplate, testClient);
        Assertions.fail();
    }

    @Test
    public void testMissedPlaceholderThrowsException() {
        testTemplateEngine.generateMessage(testTemplate, testClient);
        Assertions.fail();
    }

    @Test
    public void testTemplateEngineIgnoresMissingValues() {
        testTemplateEngine.generateMessage(testTemplate, testClient);
        Assertions.fail();
    }
}
