package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Setter;
import model.UsuarioModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class UsuarioService {
    final UsuarioModel usuarioModel = new UsuarioModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "https://ecocycle-api-h9fwcsf8exb7h9ed.chilecentral-01.azurewebsites.net/";
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    @Setter
    String idUsuario;

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

    public void setFieldsUsuario(String field, String value) {
        switch(field){
            case "nome" -> usuarioModel.setNome(value);
            case "email" -> usuarioModel.setEmail(value);
            case "role" -> usuarioModel.setRole(value);
            case "senha" -> usuarioModel.setSenha(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void createUsuario(String endPoint){
        if (usuarioModel.getEmail() != null && usuarioModel.getEmail().equals("raphael.novo@email.com")) {
            String emailDinamico = "raphael.novo." + System.currentTimeMillis() + "@email.com";
            usuarioModel.setEmail(emailDinamico);
        }

        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(usuarioModel);
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

    public void retrieveIdUsuario(){
        idUsuario = response.jsonPath().getString("id");
    }

    public void deleteUsuario(String endPoint) {
        String url = baseUrl + endPoint + "/" + idUsuario;
        response = given()
                .auth().basic(getAuthUser(), getAuthPassword())
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro de Usuário bem sucedido", "usuario-schema.json" ->
                    jsonSchema = loadJsonFromFile(schemasPath + "usuario-schema.json");
            default -> throw new IllegalStateException("Unexpected contract: " + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {


        JSONObject jsonResponse = new JSONObject(response.getBody().asString());

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());

        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());

        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);

        return schemaValidationErrors;

    }
}