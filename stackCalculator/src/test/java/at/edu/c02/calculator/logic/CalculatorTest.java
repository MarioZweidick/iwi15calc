package at.edu.c02.calculator.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;
import at.edu.c02.calculator.Calculator.Operation;
import at.edu.c02.calculator.logic.CalculatorImpl;

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
	public void testModulo() throws  Exception
	{
		Calculator calc = new CalculatorImpl();

		calc.push(2);
		calc.push(2);
		double result = calc.perform(Operation.modulo);
		Assert.assertEquals(0.0,result,0);

		calc = new CalculatorImpl();
		calc.push(1);
		calc.push(2);
		result = calc.perform(Operation.modulo);
		Assert.assertEquals(1.0,result,0);

		calc = new CalculatorImpl();
		calc.push(1);
		calc.push(2);
		result = calc.perform(Operation.modulo);
		Assert.assertNotSame(0.0,result);
	}

	@Test
	public void testCos() throws Exception
	{
		Calculator cal = new CalculatorImpl();
		cal.push(90);
		Assert.assertEquals(cal.perform1Var(Operation.cos),-0.4480736161,0.0001);

		cal = new CalculatorImpl();
		cal.push(0);
		Assert.assertEquals(cal.perform1Var(Operation.cos),1,0);
	}

	@Test
	public void testSin() throws Exception
	{
		Calculator cal = new CalculatorImpl();
		cal.push(90);
		Assert.assertEquals(cal.perform1Var(Operation.sin),0.89399666360056,0.001);

		cal = new CalculatorImpl();
		cal.push(0);
		Assert.assertEquals(cal.perform1Var(Operation.sin),0,0);
	}
}
