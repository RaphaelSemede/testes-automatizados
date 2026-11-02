package hook;

import io.restassured.response.Response;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class Hook {
    @Before
    public void limparUsuarioPorEmail() {
        String emailAlvo = "raphael.novo@email.com";

        try {
            Response response = given()
                    .baseUri("https://ecocycle-api-h9fwcsf8exb7h9ed.chilecentral-01.azurewebsites.net")
                    .queryParam("email", emailAlvo)
                    .when()
                    .get("/usuarios");

            String idUsuario = response.jsonPath().getString("find { it.email == '" + emailAlvo + "' }.id");

            if (idUsuario != null && !idUsuario.isEmpty()) {
                given()
                        .baseUri("https://ecocycle-api-h9fwcsf8exb7h9ed.chilecentral-01.azurewebsites.net")
                        .when()
                        .delete("/usuarios/" + idUsuario);

                System.out.println("Usuário antigo apagado com sucesso, ID: " + idUsuario);
            }
        } catch (Exception e) {
            System.out.println("Nenhum usuário anterior encontrado para limpar.");
        }
    }
}