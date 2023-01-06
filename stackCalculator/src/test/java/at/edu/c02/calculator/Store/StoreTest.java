package at.edu.c02.calculator.Store;

import at.edu.c02.calculator.logic.Store;
import org.junit.Test;
import org.junit.Assert;

import java.security.KeyException;

public class StoreTest {

    @Test
    public void testSetandGet() {
        Store store = new Store();
        store.saveValue("BierPreis", 3.0);
        try {
            Assert.assertEquals(store.getValue("BierPreis"), 3.0, 0.0);
        } catch (KeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionHandling() {
        Store store = new Store();
        try {
            store.getValue("BierPreis");
        } catch (KeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void valueGetsOverWritten() {
        Store store = new Store();
        store.saveValue("BierPreis", 2.0);
        store.saveValue("BierPreis", 3.0);
        try {
            Assert.assertEquals(store.getValue("BierPreis"), 3.0, 0);
        } catch (KeyException e) {
            throw new RuntimeException(e);
        }
    }
}
