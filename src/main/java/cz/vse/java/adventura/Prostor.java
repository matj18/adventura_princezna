package cz.vse.java.adventura;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 * Dále se v něm mohou nacházet věci, uložené v seznamu, a jedna postava.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Júlia Matulová
 * @version pro školní rok 2016/2017
 * @created květen 2019
 */
public class Prostor {

    private String nazev;
    private String popis;
    private boolean zamceno;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Vec> veci;
    private Postava postava;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru, který se zobrazuje, když hráč vstoupí do místnosti,
     *              navazuje na "Princezna je"
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        this.zamceno = false; //původně jsou všechny odemčené
        vychody = new HashSet<>();
        veci = new ArrayList<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }
    
    /**
     * Definuje věc, nacházející se v prostoru. Na věci je použit ArrayList, 
     * správné vložení lze kontrolovat pomocí návratové hodnoty
     * 
     * @return boolean true = věc v pořádku přidána, false = chyba při přidávání
     * 
     */
    public boolean setVec(Vec neco) {
        return veci.add(neco);
    }

    
    /**
     * Tato metoda nastaví prostoru postavu a hlásí, zda se vložení povedlo.
     * 
     * @return boolean true = postava nastavena, false = chyba, prostor už postavu má
     */
    public boolean setPostava(Postava postava) {
        if (this.postava == null) {
            this.postava = postava;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Vrátí odkaz na postavu v prostoru
     * @return Postava: postava která je v tomto prostoru
     */
    
    public Postava getPostava() {
        return postava;
    }
    
    /**
     * Metoda odebere věc ze seznamu věcí, které jsou v prostoru a odkaz na ni vrátí.
     * V případě, že věc nenajde, vrací null.
     * 
     * @param nazevVeci: název, který věc identifikuje,
     *          jde o věc, kterou chceme z prostoru odstranit
     * @return Vec: odebraná věc
     */
    
    public Vec odeberVec(String nazevVeci){
        for(Vec neco : veci){
            if(neco.getNazev().equals(nazevVeci)&& neco.jePrenositelnost()){
                veci.remove(neco);
                return neco;
            }
        }
        return null;
    }

    /**
     * Zjišťuje, zda se věc nachází v prostoru
     * @param nazevVeci: název věci, u které si chceme ověřit přítomnost
     * @return boolean true = věc se nachází v prostoru, false = věc tam není
     */
    public boolean jeVProstoru(String nazevVeci){
        for(Vec neco : veci){
            if(neco.getNazev().equals(nazevVeci)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Tato metoda nastaví zadanou věc (pokud je v prostoru) jako viditelnou.
     * Je-li věc už viditelná, nic se nezmění.
     * 
     * @param neco: věc, kterou chceme zviditelnit
     */
    
    public void ukazVec(Vec neco) {
        for (Vec vec: veci) {
            if (vec.equals(neco)) {
                vec.setViditelnost(true);
            }
        }
    }
    

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param obj object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    public String getPopis() {
        return popis;
    }
    /**
     * Vrací "dlouhý" popis prostoru, který obsahuje i popis východů,
     * věcí a postavy v prostoru.
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        String vracenyText = "Princezna je " + popis;
        if (popisVeci() != null) {
            vracenyText += "\n"
            + popisVeci();
        }
        vracenyText += ".\n"
            + popisVychodu();
        if (postava != null) {
            vracenyText += ".\n"
            + "Můžeš si promluvit s: " +postava.getJmeno();
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if (sousedni.jeZamceno()) {
                vracenyText += "(zamknuto)";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje viditelné věci v prostoru
     *
     * @return Popis názvů věcí v prostoru, nebo null, pokud tam žádné nejsou
     */
    public String popisVeci() {
        String vracenyText = "co tu je:";
        for (Vec neco : veci) {
            if (neco.jeViditelnost()) {
                vracenyText += " " + neco.getNazev();
            }
        }
        if (vracenyText.equals("co tu je:")){
            return null;
        }
        else {
            return vracenyText;
        }
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
            .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy HerniPlan.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Vrací informaci o tom, zda je prostor zamčený.
     * 
     * @return true/false podle toho, zda je zamčeno
     */
    public boolean jeZamceno() {
        return this.zamceno;
    }

    /**
     * Umožňuje nastavit, zda je prostor zamčený.
     * 
     * @param zamceno: boolean, true pro uzamknutí prostoru, false pro odemknutí
     */
    public void setZamceno(boolean zamceno){
        this.zamceno = zamceno;
    }

    public Collection<Vec> getSeznamVeci() {
        return veci;
    }
}
