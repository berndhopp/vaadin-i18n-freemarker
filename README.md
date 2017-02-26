# REASON

this module can be used to integrate [apache freemarker](http://freemarker.org) into [vaadin-i18n](https://github.com/berndhopp/vaadin-i18n).

# USAGE

## create FreemarkerTranslator

Subclass [FreemarkerTranslator](https://github.com/berndhopp/vaadin-i18n-freemarker/blob/master/src/main/java/org/vaadin/i18n/freemarker/FreemarkerTranslator.java) and implement
the two methods mentioned below.

### createConfiguration

here you need to create a [Configuration](http://freemarker.org/docs/pgui_quickstart_createconfiguration.html) instance. This object cannot be null and 
will be cached infinitely and used for every template from this point on.

### getParametersMap

here the current parametersMap should be returned, that is used to fill in parameters as in "hello ${user.fullname}", where the returned map must have an entry under 
"user" with an object that has a getFullName() method.

## INTEGRATE

### GUICE

subclass



