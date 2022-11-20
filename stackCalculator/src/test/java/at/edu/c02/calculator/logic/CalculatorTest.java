package at.edu.c02.calculator.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;
import at.edu.c02.calculator.Calculator.Operation;
import at.edu.c02.calculator.logic.CalculatorImpl;

import java.util.ArrayList;

public class CalculatorTest {

	@Test
	public void testSimpleAddOperation() throws Exception {

		//setup
		Calculator calc = new CalculatorImpl();
		
		//execute
		calc.push(2.0);
		calc.push(3);
		double result = calc.perform(Operation.add);

		//verify
		assertEquals(5, result, 0);
		

	}
	
	@Test
	public void testSimpleMulOperation() throws Exception {

		Calculator calc = new CalculatorImpl();
		calc.push(2.0);
		calc.push(3);
		double result = calc.perform(Operation.mul);

		assertEquals(6, result, 0);

	}
	
	@Test
	public void testSimpleDivOperation() throws Exception {

		Calculator calc = new CalculatorImpl();
		calc.push(6.0);
		calc.push(2);
		double result = calc.perform(Operation.div);

		assertEquals(3, result, 0);

	}
	
	
	
	

	//
	@Test(expected = CalculatorException.class)
	public void testPopOnEmptyStack() throws Exception {

		Calculator calc = new CalculatorImpl();
		calc.pop();

	}

	@Test
	public void testDivisionByZero() throws Exception {

		//Setup
		Calculator calc = new CalculatorImpl();
		try {
			calc.push(2);
			calc.push(0);
			calc.perform(Operation.div);

			fail("Exception expected");
			

		} catch (CalculatorException e) {
			assertEquals("Division by zero", e.getMessage());
			// e.getCause()
		}

	}
	@Test
	public void testSkalarprodukt() throws CalculatorException{


		Calculator calc = new CalculatorImpl();
		calc.push(2);
		calc.push(2);
		calc.push(2);

		calc.push(3);
		calc.push(3);
		calc.push(3);
		calc.push(3);



		Double result = calc.perform(Operation.skalar);
		assertEquals(18, result, 0);

	}
	@Test
	public void testSkalarFehler() throws CalculatorException{
		Calculator cal  = new CalculatorImpl();
	try {


		cal.push(2);
		cal.push(2);
		cal.push(3);
		cal.push(3);
		cal.push(2);
		cal.push(5);

		cal.perform(Operation.skalar);
	}
	catch (CalculatorException e){
		assertEquals("Vektoren brauchen die selben Dimensionen!",e.getMessage());
	}
	}

}
