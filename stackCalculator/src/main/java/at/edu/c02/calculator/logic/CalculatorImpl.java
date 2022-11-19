package at.edu.c02.calculator.logic;

import java.util.ArrayList;
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
				if (Double.isInfinite(test)) {
					throw new CalculatorException("Modulo by zero");
				}
				return test;
		}
		return 0;
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

	public double performSkalar(Operation skalar) throws CalculatorException {

		double ergebnis = 0;

		double data = stack_.pop();
		int size = (int) data;

		ArrayList<Double> vectorOne = new ArrayList<>();
		ArrayList<Double> vectorTwo = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			vectorOne.add(stack_.pop());
		}
		for (int i = 0; i < size; i++) {
			vectorTwo.add(stack_.pop());
		}

		if(vectorOne.size() != vectorTwo.size()){
			throw new CalculatorException("Vektoren sind nicht gleich lang");
		}

		for (int i = 0; i < vectorOne.size(); i++) {

			ergebnis += vectorOne.get(i) * vectorTwo.get(i);

		}
		return ergebnis;
	}
}
