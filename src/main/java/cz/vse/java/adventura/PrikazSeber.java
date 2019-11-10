/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.adventura;

/*******************************************************************************
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author   Jarmila Pavlíčková, Júlia Matulová
 * @version  pro školní rok 2016/2017
 * @created  květen 2019
 */
public class PrikazSeber implements IPrikaz
{
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Brasna brasna;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     *          brasna brašna v této hře
     */    
    public PrikazSeber(HerniPlan plan, Brasna brasna) {
        this.plan = plan;
        this.brasna = brasna;
    }

    /**
     *  Provádí příkaz "seber". Pokusí se sebrat věc. Musí být v aktuální místnosti a musí být přenositelná.
     *  Zároveň se kontroluje maximum brašny
     *
     *@param parametry - jako  parametr obsahuje název věci, kterou chce sebrat
     *                         
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co sebrat? je potřeba zadat název věci.";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.odeberVec(nazevVeci);
        if (sbirana != null) {      
            if(brasna.pridej(sbirana)){
                return "Vloženo do brašny.";
            }
            else{
                aktualniProstor.setVec(sbirana);
                return "Tolik věcí se nevejde, něco tu bude muset zůstat.";
            }                  
        }
        else{
            if(aktualniProstor.jeVProstoru(nazevVeci)){
                return "To nejde odnést.";
            }
            else {
                return "To tu není!";
            }
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
