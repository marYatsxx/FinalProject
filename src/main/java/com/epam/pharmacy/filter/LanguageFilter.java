package com.epam.pharmacy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {
    private static final String DEFAULT_LANGUAGE = "defaultLanguage";
    private static final String LANGUAGE = "language";
    private String defaultLanguage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLanguage = filterConfig.getInitParameter(DEFAULT_LANGUAGE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object locale = session.getAttribute(LANGUAGE);
        if (locale == null) {
            session.setAttribute(LANGUAGE, defaultLanguage);
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
