/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.adventura;


/**
 *  Třída PrikazVyhod implementuje pro hru příkaz vyhoď.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author   Júlia Matulová
 * @version  pro školní rok 2016/2017
 * @created  květen 2019
 *  
 */
public class PrikazVyhod implements IPrikaz
{
    private static final String NAZEV = "vyhoď";
    private HerniPlan plan;
    private Brasna brasna;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     *         brasna, odkaz na brašnu používanou ve hře
     */    
    public PrikazVyhod(HerniPlan plan, Brasna brasna) {
        this.plan = plan;
        this.brasna = brasna;
    }

    /**
     *  Provádí příkaz "vyhoď". Vyhazujeme věc z brašny, pokud tam je.
     *
     *@param parametry - jako  parametr obsahuje název věci, kterou chceme vyhodit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co vyhodit z brašny? Je potřeba zadat název věci.";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vyhazovana = brasna.odeber(nazevVeci);
        if (vyhazovana == null) {   
            return nazevVeci + " v brašně není.";
        }

        else{
            aktualniProstor.setVec(vyhazovana);
            return "Vyhozeno z brašny.";
        }                  

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
