package br.com.rsinet.hub_tdd.falhas;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.rsinet.hub_tdd.pageFactory.HomePage;
import br.com.rsinet.hub_tdd.util.Constant;
import br.com.rsinet.hub_tdd.util.DriverFactory;
import br.com.rsinet.hub_tdd.util.ExcelUtils;
import br.com.rsinet.hub_tdd.util.Prints;

public class ConsultaPorBarraDePesquisaErrado extends DriverFactory {

	private WebDriver driver;
	private HomePage homePage;

	@BeforeMethod
	public void inicio() throws Exception {
		driver = DriverFactory.iniciaBrowser();
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "PesquisaBarra");
		homePage = PageFactory.initElements(driver, HomePage.class);
	}

	@Test
	public void consultaPorBarraDePesquisaComProdutoInexistenteNoBanco() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		homePage.bt_Lupa();
		
		String txt_Pesquisa = (ExcelUtils.getCellData(5, Constant.pesquisaBarraErro));
		homePage.pesquisar(txt_Pesquisa);
		
		String elemento = ExcelUtils.getCellData(5, Constant.pesquisaBarraErro);
		String resposta = homePage.result_Produto.getText();
		Assert.assertTrue(resposta.equals("No results for " + "\"" + elemento + "\""), "Nenhum resultado encontrado para "+elemento+"!");
	}

	@AfterMethod
	public void fim() throws Exception {
		Prints.tirarPrintsDeFalha("ConsultaPorBarraDePesquisaErrado ", driver);
		DriverFactory.fechaBrowser(driver);
	}

}
