import com.company.Fraction;
import com.company.Polynomial;
import com.company.SetOfFraction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PolynomialTest {
    SetOfFraction setOfFraction = new SetOfFraction();
    Fraction f1 = new Fraction(1, 3),
            f2 = new Fraction(6, 4),
            f3 = new Fraction(5, 9),
            f4 = new Fraction(6, 2),
            f5 = new Fraction(3, 4);
    Polynomial p1;
    Polynomial p2 = new Polynomial();

    @Before
    public void setUp() {
        setOfFraction.addFraction(f1, f2, f3, f4, f5);
        p1 = new Polynomial(setOfFraction);
        p2.addCoefficient(new Fraction(1, 2));
        p2.addCoefficient(new Fraction(3, 4));
    }

    @Test
    public void addition() {
        Fraction[] coefficients = Polynomial.addition(p1, p2).getCoefficients().toArray(new Fraction[0]);
        Fraction[] fractions = new Fraction[]{
                new Fraction(5, 6),
                new Fraction(36, 16),
                new Fraction(5, 9),
                new Fraction(6, 2),
                new Fraction(3, 4)
        };
        Assert.assertTrue(coefficients.length == fractions.length);
        for (int i = 0; i < coefficients.length; i++) {
            Assert.assertEquals(coefficients[i].value(), fractions[i].value(), 0.0);
        }
    }
}
