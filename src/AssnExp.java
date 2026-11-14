public class AssnExp extends Expr {
	public final String id;
	public final Expr e;

	public AssnExp(String _id, Expr _e) {
		id = _id;
		e = _e;
	}

	public Value eval(Env env) throws EvalError {
		Value oldValue = env.lookup(id);
		Value newValue = e.eval(env);

		if (!sameType(oldValue, newValue))
			throw new EvalError("Incompatible types in assignment");

		env.updateBinding(id, newValue);
		return newValue;
	}

	private static boolean sameType(Value v1, Value v2) {
		if (v1 instanceof IntVal)
			return v2 instanceof IntVal;
		if (v1 instanceof BoolVal)
			return v2 instanceof BoolVal;
		if (v1 instanceof FunVal)
			return v2 instanceof FunVal;
		return false;
	}

	public String toString() {
		return "(" + id + ":=" + e + ")";
	}
}
