package cz.vse.java.adventura;

/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

import java.util.*;

/**
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author   Júlia Matulová
 * @version  pro školní rok 2016/2017
 * @created  květen 2019
 *  
 */
public class PrikazMluv implements IPrikaz
{
    private static final String NAZEV = "mluv";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "mluv". Je-li v prostoru postava, kterou hráč označil,
     *  vrátí se její promluva a mohou se zviditelnit některé věci.
     *
     *@param parametry - jako  parametr obsahuje jméno postavy, se kterou chceme mluvit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "S kým si má princezna promluvit?";
        }
        String jmenoPostavy = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.getPostava();
        if (postava == null) {
            return "Nikdo takový tu není.";
        }
        Collection<Vec> seznam = postava.getSeznam();
        if (postava.getJmeno().equals(jmenoPostavy)) {
            for (Vec vec: seznam) {
                aktualniProstor.ukazVec(vec);
            }   
            String popisVeci = aktualniProstor.popisVeci();
            if (popisVeci == null) {
                return postava.mluv();
            }
            else {
                return postava.mluv() + "\n" + popisVeci;
            }
        }
        else{
            return "Nikdo takový tu není.";
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
