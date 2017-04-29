package org.lisa.freemarker;

import freemarker.template.Configuration;

import org.junit.Test;

import java.util.Collections;
import java.util.Locale;

@SuppressWarnings("unchecked")
public class SanityTests {

    @Test(expected = NullPointerException.class)
    public void null_pointer_from_create_configuration_throws_NPE() {
        new FreemarkerTranslator(() -> Collections.EMPTY_MAP, null) {
        };
    }

    @Test(expected = NullPointerException.class)
    public void null_pointer_from_get_parameters_map_throws_NPE() {
        new FreemarkerTranslator(null, new Configuration());
    }

    @Test(expected = NullPointerException.class)
    public void npe_from_translate_null() {
        new FreemarkerTranslator(() -> Collections.EMPTY_MAP, new Configuration(Configuration.VERSION_2_3_26)).translate(null, Locale.GERMANY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegal_argument_from_translate_empty_string() {
        new FreemarkerTranslator(() -> Collections.EMPTY_MAP, new Configuration(Configuration.VERSION_2_3_26)).translate("", Locale.GERMANY);
    }


    @Test(expected = NullPointerException.class)
    public void npe_from_null_locale() {
        new FreemarkerTranslator(() -> Collections.EMPTY_MAP, new Configuration(Configuration.VERSION_2_3_26)).translate("asdf", null);
    }
}
