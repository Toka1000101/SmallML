public class LetValExp extends Expr {
	public final String bvar;
	public final Expr bexpr;
	public final Expr body;

	public LetValExp(String _bvar, Expr _bexpr, Expr _body) {
		bvar = _bvar;
		bexpr = _bexpr;
		body = _body;
	}

	public Value eval(Env e) throws EvalError {
		Value value = bexpr.eval(e);
		Env newEnv = e.addBinding(bvar, value);
		return body.eval(newEnv);
	}

	public String toString() {
		return "(let val " + bvar + "=" + bexpr + " in " + body + " end)";
	}
}
