package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpServletUtils {

    public static Optional<String> getHeader(HttpServletRequest request, String key) {
        Map<String, String> headers = Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, request::getHeader));

        return Optional.ofNullable(headers.get(key));
    }
}
