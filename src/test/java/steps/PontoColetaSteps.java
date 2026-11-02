package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.PendingException;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.PontoColetaService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PontoColetaSteps {

    PontoColetaService pontoColetaService = new PontoColetaService();

    @Dado("que eu tenha os seguintes dados do ponto de coleta:")
    public void queEuTenhaOsSeguintesDadosDoPontoDeColeta(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows){
            pontoColetaService.setFieldsPontoColeta(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de pontos de coleta")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDePontosDeColeta(String endPoint) {
        pontoColetaService.createPontoColeta(endPoint);
    }

    @Então("o status code da resposta de pontos de coleta deve ser {int}")
    public void oStatusCodeDaRespostaDePontosDeColetaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, pontoColetaService.response.statusCode());
    }

    @E("a mensagem de erro de pontos de coleta deve retornar {string}")
    public void aMensagemDeErroDePontosDeColetaDeveRetornar(String mensagem) {
        ErrorMessageModel errorMessageModel = pontoColetaService.gson.fromJson(pontoColetaService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertTrue(errorMessageModel.getMensagem().contains(mensagem));
    }

    @Dado("que exista um ponto de coleta cadastrado com o ID {string}")
    public void queExistaUmPontoDeColetaCadastradoComOID(String id) {
        pontoColetaService.retrieveIdPontoColeta();
    }

    @Quando("eu enviar a requisição DELETE para o endpoint {string} de deleção de pontos de coleta")
    public void euEnviarARequisiçãoDELETEParaOEndpointDeDeleçãoDePontosDeColeta(String endPoint) {
        pontoColetaService.deletePontoColeta(endPoint);
    }

    @Dado("que não exista um ponto de coleta cadastrado com o ID {string}")
    public void queNãoExistaUmPontoDeColetaCadastradoComOID(String id) {
        pontoColetaService.setIdPontoColeta(id);
    }

    @E("a resposta de pontos de coleta deve estar em conformidade com o contrato {string}")
    public void aRespostaDePontosDeColetaDeveEstarEmConformidadeComOContrato(String contract) throws IOException {
        pontoColetaService.setContract(contract);
    }

    @Então("a resposta da requisição de ponto de coleta deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDePontoDeColetaDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = pontoColetaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
