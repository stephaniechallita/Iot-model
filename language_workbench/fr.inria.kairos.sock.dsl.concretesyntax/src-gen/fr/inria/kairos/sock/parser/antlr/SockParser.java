/*
 * generated by Xtext 2.14.0
 */
package fr.inria.kairos.sock.parser.antlr;

import com.google.inject.Inject;
import fr.inria.kairos.sock.parser.antlr.internal.InternalSockParser;
import fr.inria.kairos.sock.services.SockGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class SockParser extends AbstractAntlrParser {

	@Inject
	private SockGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalSockParser createParser(XtextTokenStream stream) {
		return new InternalSockParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "IotSystem";
	}

	public SockGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(SockGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
