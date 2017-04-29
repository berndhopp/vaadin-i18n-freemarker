package org.lisa.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import org.lisa.api.Translator;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * A {@link Translator} based on the apache freemarker framework
 *
 * @author Bernd Hopp
 * @see <a href="http://freemarker.org/">freemarker documentation</a>
 */
public class FreemarkerTranslator implements Translator {

    private final Supplier<Map<String, Object>> parametersSupplier;
    private final Configuration configuration;

    public FreemarkerTranslator(Supplier<Map<String, Object>> parametersSupplier, Configuration configuration) {
        this.parametersSupplier = Objects.requireNonNull(parametersSupplier);
        this.configuration = Objects.requireNonNull(configuration);
    }

    public String translate(String template, Locale currentLocale) {
        Objects.requireNonNull(currentLocale);
        Objects.requireNonNull(template);

        if (template.isEmpty()) {
            throw new IllegalArgumentException("'template' cannot be empty");
        }

        final Map<String, Object> parametersMap = requireNonNull(
                parametersSupplier.get(),
                "parametersSupplier cannot return null"
        );

        try {
            Writer writer = new StringWriter();

            configuration
                    .getTemplate(template, currentLocale)
                    .process(parametersMap, writer);

            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
