import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {
	private static final String TEST_FEATURE_NEGATIVE = "tests/feature-negative-test";
	private static final String TEST_FEATURE_POSITIVE = "tests/feature-positive-test";
	private static final String TEST_WSH6 = "tests/wsh6-test";
	private static final String TEST_UNIVERSAL = "tests/universal-test";
	private static final String TEST_SEMANTICS_POSITIVE = "tests/semantics-test/semantics-positive-test";
	private static final String TEST_SEMANTICS_NEGATIVE = "tests/semantics-test/semantics-negative-test";
	private static final String TEST_UNIVERSAL_SEMANTICS = "tests/semantics-test/universal-semantics-test";

	void main(String[] args) {
		boolean runParser = false;
		boolean runSemantics = false;
		boolean runUniversalSemantics = false;

		if(args.length == 0) {
			runParser = true;
			runSemantics = true;
			runUniversalSemantics = true;
		} else {
			for(String arg : args) {
				switch(arg) {
					case "--all", "-a", "all" -> {
	runParser = true;
	runSemantics = true;
	runUniversalSemantics = true;
					}
					case "--parser", "-p", "parser" -> runParser = true;
					case "--semantics", "-s", "semantics" -> runSemantics = true;
					case "--universal-semantics", "--universal", "-u", "universal-semantics", "universal" ->
runUniversalSemantics = true;
					case "--help", "-h", "help" -> {
	printUsage();
	return;
					}
default -> {
	IO.println("Unknown option: " + arg);
	printUsage();
	System.exit(1);
}
				}
			}
		}

if(runParser) {
	IO.println("=== PARSER TESTS (ParseMain) ===");
	runTests(TEST_FEATURE_POSITIVE, "ParseMain");
	runTests(TEST_FEATURE_NEGATIVE, "ParseMain");
	runTests(TEST_WSH6, "ParseMain");
	runTests(TEST_UNIVERSAL, "ParseMain");
}

if(runSemantics) {
	IO.println("=== SINGLE-FEATURE SEMANTICS TESTS (InterpretMain) ===");
	runTests(TEST_SEMANTICS_POSITIVE, "InterpretMain");
	runTests(TEST_SEMANTICS_NEGATIVE, "InterpretMain");
}

if(runUniversalSemantics) {
	IO.println("=== UNIVERSAL SEMANTICS TESTS (InterpretMain) ===");
	runTests(TEST_UNIVERSAL_SEMANTICS, "InterpretMain");
}
	}

	private void printUsage() {
		IO.println("Usage: java TestRunner.java [option]");
		IO.println("  (no option)               Run all tests");
		IO.println("  --parser, -p              Run parser tests only (ParseMain)");
		IO.println("  --semantics, -s           Run single-feature semantics tests only (InterpretMain)");
		IO.println("  --universal-semantics, -u Run universal semantics tests only (InterpretMain)");
		IO.println("  --all, -a                 Run all tests");
		IO.println("  --help, -h                Show this help message");
	}

	private void runTests(String pathStr, String mainClass) {
		IO.println("TESTING " + pathStr + " with " + mainClass);
		Path path = Paths.get(pathStr);
		if(!Files.exists(path) || !Files.isDirectory(path)) {
			IO.println("ERROR: Directory not found -> " + path);
			System.exit(1);
		}

		try {
			List<Path> testFiles = Files.walk(path)
				.filter(p -> p.toString().endsWith(".txt"))
				.sorted()
				.collect(Collectors.toList());

			if(testFiles.isEmpty()) {
				IO.println("No .txt test files found in " + path);
				return;
			}

			for(Path testFile : testFiles) {
				IO.println("----------------------------------------");
				IO.println("Running test: " + testFile.getFileName());
				IO.println("----------------------------------------");
				ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", mainClass);
				pb.redirectInput(testFile.toFile());
				pb.redirectErrorStream(true);
				Process process = pb.start();

				try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println("  " + line);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
