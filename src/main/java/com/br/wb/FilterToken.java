package com.br.wb;

import com.br.wb.respositories.ClientRepository;
import com.br.wb.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterToken extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final ClientRepository clientRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer1 ", "");
            var subject = this.tokenService.getSubject(token);
            var user = clientRepository.findByEmail(subject).orElseThrow();
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
