package sample;

import org.junit.Test;

//import static org.mockito.Mockito.spy;

import static org.junit.Assert.assertEquals;

public class BankTest {


    @Test
    public void InitWithoutParamaterOK1(){
        Bank b1 = new Bank();
        assertEquals(b1.getName(),"Bankdefaut2");
    }

    @Test
    public void IniiWithoutParameterOk2(){
        Bank b2 = new Bank();
        assertEquals(b2.getName(), "Bankdefaut0");
        Bank b3 = new Bank();
        assertEquals(b3.getName(), "Bankdefaut1");
    }


    @Test
    public void InitWithParameterOK1(){
        Bank b1 = new Bank("./target/XML/Bank_test_001.xml");
        assertEquals(b1.getName(), "Bank_test_001");
    }

}
