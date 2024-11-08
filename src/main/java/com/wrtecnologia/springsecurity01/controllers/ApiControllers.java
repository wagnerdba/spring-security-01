package com.wrtecnologia.springsecurity01.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiControllers {
    @GetMapping("/public")
    String publicRoute() {
        return "<h1>Rota Pública, acesso liberado</h1>";
    }

    @GetMapping("/private")
    String privateRoute(@AuthenticationPrincipal OidcUser principal) {  //Open ID - Informações do usuário
        return """
                <h1>Rota Privada, acesso autorizado!</h1>
                """;
    }

    @GetMapping("/cookie")
    public Map<String, Object> cookie(@AuthenticationPrincipal OidcUser principal) {
        // Cria um map com as informações do usuário e retorna como JSON
        Map<String, Object> response = new HashMap<>();
        //response.put("principal", principal);
        response.put("email", principal.getAttribute("email"));
        response.put("authorities", principal.getAuthorities());
        response.put("jwt", principal.getIdToken().getTokenValue());

        return response;
    }

    /* // CONSOLE F12
    // Definindo o cookie JSESSIONID sem a opção HttpOnly
    document.cookie = "JSESSIONID=3E03125B3F27DEFF942BAEE91B7024DA; path=/; domain=localhost; expires=Thu, 31 Dec 2099 23:59:59 GMT; secure";

    // Redireciona para a URL desejada após configurar o cookie
    window.location.href = "http://localhost:8080/cookie";
     */

    @GetMapping("/jwt")
    public Map<String, Object> jwt(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "JWT - Rota Privada, acesso autorizado!");
        response.put("principal", jwt.getClaims());
        response.put("email", jwt.getClaims().get("email"));
        response.put("jwtToken", jwt.getTokenValue());
        return response;
    }

    // CONSOLE F12 OU POSTMAN (testar endpoint /jwt)
        /*
        fetch('http://localhost:8080/jwt', {
        method: 'GET', // ou 'POST', conforme necessário
        headers: {
        'Authorization': 'Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImU4NjNmZTI5MmZhMmEyOTY3Y2Q3NTUxYzQyYTEyMTFiY2FjNTUwNzEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIyMjIyMzQ5MjE5MC1xNWtvY2FyaXEyZDNvY2Rnbjhsczg1a2h0cWUwZTY3dS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6IjIyMjIzNDkyMTkwLXE1a29jYXJpcTJkM29jZGduOGxzODVraHRxZTBlNjd1LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAwNjA2MTA3NjEwODAzNjkxODc2IiwiZW1haWwiOiJ3YWduZXJkYmFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiI2UXgwV3VtbVFGUTV4VFQwN2hHcU1BIiwibm9uY2UiOiJFY1Y3emV4b1V0dHVWTG5UVnI1ZDJaVUlybnI4UFI4U2V1MHNXeHpZRVpJIiwibmFtZSI6IldhZ25lciBQaXJlcyIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NKU3Jtdy1JWWRBZUROUEU0QVh2Ym1PNHg1WkRCWnp5RmRxV3FZdmRqeW9VbGFnaWVaZD1zOTYtYyIsImdpdmVuX25hbWUiOiJXYWduZXIiLCJmYW1pbHlfbmFtZSI6IlBpcmVzIiwiaWF0IjoxNzMwOTkxMzczLCJleHAiOjE3MzA5OTQ5NzN9.FcPm6DusPfLkCtx2GvpOswR7nzHxz0gR4VkIqg1R9rybnv5FLdxvPABwyQS6Pk4vKvp0ZyA94n1XTUXCib9WIgNCKn6cSwy7dP1nKgFtW2Z-NgzFm_gm7BcjSP6TETHpEPTSko-ckh1MTpztgd7JTVtTc2SSg1ZL5bxG4IKBen4Y2LUfiPNSCsPx2R_jf2z4DjFbg-18rpu730770cS3LzLAUVJTNm9z51qCdSTxqAEdfSz5cYfxVBeEwJaV00DItgwEUjtQLVjNK0v0dW0tJJnwPbAy8n61uPEBR5YLWBbJZO755BqUtNoLrBMUBBhMppwGGnoAIvZmRAHEHkhvOw'
        }
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Erro:', error));
         */
}
