package com.epam.pharmacy.filter;

import com.epam.pharmacy.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnregisteredUserFilter implements Filter {
    private static final String COMMANDS_DELIMITER = ",";
    private static final String COMMANDS = "commands";
    private static final String COMMAND = "command";
    private static final String REDIRECT_LOGIN_PAGE = "pharmacy?command=viewLoginPage";

    private List<String> commands = Collections.emptyList();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String commands = filterConfig.getInitParameter(COMMANDS);
        String[] splitCommands = commands.split(COMMANDS_DELIMITER);
        this.commands = Arrays.asList(splitCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.USER);
        String command = request.getParameter(COMMAND);
        if (user == null && command != null && !commands.contains(command)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(REDIRECT_LOGIN_PAGE);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
