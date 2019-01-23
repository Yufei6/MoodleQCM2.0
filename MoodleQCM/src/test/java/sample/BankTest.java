package sample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private SuperBank sb1;

    @Before
    public void setUp() throws javax.xml.parsers.ParserConfigurationException{
        sb1 = spy(new SuperBank());
    }




    @Test
    public void InitWithoutParamaterOK1(){
        Bank b1 = new Bank();
//        assertEquals(b1.getName(),"Bankdefaut0");
        Bank b2 = new Bank();
//        assertEquals(b2.getName(), "Bankdefaut1");
        Bank b3 = new Bank();
//        assertEquals(b3.getName(), "Bankdefaut2");
    }




    @Test
    public void InitWithParameterOK1(){
        when(sb1.find("100")).thenReturn("./target/Question/100.xml");
        when(sb1.find("200")).thenReturn("./target/Question/200.xml");
        when(sb1.find("300")).thenReturn("./target/Question/200.xml");
        Bank b1 = new Bank("./target/BankAndQcm/Bank_test_001.xml", sb1);
        assertEquals(b1.getName(), "Bank_test_001");
    }


    @Test
    public void saveOK1() throws WrongQuestionTypeException{
        when(sb1.find("100")).thenReturn("./target/Question/100.xml");
        when(sb1.find("200")).thenReturn("./target/Question/200.xml");
        when(sb1.find("300")).thenReturn("./target/Question/300.xml");
        Bank b1 = new Bank("./target/BankAndQcm/Bank_test_001.xml", sb1);
        Question q1 = new Question("./target/Question/300.xml");
        b1.addQuestion(q1);
        b1.save();
    }

}
