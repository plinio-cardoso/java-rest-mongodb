package ie.dsch.services.security;


import ie.dsch.services.model.ApplicationUser;
import ie.dsch.services.service.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
@Data
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.token-prefix:Bearer }")
    private String tokenPrefix;

    @Value("${security.jwt.token.header-string:Authorization}")
    private String headerString;

    @Value("${security.jwt.token.expire-length:10}")
    private long validityInMinutes;

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public String createToken(String username) {

        ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(validityInMinutes, ChronoUnit.MINUTES);

        String token = Jwts.builder().setSubject(username)
                .setExpiration(Date.from(expirationTimeUTC.toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return token;
    }

    public UsernamePasswordAuthenticationToken authenticationTokenFactory(ApplicationUser applicationUser) {
        return new UsernamePasswordAuthenticationToken(
                applicationUser.getUsername(),
                applicationUser.getPassword()
        );
    }

    public String getHeaderToken(String token) {
        return tokenPrefix + " " + token;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token.replace(tokenPrefix, ""))
                .getBody()
                .getSubject();
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(headerString);

        if(token == null) return null;

        String username = this.getUsernameFromToken(token);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        return null;
    }
}
