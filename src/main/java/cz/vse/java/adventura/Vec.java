/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.adventura;



/*******************************************************************************
 * Instance třídy Vec představují věci v této hře.
 *
 * @author    Jarmila Pavlíčková, Júlia Matulová
 * @version    pro školní rok 2016/2017
 * @created    květen 2019
 */

public class Vec
{
    private String nazev;
    private boolean prenositelnost;
    private boolean viditelnost;

    /***************************************************************************
     *  Konstruktor vytvoří instanci věci
     *  
     * @param nazev: string název věci, který se používá pro identifikaci
     * @param prenositelnost: boolean, zda hráč může věc sebrat
     * @param viditelnost: boolean, zda hráč ví o tom, že tam věc je
     */
    public Vec(String nazev,boolean prenositelnost, boolean viditelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.viditelnost = viditelnost;
    }
    /**
    * Metoda vrátí název věci
    * 
    * @return vrací název věci, který ji jednoznačně identifikuje
    */

    public String getNazev(){
        return nazev;
    }
    /**
     * Metoda vrací hodnotu prenositelnosti
     * 
     * @return vrací true/false, zda jde věc odnést
     */
    public boolean jePrenositelnost(){
        return prenositelnost;
    }
    
    /**
     * Metoda vrací hodnotu viditelnosti
     * 
     * @return vrací true/false, zda je věc pro hráče vidět (zda ví, že je věc tam)
     */
    public boolean jeViditelnost(){
        return viditelnost;
    }
    
    /**
     * Metoda nastavuje viditelnost věci
     * 
     * @param viditelnost, která se má nastavit
     */
    public void setViditelnost(boolean viditelnost) {
        this.viditelnost = viditelnost;
    }
 
    
    /**
     * Metoda equals pro porovnání dvou věcí. Překrývá se metoda equals ze
     * třídy Object. Dvě věci jsou shodné, pokud mají stejný název.
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param obj object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaná věc stejný název, jinak false
     */  
    @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Vec)) {
            return false;    // pokud parametr není typu Vec, vrátíme false
        }
        // přetypujeme parametr na typ Vec 
        Vec druha = (Vec) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druha.nazev));       
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
}
