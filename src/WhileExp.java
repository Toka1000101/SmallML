public class WhileExp extends Expr {
	public final Expr cond;
	public final Expr body;

	public WhileExp(Expr _cond, Expr _body) {
		cond = _cond;
		body = _body;
	}

	public Value eval(Env e) throws EvalError {
		while (true) {
			Value test = cond.eval(e);
			if (!(test instanceof BoolVal))
				throw new EvalError("Evaluation error: condition not boolean");
			if (!((BoolVal) test).value)
				return new BoolVal(false);
			body.eval(e);
		}
	}

	public String toString() {
		return "(while " + cond + " do " + body + ")";
	}
}
