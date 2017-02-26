package org.vaadin.i18n.freemarker;

import com.vaadin.server.VaadinSession;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.vaadin.i18n.api.TranslationException;
import org.vaadin.i18n.api.Translator;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings("unused")
public abstract class FreemarkerTranslator implements Translator {

    private static Configuration configuration;
    private final StringWriter stringWriter = new StringWriter();
    private Map<String, Object> parametersMap;

    /**
     * the {@link Configuration} to use. This method will be called only once and the returned Configuration
     * will be cached as a singleton thereafter.
     * @return the singleton Configuration to use
     */
    protected abstract Configuration createConfiguration();

    /**
     * The parameters map to be used by freemarker, overwrite this method if
     * you have parametrized templated. In order to use for example a template like "hello ${user.fullname}",
     * the returned map must have an entry under "user" with an object that has a getFullName() method.
     * @see <a href="http://freemarker.org/">freemarker documentation</a>
     * @see {@link freemarker.template.Template#process(Object, Writer)}
     * @return the ParametersMap
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> getParametersMap(){
        return Collections.EMPTY_MAP;
    }

    private Configuration getConfiguration() {
        if (configuration == null) {
            configuration = createConfiguration();

            if (configuration == null) {
                throw new NullPointerException("createConfiguration() cannot return null");
            }
        }

        return configuration;
    }

    public String translate(String template) throws TranslationException {

        if(template == null || template.isEmpty()){
            throw new IllegalArgumentException("'template' cannot be null or empty");
        }

        try {
            synchronized (stringWriter) {
                stringWriter.getBuffer().setLength(0);

                getConfiguration()
                        .getTemplate(template, VaadinSession.getCurrent().getLocale())
                        .process(getParametersMap(), stringWriter);

                return stringWriter.toString();
            }
        } catch (IOException | TemplateException e) {
            throw new TranslationException(e);
        }
    }
}
