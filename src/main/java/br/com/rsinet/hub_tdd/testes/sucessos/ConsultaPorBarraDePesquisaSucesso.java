package br.com.rsinet.hub_tdd.testes.sucessos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.rsinet.hub_tdd.utils.PegaMassa;
import br.com.rsinet.hub_tdd.pageFactory.HomePage;
import br.com.rsinet.hub_tdd.pageFactory.PesquisaPage;
import br.com.rsinet.hub_tdd.utils.Constantes;
import br.com.rsinet.hub_tdd.utils.DriverFactory;
import br.com.rsinet.hub_tdd.utils.ExcelUtils;
import br.com.rsinet.hub_tdd.utils.Prints;

public class ConsultaPorBarraDePesquisaSucesso extends DriverFactory {

	private WebDriver driver;
	private HomePage homePage;
	private PesquisaPage pesquisaPage;
	private PegaMassa pegaMassa;

	/* Área responsavel pelos testes de sucesso. */

	/* @ responsavel por executar todos os elementos antes do @Test. */
	@BeforeMethod
	public void inicio() throws Exception {
		driver = DriverFactory.iniciaBrowser(DriverType.Chrome, Constantes.URL);

		/* Comando responsavel por ler o arquivo e aba do excel especificados. */
		ExcelUtils.setExcelFile(Constantes.Path_TestData + Constantes.File_TestData, "PesquisaBarra");
		/*
		 * Comando responsavel por iniciar os elementos dentro da pageFactory
		 * especificada.
		 */
		homePage = PageFactory.initElements(driver, HomePage.class);
		pesquisaPage = PageFactory.initElements(driver, PesquisaPage.class);
		pegaMassa = new PegaMassa();
	}

	/* @ responsavel por executar a pilha de testes. */
	@Test
	public void consultaProdutoPorBarraDePesquisa() throws Exception {
		homePage.bt_Lupa();

		/*
		 * Atribui o valor recebido pela coluna e linha especificada no arquivo excel a
		 * uma variavel
		 */
		String txt_Pesquisa = pegaMassa.PesquisaNaBarra();
		homePage.pesquisar(txt_Pesquisa);

		homePage.bt_FechaSugestao(driver);

		pesquisaPage.selecionaProduto(driver);

		/*
		 * Comandos responsaveis por receber os valores necessários para acionar o
		 * assert.
		 */
		String condicao = pegaMassa.CondicaoAssertBarra();
		String mensagem = pegaMassa.MenssagemAssertBarra();
		String elemento = pesquisaPage.desc_Produto.getText();
		/* Comando responsavel por conferir se o teste agiu como o esperado. */
		Assert.assertTrue(elemento.equals(condicao), mensagem);
	}

	/* @ responsaverl por executar todos os elementos depois do @Test. */
	@AfterMethod
	public void fim() throws Exception {
		Prints.tirarPrintsDeSucesso("ConsultaPorBarraDePesquisaSucesso ", driver);
		DriverFactory.fechaBrowser(driver);
	}

}
