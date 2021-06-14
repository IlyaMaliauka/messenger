package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        String input = template.templateMessage;
        Map<String, String> replacementStrings = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String regexp = "#\\{(.+?)}";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            System.out.println("Please enter " + matcher.group(1).toLowerCase() + " :");
            try {
                replacementStrings.put(matcher.group(1), scanner.nextLine());
            } catch (NoSuchElementException e) {
                replacementStrings.put(matcher.group(1), "TEST_" + matcher.group(1));
            }
        }

        StrSubstitutor sub = new StrSubstitutor(replacementStrings, "#{", "}");
        return sub.replace(input);
    }
}
