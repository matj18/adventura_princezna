package cz.vse.java.adventura;

import java.util.*;

/**
 * Instance třídy postava jsou postavy v jednoduché hře, dá se s nimi mluvit a to může
 * způsobit, že se zviditelní některé věci.
 *
 * @author     Júlia Matulová
 * @version    pro školní rok 2016/2017
 * @created    květen 2019
 */
public class Postava
{
    private String jmeno;
    private String rec;
    private Collection<Vec> seznam;

    /**
     * Kostruktor, nastaví jméno a řeč postavy, 
     * vytvoří nový seznam pro věci, které se zviditelní při promluvě
     */
    public Postava(String jmeno, String rec){
        this.jmeno = jmeno;
        this.rec = rec;
        this.seznam = new ArrayList<>();
    }

    /**
     * Vrací jméno postavy
     * 
     * @return jméno postavy
     */
    public String getJmeno(){
        return jmeno;
    }
    
    /**
     * Vrací promluvu postavy
     * 
     * @return řeč postavy
     */
    public String mluv() {
        return rec;
    }
    
    /**
     * Vrací seznam věcí, které se zviditelní při promluvě postavy.
     * 
     * @return seznam věcí, které se zviditelní při promluvě postavy
     */
    public Collection<Vec> getSeznam() {
        return seznam;
    }
    
    /**
     * Přidá do seznamu věcí, které se zviditelní, další věc
     * 
     * @param vec, která se má přidat
     * @return True/false podle toho, zda se přidání povedlo
     */
    public boolean pridejVec(Vec vec) {
        return seznam.add(vec);
    }
    
    /**
     * Metoda equals pro porovnání dvou postav. Překrývá se metoda equals ze
     * třídy Object. Dvě postavy jsou shodné, pokud mají stejné jméno.
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param obj object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaná postava stejné jméno, jinak false
     */  
    @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(obj instanceof Postava)) {
            return false;    // pokud parametr není typu Postava, vrátíme false
        }
        // přetypujeme parametr na typ Postava
        Postava druha = (Postava) obj;

        //metoda equals třídy java.util.Objects porovná hodnoty obou jmen. 
        //Vrátí true pro stejná jména a i v případě, že jsou obě jména null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.jmeno, druha.jmeno));       
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
        int hashNazvu = java.util.Objects.hashCode(this.jmeno);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
}

