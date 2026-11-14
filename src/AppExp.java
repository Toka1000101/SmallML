public class AppExp extends Expr {
	public final String id;
	public final Expr arg;

	public AppExp(String _id, Expr _arg) {
		id = _id;
		arg = _arg;
	}

	public Value eval(Env e) throws EvalError {
		Value function = e.lookup(id);
		if (!(function instanceof FunVal))
			throw new EvalError("Not a function: " + id);

		Value argValue = arg.eval(e);
		FunVal funValue = (FunVal) function;
		Env callEnv = funValue.env.addBinding(funValue.bvar, argValue);
		return funValue.body.eval(callEnv);
	}

	public String toString() {
		return id + "(" + arg + ")";
	}
}
