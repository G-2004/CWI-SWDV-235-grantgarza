package gg.jte.generated.ondemand.jte;
@SuppressWarnings("unchecked")
public final class JteaccountPageGenerated {
	public static final String JTE_NAME = "jte/accountPage.jte";
	public static final int[] JTE_LINE_INFO = {1,1,1,1,1,4,4,8,8,8,8,8,8,9,9,13,13,13,13,35,35,35,36,36,36,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, edu.cwi.UserAccount account) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.jte.JteaccountLayoutGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				gg.jte.generated.ondemand.jte.JteuserAccountGenerated.render(jteOutput, jteHtmlInterceptor, account.name(), account.formatBalance(), account.id());
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        ");
				gg.jte.generated.ondemand.jte.JtetransactionsListGenerated.render(jteOutput, jteHtmlInterceptor, account.transactions());
				jteOutput.writeContent("\r\n\r\n        <h2>New Transaction</h2>\r\n\r\n        <form action=\"/account/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(account.id());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/transactions\" method=\"post\">\r\n            <div>\r\n                <label for=\"transactionType\">Transaction Type</label>\r\n                <select name=\"transactionType\" id=\"transactionType\">\r\n                    <option value=\"DEPOSIT\">Deposit</option>\r\n                    <option value=\"WITHDRAWAL\">Withdrawal</option>\r\n                </select>\r\n            </div>\r\n\r\n            <div>\r\n                <label for=\"amount\">Amount</label>\r\n                <input type=\"number\" name=\"amount\" id=\"amount\"\r\n                    step=\"0.01\" min=\"0.01\" required>\r\n            </div>\r\n\r\n            <div>\r\n                <label for=\"description\">Description</label>\r\n                <input type=\"text\" name=\"description\" id=\"description\">\r\n            </div>\r\n\r\n            <button type=\"submit\">Submit Transaction</button>\r\n        </form>\r\n    ");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.get("title");
		edu.cwi.UserAccount account = (edu.cwi.UserAccount)params.get("account");
		render(jteOutput, jteHtmlInterceptor, title, account);
	}
}
