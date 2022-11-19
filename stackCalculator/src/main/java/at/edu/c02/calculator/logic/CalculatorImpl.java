package at.edu.c02.calculator.logic;

import java.util.Stack;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;


public class CalculatorImpl implements Calculator {

	private Stack<Double> stack_ = new Stack<Double>();

	@Override
	public double perform(Operation op) throws CalculatorException {

		double b = pop();
		double a = pop();

		switch (op) {
		case add:
			return a + b;
		case sub:
			return a - b;
		case div:
			double c = a / b;
			if (Double.isInfinite(c))
				throw new CalculatorException("Division by zero");
			return c;
		case mul:
			return a * b;
		case modulo:
			double test = a % b;
			if(Double.isInfinite(test)){
				throw new CalculatorException("Modulo by zero");
			}
			return test;


		}
		return 0;
	}

	@Override
	public double perform1Var(Operation op) throws CalculatorException
	{
		double a = pop();

		switch (op)
		{
			case sin:
				return Math.sin(a);
			case cos:
				return Math.cos(a);
			default:
				throw new CalculatorException("Invalid operation for single variable");
		}
	}

	@Override
	public double pop() throws CalculatorException {
		if (stack_.isEmpty())
			throw new CalculatorException();
		return stack_.pop();
	}

	@Override
	public void push(double v) {
		stack_.push(v);
	}

	@Override
	public void clear() {
		stack_.clear();
	}

}
