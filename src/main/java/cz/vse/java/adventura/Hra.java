package cz.vse.java.adventura;


/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Júlia Matulová
 *@version    pro školní rok 2016/2017
 *@created    květen 2019
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private Brasna brasna;
    private boolean konecHry = false;

    /**
     *  Konstruktor, vytváří hru a inicializuje místnosti a další (prostřednictvím třídy HerniPlan)
     *  a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        brasna = new Brasna();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, brasna));
        platnePrikazy.vlozPrikaz(new PrikazVyhod(herniPlan, brasna));
        platnePrikazy.vlozPrikaz(new PrikazBrasna(brasna));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan, brasna));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan)); 
        platnePrikazy.vlozPrikaz(new PrikazHadej(herniPlan, brasna, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     *  
     *  @return String s uvítáním
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Hrajete za princeznu, která na plese tančila s princem.\n" +
               "Toho ale unesl zlý čaroděj, a tak se princezna rozhodla prince najít a zachránit.\n" +
               "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     *  @return String s epilogem
     */
    public String vratEpilog() {
        return "Děkujeme, že jste si zahráli.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     * @return boolen zda je konec hry
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano, spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Toto není platný příkaz.";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá např. třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false = konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

