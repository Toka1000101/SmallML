public class FunVal extends Value {
	public final String bvar;
	public final Expr body;
	public final Env env;

	public FunVal(String _bvar, Expr _body, Env _env) {
		bvar = _bvar;
		body = _body;
		env = _env;
	}

	public String toString() {
		return "a function";
	}
}
