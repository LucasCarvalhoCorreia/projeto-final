package br.com.rsinet.hub_tdd.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagamentoPage {

	/* Area onde são encontrados os elementos na pagina de pagamentos. */

	/*
	 * Encontra os Editores de Texto, Botões, ComboBox e CheckBox para o campo
	 * correspondente ao nome da variável e atribui na mesma.
	 */
	/* Utilizado para preencher as informações de Login na conta Pay. */
	@FindBy(id = "next_btn")
	private WebElement bt_Next;

	@FindBy(name = "safepay_username")
	private WebElement txt_UserNamePay;

	@FindBy(name = "safepay_password")
	private WebElement txt_PasswordPay;

	@FindBy(name = "save_safepay")
	private WebElement check_SavePay;

	@FindBy(id = "pay_now_btn_SAFEPAY")
	private WebElement bt_Pay;

	@FindBy(xpath = "//*[@id=\"orderPaymentSuccess\"]/h2/span")
	public WebElement lbl_Pago;

	/*
	 * Método utilizado para clicar no botão para a proxima etapa de compra de
	 * produto.
	 */
	public void bt_Next() {
		this.bt_Next.click();
	}

	/* Método utilizado para logar na conta Pay. */
	public void logaContaPay(String txt_UserNamePay, String txt_PasswordPay) {
		this.txt_UserNamePay.clear();
		this.txt_UserNamePay.sendKeys(txt_UserNamePay);
		this.txt_PasswordPay.clear();
		this.txt_PasswordPay.sendKeys(txt_PasswordPay);
	}

	/* Método utilizado para clicar na check box de salvar login da conta Pay. */
	public void check_SavePay() {
		this.check_SavePay.click();
	}

	/* Método utilizado para clicar no botão para comprar o produto. */
	public void bt_Pay() {
		this.bt_Pay.click();
	}

	/*
	 * Método utilizado para aguardar e pegar a mensagem de confirmação de compra da
	 * tela de pagamento.
	 */
	public void pega_Pago(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(this.lbl_Pago));
	}
}
