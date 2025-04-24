package com.luecardoso.projetos.controleacesso.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        String secretHeader = request.getHeader("x-secret");
        System.out.println("[DEBUG] x-secret header value: " + secretHeader);
        if (secretHeader != null && secretHeader.equals("secr3t")) {
            System.out.println("Valid secret header. Setting authentication.");

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    "muito secreto", null, List.of(new SimpleGrantedAuthority("ADMIN")));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication set: " + SecurityContextHolder.getContext().getAuthentication());
            filterChain.doFilter(request, response);
        } else {
            System.out.println("Invalid or missing secret header.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Cabeçalho x-secret inválido ou ausente");
        }
    }
}
