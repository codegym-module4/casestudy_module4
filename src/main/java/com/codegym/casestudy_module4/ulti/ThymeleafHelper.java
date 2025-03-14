package com.codegym.casestudy_module4.ulti;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.method.HandlerMethod;

@Component
public class ThymeleafHelper {
    private final RequestMappingHandlerMapping handlerMapping;
    private final HttpServletRequest request;

    public ThymeleafHelper(RequestMappingHandlerMapping handlerMapping, HttpServletRequest request) {
        this.handlerMapping = handlerMapping;
        this.request = request;
    }

    public String getControllerName() {
        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if (handler instanceof HandlerMethod handlerMethod) {
            return handlerMethod.getBeanType().getSimpleName();
        }
        return "UnknownController";
    }

    public String getMethodName() {
        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if (handler instanceof HandlerMethod handlerMethod) {
            return handlerMethod.getMethod().getName();
        }
        return "UnknownMethod";
    }
}
