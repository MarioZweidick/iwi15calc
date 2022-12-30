package at.edu.c02.calculator.parser;

import java.io.File;
import java.io.FileNotFoundException;

import at.edu.c02.calculator.logic.CalculatorImpl;
import org.junit.Test;
import static org.mockito.Mockito.*;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.Calculator.Operation;

public class ParserTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNullParser() {
		new Parser(null);
	}

	@Test(expected = FileNotFoundException.class)
	public void testParserInvalidFile() throws Exception {

		Calculator cal = mock(Calculator.class);

		Parser parser = new Parser(cal);
		parser.parse(new File("invalid"));
	}

	@Test
	public void testParserTest01Xml() throws Exception {

		Calculator cal = mock(Calculator.class);

		Parser parser = new Parser(cal);
		parser.parse(new File("src/test/resources/test01.xml"));
		
		verify(cal).push(1.0);
		verify(cal).push(2.0);
		verify(cal).perform(Operation.add);
		verifyNoMoreInteractions(cal);
	}

	@Test
	public  void testParserTestModulo() throws Exception
	{
		Calculator cal = mock(Calculator.class);

		Parser parser = new Parser(cal);
		parser.parse(new File("src/test/resources/test04.xml"));

		verify(cal).push(2.0);
		verify(cal).push(1.0);
		verify(cal).perform(Operation.modulo);

	}

	@Test
	public  void testParserTest3Add() throws Exception
	{
		Calculator cal = mock(Calculator.class);

		Parser parser = new Parser(cal);
		parser.parse(new File("src/test/resources/test05.xml"));

		verify(cal).push(6);
		verify(cal).push(4);
		verify(cal).perform(Operation.add);

	}

	@Test
	public void TestParserTestSin() throws Exception
	{
		Calculator cal = mock(Calculator.class);

		Parser parser = new Parser(cal);
		parser.parse(new File("src/test/resources/test06.xml"));

		verify(cal).push(6);
		verify(cal).perform(Operation.sin);
	}
  
  	@Test
	public void testParserTestSkalarXml() throws Exception{
		Calculator cal = mock(Calculator.class);

		Parser parser = new Parser(cal);

		parser.parse(new File("src/test/resources/Skalar.xml"));
		verify(cal).push(3.0);
		verify(cal).push(2.0);
		verify(cal).push(1.0);
		verify(cal).perform(Operation.skalar);
		verifyNoMoreInteractions(cal);
	}

	@Test
	public void testLoadStore()throws Exception
	{
		Calculator cal = mock(Calculator.class);
		Parser parser = new Parser(cal);

		parser.parse(new File("src/test/resources/test07.xml"));
		verify(cal).setStoredValue(0.0);
		verify(cal).loadStoredValue();
	}


}
