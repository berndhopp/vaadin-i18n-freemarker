package org.lisa.freemarker;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class FunctionalityTest {

    @Test
    public void test_german() {
        final FreemarkerTranslator germanTranslator = new TestTranslator();

        final String actual = germanTranslator.translate("test_text.ftl", Locale.GERMANY);

        assertEquals("hallo Robert Smith! Bla Bla Bla. Mit freundlichen Grüßen", actual);
    }

    @Test
    public void test_english() {
        final FreemarkerTranslator englishTranslator = new TestTranslator();

        final String actual = englishTranslator.translate("test_text.ftl", Locale.ENGLISH);

        assertEquals("hello Robert Smith! Bla Bla Bla. Kind regards", actual);
    }

    @Test
    public void test_fallback() {
        final FreemarkerTranslator chineseTranslator = new TestTranslator();

        final String actual = chineseTranslator.translate("test_text.ftl", Locale.TRADITIONAL_CHINESE);

        assertEquals("hello Robert Smith, this is the fallback template", actual);
    }

}
