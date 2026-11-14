// A driver class for invoking/running the generated parser. Reads from System.in.
// Do not change that. Use input redirection instead to read from a file.
// Terminate input by typing end-of-file (ctrl-d on Mac and Linux/Unix and ctrl-z on Windows).
public class ParseMain {
	public static void main(String[] args) {
		try {
			ProjLangParser parser = new ProjLangParser(System.in);
			parser.input();
			System.out.println("Parse successful");
		} catch (Exception e) {
			System.out.println("Parse error:\n" + e.getMessage());
		} catch(TokenMgrError e) {
			System.out.println("Scan error:\n" + e.getMessage());
		}

	}
}
