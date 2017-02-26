package org.vaadin.i18n.freemarker;

import freemarker.template.Configuration;
import org.junit.Test;
import org.vaadin.i18n.api.TranslationException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FunctionalityTest {

    @Test
    public void test_german() throws TranslationException {
        final FreemarkerTranslator germanTranslator = new TestTranslator(Locale.GERMANY);

        final String actual = germanTranslator.translate("test_text.ftl");

        assertEquals("hallo Robert Smith! Bla Bla Bla. Mit freundlichen Grüßen", actual);
    }

    @Test
    public void test_english() throws TranslationException {
        final FreemarkerTranslator englishTranslator = new TestTranslator(Locale.ENGLISH);

        final String actual = englishTranslator.translate("test_text.ftl");

        assertEquals("hello Robert Smith! Bla Bla Bla. Kind regards", actual);
    }

    @Test
    public void test_fallback() throws TranslationException {
        final FreemarkerTranslator chineseTranslator = new TestTranslator(Locale.TRADITIONAL_CHINESE);

        final String actual = chineseTranslator.translate("test_text.ftl");

        assertEquals("hello Robert Smith, this is the fallback template", actual);
    }

    private static class TestTranslator extends FreemarkerTranslator {

        private final Locale locale;

        private TestTranslator(Locale locale) {
            this.locale = locale;
        }

        @Override
        protected Configuration createConfiguration() {
            final Configuration configuration = new Configuration();
            configuration.setClassForTemplateLoading(getClass(), "templates");
            return configuration;
        }

        @Override
        protected Map<String, Object> getParametersMap() {
            Map<String, Object> map = new HashMap<>();

            map.put("user", "Robert Smith");

            return map;
        }

        @Override
        protected Locale getCurrentLocale() {
            return locale;
        }
    }
}
