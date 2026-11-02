package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.ResiduoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResiduoSteps {

    ResiduoService residuoService = new ResiduoService();

    @Dado("que eu tenha os seguintes dados do resíduo:")
    public void queEuTenhaOsSeguintesDadosDoResíduo(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows){
            residuoService.setFieldsResiduo(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de resíduos")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeResíduos(String endPoint) {
        residuoService.createResiduo(endPoint);
    }

    @Então("o status code da resposta de resíduos deve ser {int}")
    public void oStatusCodeDaRespostaDeResíduosDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, residuoService.response.statusCode());
    }

    @E("a mensagem de erro de resíduos deve retornar {string}")
    public void aMensagemDeErroDeResíduosDeveRetornar(String mensagem) {
        ErrorMessageModel errorMessageModel = residuoService.gson.fromJson(residuoService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertTrue(errorMessageModel.getMensagem().contains(mensagem));
    }

    @Quando("eu enviar a requisição DELETE para o endpoint {string} de deleção de resíduos")
    public void euEnviarARequisiçãoDELETEParaOEndpointDeDeleçãoDeResíduos(String endPoint) {
        residuoService.deleteResiduo(endPoint);
    }

    @Dado("que exista um residuo cadastrado com o ID {string}")
    public void queExistaUmResiduoCadastradoComOID(String id) {
        residuoService.retrieveIdResiduo();
    }

    @Dado("que não exista um resíduo cadastrado com o ID {string}")
    public void queNãoExistaUmResíduoCadastradoComOID(String id) {
        residuoService.setIdResiduo(id);
    }

    @E("a resposta de resíduos deve estar em conformidade com o contrato {string}")
    public void aRespostaDeResíduosDeveEstarEmConformidadeComOContrato(String contract) throws IOException {
        residuoService.setContract(contract);
    }

    @Então("a resposta da requisição de resíduos deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeResíduosDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = residuoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}

