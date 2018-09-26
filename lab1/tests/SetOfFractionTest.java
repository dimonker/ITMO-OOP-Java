import com.company.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SetOfFractionTest {
    SetOfFraction setOfFraction = new SetOfFraction();
    Fraction f1 = new Fraction(1, 3),
             f2 = new Fraction( 6, 4),
             f3 = new Fraction(5,9),
             f4 = new Fraction(6,2),
             f5 = new Fraction(3, 4);

    @Before
    public void setUP(){
        setOfFraction.addFraction(f1, f2, f3, f4 ,f5);

    }

    @Test
    public void testGetMin(){
        Assert.assertEquals(setOfFraction.getMin().value(), f1.value(), 0.0);
    }

    @Test
    public void testGetMax(){
        Assert.assertEquals(setOfFraction.getMax().value(), f4.value(), 0.0);
    }

    @Test
    public void CountLessThan(){
        Assert.assertTrue(setOfFraction.numberLower(new Fraction(4, 3)) == 3);
    }


}
