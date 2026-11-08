package gg.jte.generated.ondemand.jte;
import gg.jte.Content;
@SuppressWarnings("unchecked")
public final class JteaccountLayoutGenerated {
	public static final String JTE_NAME = "jte/accountLayout.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,2,11,11,11,11,17,17,17,20,20,20,25,25,25,2,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, Content account, Content detail) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"en\">\r\n    <head>\r\n        <meta charset=\"utf-8\">\r\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n        <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</title>\r\n    </head>\r\n    <body>\r\n        <div class=\"container\">\r\n            <div class=\"row align-items-start\">\r\n                <div class=\"col\">\r\n                    ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(account);
		jteOutput.writeContent("\r\n                </div>\r\n                <div class=\"col\">\r\n                    ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(detail);
		jteOutput.writeContent("\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.get("title");
		Content account = (Content)params.get("account");
		Content detail = (Content)params.get("detail");
		render(jteOutput, jteHtmlInterceptor, title, account, detail);
	}
}
