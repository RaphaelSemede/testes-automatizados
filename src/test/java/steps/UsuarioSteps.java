package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.PendingException;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import model.UsuarioModel;
import org.junit.Assert;
import services.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UsuarioSteps {

    UsuarioService usuarioService = new UsuarioService();

    @Dado("que eu tenha os seguintes dados do usuário:")
    public void queEuTenhaOsSeguintesDadosDoUsuário(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows){
            usuarioService.setFieldsUsuario(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuários")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuários(String endPoint) {
        usuarioService.createUsuario(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, usuarioService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String mensagem) {
        ErrorMessageModel errorMessageModel = usuarioService.gson.fromJson(usuarioService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertTrue(errorMessageModel.getMensagem().contains(mensagem));
    }

    @Dado("que exista um usuário cadastrado com o ID {string}")
    public void queExistaUmUsuárioCadastradoComOID(String id) {
        usuarioService.retrieveIdUsuario();
    }

    @Quando("eu enviar a requisição DELETE para o endpoint {string}")
    public void euEnviarARequisiçãoDELETEParaOEndpoint(String endPoint) {
        usuarioService.deleteUsuario(endPoint);
    }

    @Dado("que não exista um usuário cadastrado com o ID {string}")
    public void queNãoExistaUmUsuárioCadastradoComOID(String id) {
        usuarioService.setIdUsuario(id);
    }

    @E("a resposta deve estar em conformidade com o contrato {string}")
    public void aRespostaDeveEstarEmConformidadeComOContrato(String contract) throws IOException {
        usuarioService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = usuarioService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
