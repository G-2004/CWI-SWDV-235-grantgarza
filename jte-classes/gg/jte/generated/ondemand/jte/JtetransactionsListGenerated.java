package gg.jte.generated.ondemand.jte;
import java.util.Map;
@SuppressWarnings("unchecked")
public final class JtetransactionsListGenerated {
	public static final String JTE_NAME = "jte/transactionsList.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,2,6,6,6,6,6,7,7,7,7,7,7,8,8,8,10,10,11,11,11,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Map<String, String> transactions) {
		jteOutput.writeContent("\r\n<h1>Transactions</h1>\r\n<ul>\r\n    ");
		boolean __jte_for_loop_entered_1 = false;
		for (var entry : transactions.entrySet()) {
			__jte_for_loop_entered_1 = true;
			jteOutput.writeContent("\r\n        <li>");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(entry.getKey());
			jteOutput.writeContent(": ");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(entry.getValue());
			jteOutput.writeContent("</li>\r\n    ");
		}
		if (!__jte_for_loop_entered_1) {
			jteOutput.writeContent("\r\n        <li>You have no transactions to display.</li>\r\n    ");
		}
		jteOutput.writeContent("\r\n</ul>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Map<String, String> transactions = (Map<String, String>)params.get("transactions");
		render(jteOutput, jteHtmlInterceptor, transactions);
	}
}
