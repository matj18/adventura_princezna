package cz.vse.java.adventura;

/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/**
 *  Třída PrikazHadej implementuje pro hru příkaz hádej.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author   Júlia Matulová
 * @version  pro školní rok 2016/2017
 * @created  květen 2019
 *  
 */


public class PrikazHadej implements IPrikaz
{
    private static final String NAZEV = "hádej";
    private HerniPlan plan;
    private Brasna brasna;
    private Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"      
     *         brasna, odkaz na brašnu používanou ve hře
     *         @param hra odkaz na hru, která může být ukončena
     *          
     */    
    public PrikazHadej(HerniPlan plan, Brasna brasna, Hra hra) {
        this.plan = plan;
        this.brasna = brasna;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "hádej". Hráč si vybírá ze tří možností. Jestliže nezvolí ani jednu z nich,
     *  má šanci to zkusit znovu.
     *  Jestliže zvolí žába, kočka nebo ryba, záleží výhra na tom, jestli má všechny přísady a uhodl kočku. Hra končí tak nebo tak.
     *
     *@param parametry - jako  parametr obsahuje tip (kočka, žába, ryba)
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();
        if (!aktualniProstor.getNazev().equals("komnata")) {
            return "Tady není proč hádat.";
        }

        if (parametry.length == 0) {
            return "Jaký je tvůj tip? Kočka, žába, nebo ryba?";
        }
        boolean maPrisady = (brasna.jeVBrasne("bylinky") && brasna.jeVBrasne("amulet") && brasna.jeVBrasne("modrá_houba") && brasna.jeVBrasne("pampeliška"));
        String tip = parametry[0];
        switch (tip) {
            case "kočka": {
                hra.setKonecHry(true);
                if (maPrisady) {
                    return "Správně! Čaroděj teď prince promění zpátky. Gratulujeme!";
                }
                else {
                    return "Bohužel se ti nepovedlo prince osvobodit, nemáš všechny přísady.";
                }
            }
            case "žába": {
                hra.setKonecHry(true);
                if (maPrisady) {
                    return "Bohužel se ti nepovedlo prince osvobodit, žába to nebyla.";
                }
                else {
                    return "Bohužel se ti nepovedlo prince osvobodit, nemáš všechny přísady.";
                }
            }
            case "ryba": {
                hra.setKonecHry(true);
                if (maPrisady) {
                    return "Bohužel se ti nepovedlo prince osvobodit, ryba to nebyla.";
                }
                else {
                    return "Bohužel se ti nepovedlo prince osvobodit, nemáš všechny přísady.";
                }
            }
            default: return "Takové zvíře tu čaroděj nemá.";
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
