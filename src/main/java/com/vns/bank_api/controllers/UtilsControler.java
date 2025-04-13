package com.vns.bank_api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UtilsControler {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("get-urls")
    public Map<String, List<String>> getUrls() {
        Map<String, List<String>> urlList = new HashMap<>();
        
        handlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            Set<String> patterns = requestMappingInfo.getPatternValues();
            Set<RequestMethod> methods = requestMappingInfo.getMethodsCondition().getMethods();
            
            patterns.forEach(pattern -> {
                List<String> methodList = methods.stream()
                    .map(RequestMethod::name)
                    .collect(Collectors.toList());
                urlList.put(pattern, methodList);
            });
        });
        
        return urlList;
    }
}
