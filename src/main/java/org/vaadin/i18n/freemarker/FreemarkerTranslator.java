package org.vaadin.i18n.freemarker;

import com.vaadin.server.VaadinSession;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.i18n.api.TranslationException;
import org.vaadin.i18n.api.Translator;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * A {@link Translator} based on the apache freemarker framework
 *
 * @author Bernd Hopp
 * @see <a href="http://freemarker.org/">freemarker documentation</a>
 */
@SuppressWarnings("unused")
public abstract class FreemarkerTranslator implements Translator {

    private static Configuration configuration;
    private final StringWriter stringWriter = new StringWriter();

    /**
     * the {@link Configuration} to use. This method will be called only once and the returned Configuration
     * will be cached as a singleton thereafter.
     *
     * @return the singleton Configuration to use
     */
    protected abstract Configuration createConfiguration();

    /**
     * The parameters map to be used by freemarker, this method needs to be implemented in order
     * to be able to have parameters for org.vaadin.i18n.freemarker.templates. In order to use for example a template like "hello ${user.fullname}",
     * the returned map must have an entry under "user" with an object that has a getFullName() method.
     *
     * @return the ParametersMap
     * @see {@link freemarker.template.Template#process(Object, Writer)}
     */
    protected abstract Map<String, Object> getParametersMap();

    private Configuration getConfiguration() {
        if (configuration == null) {
            configuration = createConfiguration();

            if (configuration == null) {
                throw new NullPointerException("createConfiguration() cannot return null");
            }
        }

        return configuration;
    }

    /**
     * Overwrite this method if you want to draw the Locale not from the VaadinSession but
     * another resource
     */
    protected Locale getCurrentLocale() {
        return VaadinSession.getCurrent().getLocale();
    }

    public String translate(String template) throws TranslationException {

        if (template == null || template.isEmpty()) {
            throw new IllegalArgumentException("'template' cannot be null or empty");
        }

        final Locale currentLocale = requireNonNull(
                getCurrentLocale(),
                "getCurrentLocale() cannot return null"
        );

        final Map<String, Object> parametersMap = requireNonNull(
                getParametersMap(),
                "getParametersMap() cannot return null"
        );

        try {
            synchronized (stringWriter) {
                stringWriter.getBuffer().setLength(0);

                getConfiguration()
                        .getTemplate(template, currentLocale)
                        .process(parametersMap, stringWriter);

                return stringWriter.toString();
            }
        } catch (IOException | TemplateException e) {
            throw new TranslationException(e);
        }
    }
}
