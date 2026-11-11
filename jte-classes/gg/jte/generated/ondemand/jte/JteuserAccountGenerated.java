package gg.jte.generated.ondemand.jte;
@SuppressWarnings("unchecked")
public final class JteuserAccountGenerated {
	public static final String JTE_NAME = "jte/userAccount.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,6,6,6,6,9,9,9,14,14,14,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String name, String balance) {
		jteOutput.writeContent("\r\n<div>\r\n    <h1>Account</h1>\r\n    <div>\r\n        <strong>Name:</strong>&nbsp;");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(name);
		jteOutput.writeContent("\r\n    </div>\r\n    <div>\r\n        <strong>Balance:</strong>&nbsp;");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(balance);
		jteOutput.writeContent("\r\n    </div>\r\n    <div>\r\n        <a href=\"/logout\">Log Out</a>\r\n    </div>\r\n</div>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String name = (String)params.get("name");
		String balance = (String)params.get("balance");
		render(jteOutput, jteHtmlInterceptor, name, balance);
	}
}
