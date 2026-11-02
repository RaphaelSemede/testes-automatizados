package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.ResiduoService;

import java.util.List;
import java.util.Map;

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
}

