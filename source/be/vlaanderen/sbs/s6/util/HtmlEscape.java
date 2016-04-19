package be.vlaanderen.sbs.s6.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

public class HtmlEscape {
	
	public static String removeHtml(String htmlString){
		return StringEscapeUtils.unescapeHtml(htmlString);
	}
	
	/**
	 * @param args
	 * @throws ParserException 
	 */
	public static void main(String[] args) throws ParserException {
		
		Parser parser = new Parser();
		parser.setInputHTML("<html>test</html>");
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		parser.visitAllNodesWith(visitor);
		String textInPage = visitor.getExtractedText();
		System.out.println(textInPage);

	}

}
