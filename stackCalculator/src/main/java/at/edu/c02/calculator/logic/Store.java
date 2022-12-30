package at.edu.c02.calculator.logic;

import java.security.KeyException;
import java.util.HashMap;



public class Store {

    HashMap<String,Double> savedNumbers;

    public Store()
    {
        savedNumbers = new HashMap<>();
    }

    public void saveValue(String key, Double input){
        if(savedNumbers.containsKey(key))
        {
            savedNumbers.replace(key,input);
        }
        savedNumbers.put(key, input);
    }

    public double getValue(String key) throws KeyException {
        if(!savedNumbers.containsKey(key))
        {
            throw new KeyException("This key does not exsist");
        }


        double valueForKey = savedNumbers.get(key);
        return valueForKey;
    }


}
