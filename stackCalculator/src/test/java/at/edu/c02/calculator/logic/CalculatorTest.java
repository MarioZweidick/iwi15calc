package at.edu.c02.calculator.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;
import at.edu.c02.calculator.Calculator.Operation;

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
		Assert.assertEquals(cal.perform(Operation.cos),-0.4480736161,0.0001);

		cal = new CalculatorImpl();
		cal.push(0);
		Assert.assertEquals(cal.perform(Operation.cos),1,0);
	}

	@Test
	public void testSin() throws Exception
	{
		Calculator cal = new CalculatorImpl();
		cal.push(90);
		Assert.assertEquals(cal.perform(Operation.sin),0.89399666360056,0.001);

		cal = new CalculatorImpl();
		cal.push(0);
		Assert.assertEquals(cal.perform(Operation.sin),0,0);
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

	@Test
	public void testStore() throws CalculatorException {
		Calculator cal = new CalculatorImpl();
		cal.setStoredValue(3.0);
		assertEquals(cal.loadStoredValue(), 3.0, 0);

		cal.setStoredValue(5.0);
		Assert.assertNotSame(cal.loadStoredValue(),3.0);
	}

}
