package gg.jte.generated.ondemand.jte;
@SuppressWarnings("unchecked")
public final class JtetransactionsPageGenerated {
	public static final String JTE_NAME = "jte/transactionsPage.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,15,15,15,16,16,16,16,16,16,17,17,34,34,34,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<String> transactions) {
		jteOutput.writeContent("\r\n<!DOCTYPE html>\r\n<html>\r\n<head>\r\n    <title>Transactions</title>\r\n</head>\r\n<body>\r\n    <h1>Transactions</h1>\r\n\r\n    <form method=\"post\" action=\"/transactions\">\r\n        <input type=\"text\" name=\"transaction\">\r\n        <button type=\"submit\">Add Transaction</button>\r\n    </form>\r\n\r\n    ");
		for (int i = 0; i < transactions.size(); i++) {
			jteOutput.writeContent("\r\n        <p>ID: ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(i);
			jteOutput.writeContent(" - ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(transactions.get(i));
			jteOutput.writeContent("</p>\r\n    ");
		}
		jteOutput.writeContent("\r\n\r\n    <h2>Update Transaction</h2>\r\n\r\n    <form method=\"post\" action=\"/transactions/update\">\r\n        <input type=\"number\" name=\"id\">\r\n        <input type=\"text\" name=\"transaction\">\r\n        <button type=\"submit\">Update</button>\r\n    </form>\r\n\r\n    <h2>Delete Transaction</h2>\r\n\r\n    <form method=\"post\" action=\"/transactions/delete\">\r\n        <input type=\"number\" name=\"id\">\r\n        <button type=\"submit\">Delete</button>\r\n    </form>\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<String> transactions = (java.util.List<String>)params.get("transactions");
		render(jteOutput, jteHtmlInterceptor, transactions);
	}
}
