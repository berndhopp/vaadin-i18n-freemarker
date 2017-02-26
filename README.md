# REASON

this module can be used to integrate [apache freemarker](http://freemarker.org) into [vaadin-i18n](https://github.com/berndhopp/vaadin-i18n).

# USAGE

## CREATE TRANSLATOR

Subclass [FreemarkerTranslator](https://github.com/berndhopp/vaadin-i18n-freemarker/blob/master/src/main/java/org/vaadin/i18n/freemarker/FreemarkerTranslator.java) and implement
the two methods mentioned below.

```java

class MyTranslator extends FreeMarkerTranslator {
    public Configuration createConfiguration(){
        Configuration configuration = new Configuration();
        
        //... set up configuration
        
        return configuration;
    }
    
    private Map<String, Object> map;
    
    public Map<String, Object> getParametersMap(){
        //we're in UI-scope here, so you may cache the map
        
        if(map == null){
            //... set up map
        }
        
        return map;
    }
}

```

### createConfiguration

here you need to create a [Configuration](http://freemarker.org/docs/pgui_quickstart_createconfiguration.html) instance. This object cannot be null and 
will be cached infinitely and used for every template from this point on.

### getParametersMap

here the current parametersMap should be returned, that is used to fill in parameters as in "hello ${user.fullname}", where the returned map must have an entry under 
"user" with an object that has a getFullName() method.
