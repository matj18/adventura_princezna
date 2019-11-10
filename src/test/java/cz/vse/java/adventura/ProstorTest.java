package cz.vse.java.adventura;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Júlia Matulová
 * @version   pro školní rok 2016/2017
 * @created   květen 2019
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {      
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku");
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    
    /**
     * Testuje, zda lze prostoru nastavit postavu, právě jednu, a zda ji správně vrací 
     */
    @Test
    public  void testJednaPostava() {      
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Postava postava1 = new Postava("pes", "haf");
        Postava postava2 = new Postava("kocka", "mnau");
        assertTrue(prostor1.setPostava(postava1));
        assertEquals(prostor1.getPostava(), postava1);
        assertFalse(prostor1.setPostava(postava2));
        assertEquals(prostor1.getPostava(), postava1);
        
    }
    
    /**
     * Testuje vkládání a odebírání věcí, vracení popisu věcí a zviditelňování
     */
    @Test
    public void testVeciVProstoru() {
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Vec nuzky = new Vec("nuzky", true, true);
        Vec papir = new Vec("papir", true, false); //neni videt
        Vec pero = new Vec("pero", true, true);
        Vec stul = new Vec("stul", false, true); //nelze prenest
        assertFalse(prostor1.jeVProstoru("nuzky"));
        prostor1.setVec(nuzky);
        assertTrue(prostor1.jeVProstoru("nuzky"));
        prostor1.setVec(papir);
        assertTrue(prostor1.jeVProstoru("papir"));
        assertEquals(prostor1.odeberVec("nuzky"), nuzky);
        assertFalse(prostor1.jeVProstoru("nuzky"));
        assertFalse(prostor1.jeVProstoru("tiskarna"));
        assertEquals(prostor1.odeberVec("nuzky"), null);
        assertNull(prostor1.popisVeci());
        prostor1.ukazVec(papir);
        assertNotNull(prostor1.popisVeci());
        prostor1.setVec(stul);
        assertEquals(prostor1.odeberVec("stul"), null);
    }
    
    /**
     * Testuje zamykani a odemykani prostoru
     */
    @Test
    public void testZamykaniProstoru() {
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        assertFalse(prostor1.jeZamceno());
        prostor1.setZamceno(false);
        assertFalse(prostor1.jeZamceno());
        prostor1.setZamceno(true);
        assertTrue(prostor1.jeZamceno());
        prostor1.setZamceno(true);
        assertTrue(prostor1.jeZamceno());
        prostor1.setZamceno(false);
        assertFalse(prostor1.jeZamceno());
    }
}
