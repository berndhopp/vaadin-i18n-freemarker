package org.lisa.freemarker;

import freemarker.template.Configuration;

import java.util.HashMap;
import java.util.Map;

class TestTranslator extends FreemarkerTranslator {

    TestTranslator() {
        super(
                () -> {
                    Map<String, Object> map = new HashMap<>();

                    map.put("user", "Robert Smith");

                    return map;
                },
                createConfiguration()
        );
    }

    private static Configuration createConfiguration() {
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setClassForTemplateLoading(FunctionalityTest.class, "templates");
        return configuration;
    }
}
