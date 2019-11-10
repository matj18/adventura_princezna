package cz.vse.java.adventura;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author   Jarmila Pavlíčková, Júlia Matulová
 * @version  pro školní rok 2016/2017
 * @created  květen 2019
 */
public class HraTest {
    private Hra hra1;
   
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }


    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí,
     * v jaké aktuální místnosti se hráč nachází,
     * jaké jsou tam věci a podobně.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("nádvoří", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        hra1.zpracujPrikaz("jdi tržiště");
        assertEquals(false, hra1.konecHry());
        assertEquals("tržiště", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("bylinky"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("plankton"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("chleba"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("pes"));
        assertEquals(hra1.getHerniPlan().getAktualniProstor().getPostava().getJmeno(), "kořenářka");
        assertFalse(hra1.getHerniPlan().getBrasna().jeVBrasne("bylinky"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().popisVeci().contains("bylinky"));
        
        hra1.zpracujPrikaz("mluv kořenářka");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().popisVeci().contains("bylinky"));
        
        assertTrue(hra1.zpracujPrikaz("seber bylinky").contains("do")); //správné vložení do brašny
        assertEquals("tržiště", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("bylinky"));
        assertTrue(hra1.zpracujPrikaz("brašna").contains("bylinky"));
        
        hra1.zpracujPrikaz("jdi les");
        assertEquals(false, hra1.konecHry());
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("žampion"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("muchomůrka"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("modrá_houba"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("bylinky"));
        
        assertTrue(hra1.zpracujPrikaz("seber modrá_houba").contains("do"));
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("modrá_houba"));
        assertTrue(hra1.zpracujPrikaz("brašna").contains("modrá_houba"));

        
        hra1.zpracujPrikaz("jdi břeh_řeky");
        assertEquals(false, hra1.konecHry());
        assertEquals("břeh_řeky", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("balvan"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("klíč"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("pampeliška"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().popisVeci().contains("klíč"));
        
        hra1.zpracujPrikaz("jdi palouček");
        assertEquals(false, hra1.konecHry());
        assertEquals("palouček", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("pampeliška"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("balvan"));
        assertEquals(hra1.getHerniPlan().getAktualniProstor().getPostava().getJmeno(), "víla");
        
        assertTrue(hra1.zpracujPrikaz("mluv víla").contains("radu"));
        assertEquals(false, hra1.konecHry());
        
        assertTrue(hra1.zpracujPrikaz("seber pampeliška").contains("do"));
        assertEquals("palouček", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("pampeliška"));
        assertTrue(hra1.zpracujPrikaz("brašna").contains("pampeliška"));
        
        hra1.zpracujPrikaz("jdi břeh_řeky");
        assertEquals(false, hra1.konecHry());
        assertEquals("břeh_řeky", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("balvan"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("klíč"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("pampeliška"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().popisVeci().contains("klíč"));
        
        assertTrue(hra1.zpracujPrikaz("seber balvan").contains("nejde"));
        assertEquals("břeh_řeky", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("balvan"));
        assertFalse(hra1.zpracujPrikaz("brašna").contains("balvan"));
        
        assertTrue(hra1.zpracujPrikaz("seber klíč").contains("do"));
        assertEquals("břeh_řeky", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("klíč"));
        assertTrue(hra1.zpracujPrikaz("brašna").contains("klíč"));
        
        hra1.zpracujPrikaz("jdi komnata");
        assertEquals(false, hra1.konecHry());
        assertEquals("břeh_řeky", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("balvan"));
        
        hra1.zpracujPrikaz("jdi les");
        assertEquals(false, hra1.konecHry());
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("žampion"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("muchomůrka"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("modrá_houba"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("bylinky"));
        
        hra1.zpracujPrikaz("jdi lesní_cesta");
        assertEquals(false, hra1.konecHry());
        assertEquals("lesní_cesta", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertNull(hra1.getHerniPlan().getAktualniProstor().popisVeci());

        hra1.zpracujPrikaz("jdi most");
        assertEquals(false, hra1.konecHry());
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("amulet"));
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("lano"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("modrá_houba"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("bylinky"));
        
        assertTrue(hra1.zpracujPrikaz("seber lano").contains("do"));
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("lano"));
        assertTrue(hra1.zpracujPrikaz("brašna").contains("lano"));
        
        assertTrue(hra1.zpracujPrikaz("seber amulet").contains("nevejde"));
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("amulet"));
        assertFalse(hra1.zpracujPrikaz("brašna").contains("amulet"));
        
        assertTrue(hra1.zpracujPrikaz("vyhoď lano").contains("Vyhozeno"));
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("lano"));
        assertFalse(hra1.zpracujPrikaz("brašna").contains("lano"));
        
        assertTrue(hra1.zpracujPrikaz("seber amulet").contains("do"));
        assertEquals("most", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("amulet"));
        assertTrue(hra1.zpracujPrikaz("brašna").contains("amulet"));
        
        hra1.zpracujPrikaz("jdi schodiště");
        assertEquals(false, hra1.konecHry());
        assertEquals("schodiště", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("díra"));
        assertFalse(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("bylinky"));
        
        hra1.zpracujPrikaz("jdi brána");
        assertEquals(false, hra1.konecHry());
        assertEquals("schodiště", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("díra"));
        
        hra1.zpracujPrikaz("jdi dvířka");
        assertEquals(false, hra1.konecHry());
        assertEquals("schodiště", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().jeVProstoru("díra"));
        
        assertTrue(hra1.zpracujPrikaz("vyhoď klíč").contains("Vyhozeno"));
        assertTrue(hra1.zpracujPrikaz("odemkni dvířka").contains("klíč"));
        assertTrue(hra1.zpracujPrikaz("seber klíč").contains("do"));
        assertTrue(hra1.zpracujPrikaz("odemkni dvířka").contains("dvířka"));
        
        hra1.zpracujPrikaz("jdi dvířka");
        assertEquals(false, hra1.konecHry());
        assertEquals("dvířka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        hra1.zpracujPrikaz("jdi komnata");
        assertEquals(false, hra1.konecHry());
        assertEquals("komnata", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(hra1.getHerniPlan().getAktualniProstor().getPostava().getJmeno(), "čaroděj");
        assertNotSame(hra1.getHerniPlan().getAktualniProstor().getPostava().getJmeno(), "víla");
        
        hra1.zpracujPrikaz("hádej pes");
        assertEquals(false, hra1.konecHry());
        
        hra1.zpracujPrikaz("hádej kočka");
        assertEquals(true, hra1.konecHry());
    }
}
