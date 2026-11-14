public class LetFunExp extends Expr {
	public final String fname;
	public final String aname;
	public final Expr fbody;
	public final Expr body;

	public LetFunExp(String _fname, String _aname, Expr _fbody, Expr _body) {
		fname = _fname;
		aname = _aname;
		fbody = _fbody;
		body = _body;
	}

	public Value eval(Env e) throws EvalError {
		// Add a dummy binding first so the closure can refer to itself recursively.
		Env newEnv = e.addBinding(fname, new IntVal(0));
		FunVal funValue = new FunVal(aname, fbody, newEnv);
		newEnv.updateBinding(fname, funValue);
		return body.eval(newEnv);
	}

	public String toString() {
		return "(let fun " + fname + "(" + aname + ")=" + fbody
			+ " in " + body + " end)";
	}
}
