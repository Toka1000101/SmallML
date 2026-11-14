public class IfExp extends Expr {
	public final Expr cond;
	public final Expr arg1;
	public final Expr arg2;

	public IfExp(Expr _cond, Expr _arg1, Expr _arg2) {
		cond = _cond;
		arg1 = _arg1;
		arg2 = _arg2;
	}

	public Value eval(Env e) throws EvalError {
		Value test = cond.eval(e);
		if (!(test instanceof BoolVal))
			throw new EvalError("Evaluation error: test not a boolean");
		return ((BoolVal) test).value ? arg1.eval(e) : arg2.eval(e);
	}

	public String toString() {
		return "(if " + cond + " then " + arg1 + " else " + arg2 + ")";
	}
}
