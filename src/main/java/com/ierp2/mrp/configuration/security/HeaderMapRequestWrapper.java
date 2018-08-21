package com.ierp2.mrp.configuration.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private Map<String, String> headerMap = new HashMap<String, String>();

    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        if (headerMap.containsKey(name)) {
            return headerMap.get(name);
        }
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : headerMap.keySet()) {
            if (!names.contains(name)) {
                names.add(name);
            }
        }
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if (headerMap.containsKey(name)) {
            List<String> values = new ArrayList<>();
            values.add(headerMap.get(name));
            return Collections.enumeration(values);
        } else {
            return super.getHeaders(name);
        }
    }
}
