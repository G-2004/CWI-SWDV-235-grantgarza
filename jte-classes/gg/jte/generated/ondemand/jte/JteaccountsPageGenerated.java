package gg.jte.generated.ondemand.jte;
@SuppressWarnings("unchecked")
public final class JteaccountsPageGenerated {
	public static final String JTE_NAME = "jte/accountsPage.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,9,9,9,9,16,16,16,16,16,16,23,23,23,23,25,25,25,26,26,26,27,27,27,27,29,29,29,31,31,36,36,36,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, edu.cwi.User user, java.util.List<edu.cwi.Account> accounts) {
		jteOutput.writeContent("\r\n<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n    <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</title>\r\n    <link rel=\"stylesheet\" href=\"css/universal.css\">\r\n</head>\r\n<body>\r\n    <div class=\"container\">\r\n        <h1>Accounts</h1>\r\n\r\n        <p>Welcome, ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(user.getFirstName());
		jteOutput.writeContent(" ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(user.getLastName());
		jteOutput.writeContent("</p>\r\n\r\n        <a href=\"/logout\">Log Out</a>\r\n\r\n        <h2>Your Accounts</h2>\r\n\r\n        <ul>\r\n            ");
		boolean __jte_for_loop_entered_1 = false;
		for (var account : accounts) {
			__jte_for_loop_entered_1 = true;
			jteOutput.writeContent("\r\n                <li>\r\n                    <strong>");
			jteOutput.setContext("strong", null);
			jteOutput.writeUserContent(account.getAccountType());
			jteOutput.writeContent("</strong>\r\n                    - $");
			jteOutput.setContext("li", null);
			jteOutput.writeUserContent(account.getBalance());
			jteOutput.writeContent("\r\n                    <a href=\"/account/");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(account.getId());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\">View Account</a>\r\n                </li>\r\n            ");
		}
		if (!__jte_for_loop_entered_1) {
			jteOutput.writeContent("\r\n                <li>You have no accounts.</li>\r\n            ");
		}
		jteOutput.writeContent("\r\n        </ul>\r\n\r\n    </div>\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.get("title");
		edu.cwi.User user = (edu.cwi.User)params.get("user");
		java.util.List<edu.cwi.Account> accounts = (java.util.List<edu.cwi.Account>)params.get("accounts");
		render(jteOutput, jteHtmlInterceptor, title, user, accounts);
	}
}
