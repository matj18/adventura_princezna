package cz.vse.java.adventura;



/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  Dále vytváří postavy a věci.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Júlia Matulová
 *@version    pro školní rok 2016/2017
 *@created    květen 2019
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Brasna brasna;
    
     /**
     *  Konstruktor, který vytváří prostředí hry.
     */
    public HerniPlan() {
        zalozProstoryHry();
        brasna = new Brasna();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů,
     *  vytváří věci a přiřadí je k prostorům, stejně tak postavy.
     *  Jako výchozí aktuální prostor nastaví nádvoří.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor nadvori = new Prostor("nádvoří","na nádvoří zámku, odkud pochází");
        Prostor trziste = new Prostor("tržiště", "na tržišti, kde může sehnat různé věci");
        Prostor les = new Prostor("les","v lese, kde se dají sbírat houby");
        Prostor brehReky = new Prostor("břeh_řeky","na břehu řeky");
        Prostor paloucek = new Prostor("palouček","na paloučku, kde bydlí víla");
        Prostor lesniCesta = new Prostor("lesní_cesta","na lesní cestě, která vede k mostu");
        Prostor most = new Prostor("most","na mostě přes řeku");
        Prostor schodiste = new Prostor("schodiště","na kamenném schodišti, které vede ke hradu");
        Prostor brana = new Prostor("brána","v bráně, kam se neměla dostat");
        Prostor dvirka = new Prostor("dvířka","v tajných dveřích do komnaty");
        Prostor komnata = new Prostor("komnata","v komnatě, kde se nachází čaroděj." + "\n"
        + "Pokud má princezna v brašně všechny potřebné přísady, může poprosit čaroděje, aby prince pustil");
        
        //zamceni prostorů
        
        brana.setZamceno(true);
        dvirka.setZamceno(true);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        nadvori.setVychod(trziste);
        trziste.setVychod(nadvori);
        trziste.setVychod(les);
        les.setVychod(trziste);
        les.setVychod(lesniCesta);
        les.setVychod(brehReky);
        brehReky.setVychod(les);
        brehReky.setVychod(paloucek);
        paloucek.setVychod(brehReky);
        lesniCesta.setVychod(les);
        lesniCesta.setVychod(most);
        most.setVychod(lesniCesta);
        most.setVychod(schodiste);
        schodiste.setVychod(brana);
        schodiste.setVychod(dvirka);
        brana.setVychod(schodiste);
        dvirka.setVychod(schodiste);
        dvirka.setVychod(komnata);
        komnata.setVychod(dvirka);
                
        aktualniProstor = nadvori;  // hra začíná na nádvoří
        
        //vytvareni a prirazeni veci do spravnych prostoru
        Vec poklad = new Vec("poklad", false, true);
        nadvori.setVec(poklad);
        Vec koberec = new Vec("koberec", true, true);
        nadvori.setVec(koberec);        
        Vec bylinky = new Vec("bylinky",true, false);
        trziste.setVec(bylinky);
        Vec zabiStehynka = new Vec("žabí_stehýnka",true, false);
        trziste.setVec(zabiStehynka);
        Vec plankton = new Vec("plankton",true, false);
        trziste.setVec(plankton);
        Vec chleba = new Vec("chleba",true, true);
        trziste.setVec(chleba);
        Vec zampion = new Vec("žampion",true, true);
        les.setVec(zampion);
        Vec muchomurka = new Vec("muchomůrka",true, true);
        les.setVec(muchomurka);
        Vec modraHouba = new Vec("modrá_houba",true, true);
        les.setVec(modraHouba);
        Vec balvan = new Vec("balvan",false, true);
        brehReky.setVec(balvan);
        Vec pampeliska = new Vec("pampeliška",true, true);
        paloucek.setVec(pampeliska);
        Vec amulet = new Vec("amulet",true, true);
        most.setVec(amulet);
        Vec lano = new Vec("lano",true, true);
        most.setVec(lano);
        Vec dira = new Vec("díra",false, true);
        schodiste.setVec(dira);
        Vec klic = new Vec("klíč",true, false);
        brehReky.setVec(klic);
        
        //vytvoreni postav a jejich prirazeni, pridani veci do seznamu, ktere se objevi po promluve
        Postava korenarka = new Postava("kořenářka","Vyznám se v různých lektvarech a kouzlech." + "\n"
        + "Vím, co budeš potřebovat." + "\n"
        + "Čaroděj prince zaklel do nějakého zvířete a ty budeš muset uhodout, do kterého." + "\n"
        + "K proměně musíš mít kouzelné přísady." + "\n"
        + "Vezmi si tyhle bylinky. Dále najdi amulet, pampelišku a modrou houbu");
        korenarka.pridejVec(bylinky);
        korenarka.pridejVec(zabiStehynka);
        korenarka.pridejVec(plankton);
        trziste.setPostava(korenarka);
        
        Postava vila = new Postava ("víla","Vítej na mém paloučku. Mám pro tebe jednu radu." + "\n"
        + "Na břehu řeky najdeš neviditelný klíč k čarodějovu hradu." + "\n"
        + "Jen se postav na břeh řeky a řekni, že ho chceš sebrat. Objeví se ti v brašně");
        paloucek.setPostava (vila);
        
        Postava carodej = new Postava ("čaroděj", "Vítej. Vidím, že prince opravdu chceš zachránit." + "\n"
        + "Dobrá tedy, dám ti šanci. Prince jsem zaklel buď do těla žáby, ryby, nebo kočky." + "\n"
        + "Až budeš chtít, hádej");
        komnata.setPostava (carodej);
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda vrací odkaz na brašnu, do které si hráč ukládá věci
     *
     *@return     brašna
     */
    
    public Brasna getBrasna() {
        return brasna;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

}
