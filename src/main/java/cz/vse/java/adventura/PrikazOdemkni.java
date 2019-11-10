package cz.vse.java.adventura;


/**
 *  Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author   Júlia Matulová
 * @version  pro školní rok 2016/2017
 * @created  květen 2019
 *  
 */
class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;
    private Brasna brasna;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *         brasna, odkaz na brašnu používanou ve hře
    */    
    public PrikazOdemkni(HerniPlan plan, Brasna brasna) {
        this.plan = plan;
        this.brasna = brasna;
    }

    /**
     *  Provádí příkaz "odemkni". Lze odemknout pouze prostor "dvířka" za pomoci "klíč"
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru, který chceme odemknout
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co odemknout? Je potřeba zadat název.";
        }

        String dvere = parametry[0];

        
        Prostor odemykanyProstor = plan.getAktualniProstor().vratSousedniProstor(dvere);
        boolean maKlic = brasna.jeVBrasne("klíč");
        if (odemykanyProstor == null) {
            return "Takové dveře tu nejsou.";
        }
        else {
            if (odemykanyProstor.jeZamceno() && odemykanyProstor.getNazev().equals("dvířka") && maKlic) {
                odemykanyProstor.setZamceno(false);
                return "Nyní jsou dvířka odemčená.";
            }
            return "Je potřeba mít správný klíč!";
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo, které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
