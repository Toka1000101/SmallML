public class BinExp extends Expr {
	public final BinOp op;
	public final Expr arg1;
	public final Expr arg2;

	public BinExp(BinOp _op, Expr _arg1, Expr _arg2) {
		op = _op;
		arg1 = _arg1;
		arg2 = _arg2;
	}

	public Value eval(Env e) throws EvalError {
		switch (op) {
			case PLUS:
				return new IntVal(intValue(arg1.eval(e)) + intValue(arg2.eval(e)));
			case MINUS:
				return new IntVal(intValue(arg1.eval(e)) - intValue(arg2.eval(e)));
			case TIMES:
				return new IntVal(intValue(arg1.eval(e)) * intValue(arg2.eval(e)));
			case DIV: {
									int divisor = intValue(arg2.eval(e));
									int dividend = intValue(arg1.eval(e));
									if (divisor == 0)
										throw new EvalError("Evaluation error: division by zero");
									return new IntVal(dividend / divisor);
			}
			case EQ: {
								 Value v1 = arg1.eval(e);
								 Value v2 = arg2.eval(e);
								 if (v1 instanceof IntVal && v2 instanceof IntVal)
									 return new BoolVal(((IntVal) v1).value == ((IntVal) v2).value);
								 if (v1 instanceof BoolVal && v2 instanceof BoolVal)
									 return new BoolVal(((BoolVal) v1).value == ((BoolVal) v2).value);
								 throw new EvalError("Evaluation error: Incompatible arg types");
			}
			case LT:
							 return new BoolVal(intValue(arg1.eval(e)) < intValue(arg2.eval(e)));
			case AND: {
									boolean left = boolValue(arg1.eval(e));
									if (!left)
										return new BoolVal(false);
									return new BoolVal(boolValue(arg2.eval(e)));
			}
			case OR: {
								 boolean left = boolValue(arg1.eval(e));
								 if (left)
									 return new BoolVal(true);
								 return new BoolVal(boolValue(arg2.eval(e)));
			}
			default:
							 throw new EvalError("Evaluation error: Unknown binary operator");
		}
	}

	private static int intValue(Value v) throws EvalError {
		if (v instanceof IntVal)
			return ((IntVal) v).value;
		throw new EvalError("Evaluation error: Incompatible arg types");
	}

	private static boolean boolValue(Value v) throws EvalError {
		if (v instanceof BoolVal)
			return ((BoolVal) v).value;
		throw new EvalError("Evaluation error: Incompatible arg types");
	}

	public String toString() {
		String symbol;
		switch (op) {
			case PLUS:  symbol = "+"; break;
			case MINUS: symbol = "-"; break;
			case TIMES: symbol = "*"; break;
			case DIV:   symbol = "/"; break;
			case EQ:    symbol = "="; break;
			case LT:    symbol = "<"; break;
			case AND:   symbol = "&"; break;
			case OR:    symbol = "|"; break;
			default:    symbol = "?"; break;
		}
		return "(" + arg1 + " " + symbol + " " + arg2 + ")";
	}
}
