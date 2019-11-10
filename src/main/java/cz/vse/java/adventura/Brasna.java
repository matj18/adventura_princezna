package cz.vse.java.adventura;
import java.util.*;

/**
 * Brasna je třída pro brašnu, kterou si hrdinka nese v průběhu hry, může do ní sbírat věci,
 * nebo je zase vyhazovat.
 * V brašně je místo pro 5 věcí.
 *
 * @author     Júlia Matulová
 * @version    pro školní rok 2016/2017
 * @created    květen 2019
 */
public class Brasna
{
    private List<Vec> seznamVeci;
    private final byte MAXIMUM = 5;

    /**
     * Konstruktor, vytvoří novou instanci s arraylistem
     */
    public Brasna() {
        seznamVeci = new ArrayList<>();
    }

    /**
     * Tato metoda přidá věc do brašny, pokud jich tam už není maximum
     * 
     * @param vec: věc, kterou chceme přidat
     * @return boolean podle metody add arraylistu
     */
    public boolean pridej(Vec vec) {
        if (seznamVeci.size() < MAXIMUM) {
            return seznamVeci.add(vec);
        }
        else {
            return false;
        }
    }

    /**
     * Metoda odebere věc z brašny a vrátí odkaz na tu věc
     * @param nazevVeci: string, název věci, kterou chceme odebrat
     * @return Vec, když ji odebere, null, pokud ji nenajde
     */
    public Vec odeber(String nazevVeci){
        for(Vec neco : seznamVeci){
            if(neco.getNazev().equals(nazevVeci)){
                seznamVeci.remove(neco);
                return neco;
            }
        }
        return null;
    }

    public String vypis() {
        String vracenyText = "";
        if (seznamVeci.isEmpty()) {
            vracenyText = "V brašně nejsou žádné věci.";
        }
        else {
            vracenyText = "V brašně je:";
            for (Vec vec : seznamVeci) {
                vracenyText += " " + vec.getNazev();
            }
        }
        return vracenyText;

    }

    public boolean jeVBrasne(String nazevVeci) {
        for (Vec vec : seznamVeci) {
                if (vec.getNazev().equals(nazevVeci)) {
                    return true;
                }
            }
        return false;
    }
}

