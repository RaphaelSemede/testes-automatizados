package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.ResiduoModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

public class ResiduoService {
    final ResiduoModel residuoModel = new ResiduoModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "https://ecocycle-api-h9fwcsf8exb7h9ed.chilecentral-01.azurewebsites.net";

    private final ObjectMapper mapper = new ObjectMapper();

    private String getAuthUser() {
        String envUser = System.getenv("API_AUTH_USER");
        if (envUser == null || envUser.isEmpty()) {
            envUser = System.getProperty("API_AUTH_USER");
        }
        return (envUser != null && !envUser.isEmpty()) ? envUser : "user";
    }

    private String getAuthPassword() {
        String envPass = System.getenv("API_AUTH_PASS");
        if (envPass == null || envPass.isEmpty()) {
            envPass = System.getProperty("API_AUTH_PASS");
        }
        return (envPass != null && !envPass.isEmpty()) ? envPass : "13a3721f-0be1-4382-97e5-cad19c88c644";
    }

    public void setFieldsResiduo(String field, String value) {
        switch(field){
            case "tipo" -> residuoModel.setTipo(value);
            case "peso" -> residuoModel.setPeso(new BigDecimal(value));
            case "dataRegistro" -> residuoModel.setDataRegistro(LocalDateTime.parse(value));
            case "statusColeta" -> residuoModel.setStatusColeta(value);
            case "usuarioId" -> residuoModel.setUsuarioId(Integer.parseInt(value));
            default -> throw new IllegalStateException("Campo inesperado: " + field);
        }
    }

    public void createResiduo(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(residuoModel);
        response = given()
                .auth().basic(getAuthUser(), getAuthPassword())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
