package cz.vse.java.adventura;


/**
 *  Třída PrikazBrasna implementuje pro hru příkaz brašna.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author   Júlia Matulová
 * @version  pro školní rok 2016/2017
 * @created  květen 2019
 *  
 */
class PrikazBrasna implements IPrikaz {
    
    private static final String NAZEV = "brašna";
    private Brasna brasna;
    
     /**
    *  Konstruktor třídy
    *  
    *  @param brasna, odkaz na brašnu používanou ve hře
    */    
    public PrikazBrasna(Brasna brasna) {
        this.brasna = brasna;
    }
    
    /**
     *  Vrací výpis věcí v brašně. Pokud je prázdná, napíše že tam nic není
     *  
     *  @return obsah brašny
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return brasna.vypis();
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
