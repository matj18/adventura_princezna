package cz.vse.java.adventura;




import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Celkové otestování třídy Brasna
 *
* @author     Júlia Matulová
 * @version    pro školní rok 2018/2019
 * @created    květen 2019
 */
public class BrasnaTest
{
    private Brasna brasna;
    private Vec kolo;
    private Vec vzduch;
    private Vec stul;
    private Vec duch;
    private Vec pero;
    private Vec tuzka;
    
    @Before
    public void setUp()
    {
        brasna = new Brasna();
        kolo = new Vec("kolo", true, true);
        vzduch = new Vec("vzduch", false, false);
        stul = new Vec("stul", false, true);
        duch = new Vec("duch", true, false);
        pero = new Vec("pero", true, true);
        tuzka = new Vec("tuzka", true, true);
    }

    @Test
    public void testBrasna() {
        assertTrue(brasna.pridej(kolo));
        assertTrue(brasna.pridej(duch));
        assertTrue(brasna.pridej(pero));
        assertTrue(brasna.jeVBrasne("kolo"));
        assertEquals(brasna.odeber("kolo"), kolo);
        assertEquals(brasna.odeber("kolo"), null);
        assertFalse(brasna.jeVBrasne("kolo"));
        assertTrue(brasna.jeVBrasne("pero"));
        assertTrue(brasna.pridej(kolo));
        assertTrue(brasna.pridej(kolo));
        assertTrue(brasna.pridej(stul));
        assertFalse(brasna.pridej(vzduch));
        assertFalse(brasna.pridej(tuzka));
        assertTrue(brasna.vypis().contains("kolo"));
        assertTrue(brasna.vypis().contains("pero"));
        assertFalse(brasna.vypis().contains("tuzka"));
    }
}

