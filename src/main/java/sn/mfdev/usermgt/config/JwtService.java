package sn.mfdev.usermgt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "J3M5N6P8R9SATCVDWEYGZH2K4M5N7Q8R9TBUCVEXFYG2J3K4N6P7Q8SATB\n";
    public String extractUsername(String token) {

        return extractClaims(token,Claims::getSubject);
    }

    //extract a single claims

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return  generateToken(new HashMap<>(), userDetails);
    }
    //permet  to know if a token is valid
    public boolean isTokenValid(String token , UserDetails userDetails){

        final String username = extractUsername(token);
//        System.out.println(isTokenExpired(token));
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        var bool = extractExpiration(token).before(new Date());
//        System.out.println(bool);
        return bool;

    }
    private Date extractExpiration(String token){

        return extractClaims(token,Claims::getExpiration);
    }
    //generation of token
     public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
//            System.out.println(userDetails.getUsername());
         return Jwts.builder()
                 .setClaims(extraClaims)
                 .setSubject(userDetails.getUsername())
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                 .signWith(getSignKey(), SignatureAlgorithm.HS256)
                 .compact();


     }


    private Claims extractAllClaims(String token){
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignKey())//ensure that the message was not change
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
