# SmallML - SML like small pgoramming language
### Commands
##### Generate files - for this run `./compile.sh`
    - javacc ProjLang.jj
    - mkdir -p output
    - cp -r ProjSuppliedFiles/* output
    - cp -r src/* output
    - mkdir -p bin
    - javac -cp output -d bin output/*.java ScanMain.java ParseMain.java
    - cp -r ProjSuppliedFiles/*.class bin

##### For interactive mode
    - java -cp bin ScanMain
    - java -cp bin ParseMain
    - java -cp bin InterpretMain

##### For file input
    - java -cp bin ScanMain < universaltest.txt
    - java -cp bin ParseMain < universaltest.txt
    - java -cp bin InterpretMain < universaltest.txt

### File structure
##### bin - class files (generated from `javac -d bin output/*java ScanMain.java ParseMain.java`)
##### output - java files (generated from `javacc ProjLang.jj`, part 1 of the project)
##### src - custom AST, value, and evaluator classes
##### ProjSuppliedFiles - original supplied files

### Test files
##### `tests/feature-negative-test` - for negative testing, parser should fail.
##### `tests/feature-positive-test` - for positive testing, parser should not fail.
##### `tests/semantics-test/semantics-positive-test` - single-feature interpreter tests that should evaluate successfully.
##### `tests/semantics-test/semantics-negative-test` - single-feature interpreter tests that should produce an evaluation error.
##### `tests/semantics-test/universal-semantics-test` - combined-feature interpreter tests, prefixed with `positive-` or `negative-`.
##### `wsh6-test` tests from worksheet 6.
##### `universal-test` individual all-around test.
##### For individual parser tests: `java -cp bin ParseMain < FILENAME.txt`
##### For individual interpreter tests: `java -cp bin InterpretMain < FILENAME.txt`
##### Parser and semantics tests:
- `java TestRunner.java` - run all tests (default)
- `java TestRunner.java --parser` - run parser tests only with `ParseMain`
- `java TestRunner.java --semantics` - run single-feature semantics tests only with `InterpretMain`
- `java TestRunner.java --universal-semantics` - run universal semantics tests only with `InterpretMain`
- `java TestRunner.java --help` - show available options
