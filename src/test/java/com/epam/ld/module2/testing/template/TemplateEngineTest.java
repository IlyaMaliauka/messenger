package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TemplateEngineTest {


    protected TemplateEngine testTemplateEngine = new TemplateEngine();
    protected Client testClient = new Client();
    protected Template testTemplate = new Template();

    @Test
    public void testTemplateEngineReplacesPlaceholders() {
        String regexp = "#\\{(.+?)}";
        Pattern pattern = Pattern.compile(regexp);

        String generatedMessage = testTemplateEngine.generateMessage(testTemplate, testClient);
        Matcher matcher = pattern.matcher(generatedMessage);
        Assertions.assertFalse(matcher.find(), "Placeholders are still in your template!");
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
