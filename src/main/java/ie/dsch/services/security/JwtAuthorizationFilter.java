package ie.dsch.services.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        super(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(jwtTokenProvider.getHeaderString());

        if (header == null || !header.startsWith(jwtTokenProvider.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuth = jwtTokenProvider.getAuthenticationToken(request);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
        chain.doFilter(request, response);
    }
}
