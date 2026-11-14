// A driver class for invoking/running the generated scanner. Reads from System.in.
// Do not change that. Use input redirection instead to read from a file.
// Terminate input by typing end-of-file (ctrl-d on Mac and Linux/Unix and ctrl-z on Windows).
public class ScanMain {
	public static void main(String[] args) {
		SimpleCharStream cstream = new SimpleCharStream(System.in);
		ProjLangParserTokenManager scanner = new ProjLangParserTokenManager(cstream);
		try {
			Token token = scanner.getNextToken();
			while (token.kind != ProjLangParserConstants.EOF) {
				String name = ProjLangParserConstants.tokenImage[token.kind];
				System.out.println("Token: " + name + " Lexeme: " + token.image);
				token = scanner.getNextToken();
			}
		} catch(TokenMgrError e) {
			System.out.println("Scan error:\n" + e.getMessage());
		}
	}
}
