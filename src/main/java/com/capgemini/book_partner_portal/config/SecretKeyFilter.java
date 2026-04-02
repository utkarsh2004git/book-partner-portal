package com.capgemini.book_partner_portal.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Order(1) // Ensures this is the absolute first thing that runs
public class SecretKeyFilter implements Filter {

    // The secret password only your frontend knows
    private static final String SECRET_KEY = "BulletProofDemo2026!";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Skip the filter for Swagger/API-docs if you want your mentor to see them
        String path = req.getRequestURI();
        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
            chain.doFilter(request, response);
            return;
        }

        String incomingKey = req.getHeader("X-Project-Secret");

        // If a malicious user found your IP via Nmap and tries to hit it directly:
        if (incomingKey == null || !incomingKey.equals(SECRET_KEY)) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.setContentType("application/json");
            res.getWriter().write("{\"error\": \"Nice try! Direct backend access is forbidden. Use the UI.\"}");
            return; // Stops the attack dead in its tracks
        }

        // If the password matches (it came from your frontend), let it through
        chain.doFilter(request, response);
    }
}