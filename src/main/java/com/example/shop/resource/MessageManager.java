package com.example.shop.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Message manager.
 */
public class MessageManager {
    private static final Character UNDERSCORE = '_';
    private static final Character HYPHEN = '-';
    private static final String BUNDLE_NAME = "pagecontent";

    private MessageManager() {}
    public static String getProperty(String key, String locale) {
        String languageTag = locale.replace(UNDERSCORE,HYPHEN);
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME, Locale.forLanguageTag(languageTag));
        return rb.getString(key);
    }
}