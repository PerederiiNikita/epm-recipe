module recipe.web.api {
    requires recipe.services.api;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires spring.core;
    requires slf4j.api;
    opens com.epm.recipe.web_api.config to spring.core;
    opens com.epm.recipe.web_api.controller to spring.web;
    exports com.epm.recipe.web_api.config;
    exports com.epm.recipe.web_api.advice;
    exports com.epm.recipe.web_api.exception;
}