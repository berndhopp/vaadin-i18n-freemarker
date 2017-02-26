package org.vaadin.i18n.freemarker;

import freemarker.template.Configuration;
import org.junit.Test;
import org.vaadin.i18n.api.TranslationException;

import java.util.Collections;
import java.util.Map;

public class SanityTests {

    @Test(expected = NullPointerException.class)
    public void null_pointer_from_create_configuration_returns_null() throws TranslationException {
        new FreemarkerTranslator() {
            @Override
            protected Configuration createConfiguration() {
                return null;
            }

            @Override
            protected Map<String, Object> getParametersMap() {
                return Collections.EMPTY_MAP;
            }
        }.translate("a");
    }

    @Test(expected = NullPointerException.class)
    public void null_pointer_from_get_parameters_map_returns_null() throws TranslationException {
        new FreemarkerTranslator() {
            @Override
            protected Configuration createConfiguration() {
                return new Configuration();
            }

            @Override
            protected Map<String, Object> getParametersMap() {
                return null;
            }
        }.translate("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegal_argument_from_translate_null() throws TranslationException {
        new FreemarkerTranslator() {
            @Override
            protected Configuration createConfiguration() {
                return new Configuration();
            }

            @Override
            protected Map<String, Object> getParametersMap() {
                return Collections.EMPTY_MAP;
            }
        }.translate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegal_argument_from_translate_empty_string() throws TranslationException {
        new FreemarkerTranslator() {
            @Override
            protected Configuration createConfiguration() {
                return new Configuration();
            }

            @Override
            protected Map<String, Object> getParametersMap() {
                return Collections.EMPTY_MAP;
            }
        }.translate("");
    }

}
