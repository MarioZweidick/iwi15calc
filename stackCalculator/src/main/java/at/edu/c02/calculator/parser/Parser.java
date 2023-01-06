package at.edu.c02.calculator.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.KeyException;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;
import at.edu.c02.calculator.Calculator.Operation;
import at.edu.c02.calculator.logic.Store;

public class Parser {

    private Calculator calc_;
    private Store store;

    public Parser(Calculator cal) {
        store = new Store();
        if (cal == null)
            throw new IllegalArgumentException("Calculator not set");
        calc_ = cal;
    }

    public double parse(File calculation) throws FileNotFoundException,
            XMLStreamException, CalculatorException {

        double result = 0;
        XMLEventReader r = createXmlEventReader(calculation);

        while (r.hasNext()) {
            XMLEvent e = r.nextEvent();
            Attribute attribute = e.asStartElement().getAttributeByName(
                    new QName("value"));
            String value = attribute != null ? attribute.getValue() : "";
            if ("push".equals(e.asStartElement().getName().getLocalPart())) {
                if ("Result".equalsIgnoreCase(value)) {
                    calc_.push(result);
                } else {
                    calc_.push(Double.parseDouble(value));
                }
            } else if ("pop"
                    .equals(e.asStartElement().getName().getLocalPart())) {
                calc_.pop();
            } else if ("operation".equals(e.asStartElement().getName().getLocalPart())) {
                result = calc_.perform(readOperation(value));
            } else if ("store".equals(e.asStartElement().getName().getLocalPart())) {
                if (checkValueExsists(value)) {
                    saveValueInStore(value, result);
                } else {
                    calc_.setStoredValue(result);
                }
            } else if ("load".equals(e.asStartElement().getName().getLocalPart())) {
                if (checkValueExsists(value)) {
                    result = restorValueOfStore(value);
                } else {
                    result = calc_.loadStoredValue();
                }
                calc_.push(result);
            }

        }

        return result;
    }


    private XMLEventReader createXmlEventReader(File calculation)
            throws FactoryConfigurationError, FileNotFoundException,
            XMLStreamException {
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        FileReader fr = new FileReader(calculation);
        XMLEventReader xmler = xmlif.createXMLEventReader(fr);
        EventFilter filter = new EventFilter() {
            public boolean accept(XMLEvent event) {

                return event.isStartElement();
            }
        };

        XMLEventReader r = xmlif.createFilteredReader(xmler, filter);
        return r;
    }

    private Operation readOperation(String value) throws CalculatorException {

        if ("*".equals(value))
            return Operation.mul;
        else if ("+".equals(value))
            return Operation.add;
        else if ("/".equals(value))
            return Operation.div;
        else if ("-".equals(value))
            return Operation.sub;
        else if ("%".equals(value))
            return Operation.modulo;
        else if ("Skalar".equals(value))
            return Operation.skalar;
        else if ("sin".equals(value))
            return Operation.sin;
        else if ("cos".equals(value))
            return Operation.cos;
        else {
            throw new CalculatorException("Unsupported Operation!");
        }
    }

    private boolean checkValueExsists(String value) {
        if ("".equals(value)) {
            return false;
        }
        return true;
    }

    private void saveValueInStore(String key, double result) {
        store.saveValue(key, result);
    }

    private double restorValueOfStore(String key) {
        double getValue;
        try {
            getValue = store.getValue(key);
        } catch (KeyException e) {
            throw new RuntimeException(e);
        }
        return getValue;
    }

}
