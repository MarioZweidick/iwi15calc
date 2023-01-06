package at.edu.c02.calculator.endtoend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import at.edu.c02.calculator.logic.CalculatorImpl;
import at.edu.c02.calculator.parser.Parser;
import org.junit.Test;

import at.edu.c02.calculator.CalculatorException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

public class EndToEndTest {

    @Test
    public void EndToEndTestWithLoadStore() throws CalculatorException, XMLStreamException, FileNotFoundException {
        CalculatorImpl cal = new CalculatorImpl();
        Parser parser = new Parser(cal);

        double result = parser.parse(new File("src/test/resources/test08.xml"));
        assertEquals(result, 6, 0);

    }

    @Test
    public void EndToEndTestWithLoadStoreParameter() throws CalculatorException, XMLStreamException, FileNotFoundException {
        CalculatorImpl cal = new CalculatorImpl();
        Parser parser = new Parser(cal);

        double result = parser.parse(new File("src/test/resources/test10.xml"));
        assertEquals(result, 6, 0);

    }
}
