package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hsbo.fbg.systemdynamics.exceptions.ModelException;
import de.hsbo.fbg.systemdynamics.functions.EulerCauchyIntegration;
import de.hsbo.fbg.systemdynamics.model.Converter;
import de.hsbo.fbg.systemdynamics.model.Flow;
import de.hsbo.fbg.systemdynamics.model.Model;
import de.hsbo.fbg.systemdynamics.model.ModelEntityType;
import de.hsbo.fbg.systemdynamics.model.Simulation;
import de.hsbo.fbg.systemdynamics.model.Stock;
import de.hsbo.fbg.systemdynamics.model.Variable;


public class DLevel1 extends DLevelMaster {

    public static String KEY_VARIABLE_AMOUNT = "KEY_VARIABLE_AMOUNT";
    public static String KEY_VARIABLE_TIME = "KEY_VARIABLE_TIME";
    public static String KEY_VARIABLE_RATE = "KEY_VARIABLE_RATE";
    public static String KEY_VARIABLE_ANUITET = "KEY_VARIABLE_ANUITET";
    public static String KEY_STOCK_OSTATAK_DUGA = "KEY_STOCK_OSTATAK_DUGA";
    public static String KEY_VARIABLE_RELATIVNA_KAM_STOPA = "KEY_VARIABLE_RELATIVNA_KAM_STOPA";
    public static String KEY_VARIABLE_KAMATE = "KEY_VARIABLE_KAMATE";
    public static String KEY_VARIABLE_KAMATE_TOTAL = "KEY_VARIABLE_KAMATE_TOTAL";
    public static String KEY_VARIABLE_KVOTA = "KEY_VARIABLE_KVOTA";
    public static String KEY_FLOW_OSTATAK_DUGA = "KEY_FLOW_OSTATAK_DUGA";
    public static String KEY_SIMULATION_TIME = "KEY_SIMULATION_TIME";

    private static String KEY_IMG_CLOUD = "KEY_IMG_CLOUD";
    private static String KEY_ARROW = "KEY_ARROW";

    public Map<String, Group> grpHolder = new HashMap<>();
    public Map<String, ItemDescription> descHolder = new HashMap<>();


    double totalKamate = 0;
    DGame DGame;
    DLevel1 d;

    public DLevel1(DGame DGame) {
        super(DGame);
        this.DGame = DGame;
        this.d = this;
        init();
    }

    @Override
    Table createMainTable() {

        Table table = new Table();
        table.setDebug(Utils.isDebugging);

        table.row().height(CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add(getLabel("OSTATAK DUGA"));
        table.add();
        table.add();
        table.add();
        table.add();
        //1
        table.row().height(CELL_SIZE_HEIGHT).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add(makeGroup(KEY_STOCK_OSTATAK_DUGA, "imgs/moneybag.png", "", 1, false));
        table.add(makeGroup(KEY_FLOW_OSTATAK_DUGA, "imgs/arrowflow.png", "", 3, false)).colspan(3);
        table.add(makeGroup(KEY_IMG_CLOUD, "imgs/cloud.png", "", 1, false));
        //2
        table.row().height(CELL_SIZE_HEIGHT).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add(makeGroup(KEY_ARROW, "imgs/arrowdown.png", "", 1, true));
        table.add();
        table.add(makeGroup(KEY_ARROW, "imgs/arrowup.png", "", 1, true));
        table.add();
        table.add();
        //3
        table.row().height(CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH).width(CELL_SIZE_WIDTH);
        table.add(getLabel("STOPA"));
        table.add();
        table.add(getLabel("KAMATE"));
        table.add();
        table.add(getLabel("KVOTA"));
        table.add();
        table.add();
        table.row().height(CELL_SIZE_HEIGHT).width(CELL_SIZE_WIDTH);
        table.add(makeGroup(KEY_VARIABLE_RELATIVNA_KAM_STOPA, "imgs/percent.png", "", 1, false));
        table.add(makeGroup(KEY_ARROW, "imgs/arrowright.png", "", 1, true));
        table.add(makeGroup(KEY_VARIABLE_KAMATE, "imgs/kamate.png", "", 1, false));
        table.add(makeGroup(KEY_ARROW, "imgs/arrowright.png", "", 1, true));
        table.add(makeGroup(KEY_VARIABLE_KVOTA, "imgs/money.png", "", 1, false));
        table.add();
        table.add();
        //4
        table.row().height(CELL_SIZE_HEIGHT).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add(makeGroup(KEY_ARROW, "imgs/arrowdown.png", "", 1, true));
        table.add();
        table.add(makeGroup(KEY_ARROW, "imgs/arrowup.png", "", 1, true));
        table.add();
        table.add();
        //5
        table.row().height(CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add(getLabel("UKUPNE KAMATE"));
        table.add();
        table.add(getLabel("ANUITET"));
        table.add();
        table.add(getLabel("SVOTA"));
        table.row().height(CELL_SIZE_HEIGHT).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add(makeGroup(KEY_VARIABLE_KAMATE_TOTAL, "imgs/ukupnekamate.png", "", 1, false));
        table.add();
        table.add(makeGroup(KEY_VARIABLE_ANUITET, "imgs/anuitet.png", "", 1, false));
        table.add(makeGroup(KEY_ARROW, "imgs/arrowleft.png", "", 1, true));
        table.add(makeGroup(KEY_VARIABLE_AMOUNT, "imgs/amount.png", "", 1, false));
        //6
        table.row().height(CELL_SIZE_HEIGHT).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add();
        table.add();
        table.add(makeGroup(KEY_ARROW, "imgs/arrowup.png", "", 1, true));
        table.add(makeGroup(KEY_ARROW, "imgs/arrowupleft.png", "", 1, true));
        table.add();
        //7
        table.row().height(CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH).width(CELL_SIZE_WIDTH);
        table.add();
        table.add();
        table.add();
        table.add();
        table.add(getLabel("KAMATNA STOPA"));
        table.add();
        table.add(getLabel("VRIJEME"));
        table.row().height(CELL_SIZE_HEIGHT).width(CELL_SIZE_WIDTH);
        table.add(makeGroup(KEY_SIMULATION_TIME, "imgs/blank.png", "", 1, false));
        table.add();
        table.add();
        table.add();
        table.add(makeGroup(KEY_VARIABLE_RATE, "imgs/percent.png", "", 1, false));
        table.add();
        table.add(makeGroup(KEY_VARIABLE_TIME, "imgs/time.png", "", 1, false));

        return table;
    }

    private void init() {

        descHolder = getItemDescriptions();

    }

    @Override
    void playSimulation(double amount, double time, double rate) {
        try {
            final Model model = new Model(0, time * 12, 1, new EulerCauchyIntegration());

            Variable amountVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_AMOUNT);
            amountVar.setInitialValue(amount);
            Variable timeVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_TIME);
            timeVar.setInitialValue(time);
            Variable rateVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_RATE);
            rateVar.setInitialValue(rate);
            Variable anuitetVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_ANUITET);
            anuitetVar.setInitialValue(anuitet(amountVar.getInitialValue(), rateVar.getInitialValue(), timeVar.getInitialValue()));
            Variable relativnaKamatnaStopaVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_RELATIVNA_KAM_STOPA);
            relativnaKamatnaStopaVar.setInitialValue(dekurzivniKamatniFaktor(rateVar.getInitialValue()) - 1);

            Stock ostatakDugaStock = (Stock) model.createModelEntity(ModelEntityType.STOCK, KEY_STOCK_OSTATAK_DUGA);
            ostatakDugaStock.setInitialValue(amountVar.getInitialValue());

            Variable kamateVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_KAMATE);
            kamateVar.setInitialValue(ostatakDugaStock.getCurrentValue() * relativnaKamatnaStopaVar.getInitialValue());
            Variable kamateTotalVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_KAMATE_TOTAL);
            kamateTotalVar.setInitialValue(0);
            Variable kvotaVar = (Variable) model.createModelEntity(ModelEntityType.VARIABLE, KEY_VARIABLE_KVOTA);
            kvotaVar.setInitialValue(anuitetVar.getCurrentValue() - kamateVar.getCurrentValue());

            Flow ostatakDugaOutflow = (Flow) model.createModelEntity(ModelEntityType.FLOW, KEY_FLOW_OSTATAK_DUGA);
            ostatakDugaStock.addOutputFlows(ostatakDugaOutflow);

            Converter uplataConv = model.createConverter(ostatakDugaOutflow, ostatakDugaStock);
            uplataConv.setFunction(() -> kvota(anuitetVar.getInitialValue(), kamate(ostatakDugaStock.getCurrentValue(), relativnaKamatnaStopaVar.getInitialValue(), kamateVar, kamateTotalVar), kvotaVar));

            ostatakDugaStock.setChangeRateFunction(() -> ostatakDugaOutflow.getCurrentValue());

            ArrayList<Otplata> data = calculate(amount, rate, time);

            Simulation simulation = new Simulation(model);
            simulation.run(grpHolder, d, data);


        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    private double anuitet(double amount, double yearlyInterestRate, double years) {
        double nazivnik = ((Math.pow(dekurzivniKamatniFaktor(yearlyInterestRate), brojMjesecnihAnuiteta(years)) * (dekurzivniKamatniFaktor(yearlyInterestRate) - 1)));
        double brojnik = (Math.pow(dekurzivniKamatniFaktor(yearlyInterestRate), brojMjesecnihAnuiteta(years)) - 1);
        return (amount * (nazivnik / brojnik));
    }

    private double relativnaKamatnaStopa(double yearlyInterestRate) {
        return yearlyInterestRate / 12;
    }

    protected double dekurzivniKamatniFaktor(double yearlyInterestRate) {
        return 1 + (relativnaKamatnaStopa(yearlyInterestRate) / 100);
    }

    private double brojMjesecnihAnuiteta(double years) {
        return years * 12;
    }

    private double kamate(double ostatakDuga, double relativnaKamatnaStopa, Variable kamataVar, Variable kamateTotalVar) {
        double kamate = ostatakDuga * relativnaKamatnaStopa;
        if (kamataVar != null) kamataVar.setCurrentValue(kamate);
        if (kamateTotalVar != null) addToKamateTotal(kamate, kamateTotalVar);
        return kamate;
    }

    private double kvota(double anuitet, double kamate, Variable kvotaVar) {
        if (kvotaVar != null) kvotaVar.setCurrentValue(anuitet - kamate);
        return anuitet - kamate;
    }

    private void addToKamateTotal(double kamate, Variable kamateTotalVar) {
        totalKamate += kamate;
        kamateTotalVar.setCurrentValue(totalKamate);
    }

    protected ArrayList<Otplata> calculate(double amount, double yearlyInterestRate, double years) {
        ArrayList<Otplata> tablica = new ArrayList<>();
        double anuitet = anuitet(amount, yearlyInterestRate, years);
        double relativnaKamatnaStopa = dekurzivniKamatniFaktor(yearlyInterestRate) - 1;
        double ostatakDuga = amount;
        double kamateTotal = 0;
        for (int i = 1; i <= brojMjesecnihAnuiteta(years); i++) {
            double kamate = kamate(ostatakDuga, relativnaKamatnaStopa, null, null);
            double kvota = kvota(anuitet, kamate, null);
            ostatakDuga -= kvota;
            kamateTotal += kamate;

            Otplata otplata = new Otplata();
            otplata.setRazdoblje(i);
            otplata.setAnuitet(anuitet);
            otplata.setKvota(kvota);
            otplata.setKamate(kamate);
            otplata.setOstatakDuga(ostatakDuga);
            otplata.setKamateTotal(kamateTotal);
            tablica.add(otplata);
        }
        return tablica;
    }

    public void updateCounters(Label label, String text) {
        if (text != null) label.setText(String.valueOf(text));
    }

    public void updateCountersFinal(Label label, String text) {
        if (text != null) label.setText(text);
    }

    private Group makeGroup(String key, String imagePath, String displayNum, int colspan, boolean isArrow) {
        Texture tx = new Texture(imagePath);
        Image img = new Image(tx);
        int imgWidth = isArrow ? CELL_SIZE_HEIGHT : CELL_SIZE_WIDTH;
        img.setSize(CELL_SIZE_WIDTH * colspan, imgWidth);
        img.setY(CELL_SIZE_HEIGHT, Align.top);
        if (colspan > 1) {
            img.setX(-CELL_SIZE_WIDTH, Align.left);
        }

        Label lbl = new Label(displayNum, skin);
        lbl.setSize(CELL_SIZE_WIDTH * colspan, CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH);
        lbl.setAlignment(Align.bottom);
        lbl.setFontScale(2.5f);
        lbl.setName(key + "LBL");
        if (colspan > 1) {
            lbl.setX(-CELL_SIZE_WIDTH);
            lbl.setAlignment(Align.bottom);
        }

        Group grp = new Group();
        grp.setSize(CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT);
        grp.addActor(img);
        if (!isArrow) grp.addActor(lbl);

        img.setName(key);
        grpHolder.put(key, grp);
        grp.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                showItemDescription(event.getTarget());
            }
        });

        return grp;
    }

    private Map<String, ItemDescription> getItemDescriptions() {
        ItemDescription item1 = new ItemDescription(KEY_STOCK_OSTATAK_DUGA, "OSTATAK DUGA", "= Ostatak duga - Kvota", "Ostatak duga predstavlja inicijalnu vrijednost duga koja se kroz vrijeme smanjuje za vrijednost otplatne kovote. Njezino praznjenje nije linearno i zajedno sa tokom i varijablama predstavlja molekulu Raspada u sistem dinamickom modelu. Ispocetka se dug smanjuje sporo te ubrzava kroz vrijeme. ");
        ItemDescription item2 = new ItemDescription(KEY_FLOW_OSTATAK_DUGA, "OSTATAK DUGA OUTFLOW", "= Kvota", "Ovaj tok predstavlja izlazni tok iz stoga kojim se otplacuje dug te on svaki mjesec umanjuje vrijednost varijable 'Ostatak duga' za iznos 'Otplatne kvote'.");
        ItemDescription item3 = new ItemDescription(KEY_VARIABLE_RELATIVNA_KAM_STOPA, "RELATIVNA KAMATNA STOPA", "= Godisnja kamatna stopa / 12 mjeseci", "Relativna kamatna stopa predstavlja mjesecnu kamatnu stopu.");
        ItemDescription item4 = new ItemDescription(KEY_VARIABLE_KAMATE, "MJESECNE KAMATE", "= Ostatak duga * Relativna kamatna stopa", "Kamate predstavljaju iznos koji duznik mjesecno placa uz otplatu duga kao naknadu za posudivanje novca. Ova vrijednost se smanjuje kroz vrijeme.");
        ItemDescription item5 = new ItemDescription(KEY_VARIABLE_KVOTA, "MJESECNA KVOTA", "= Anuitet - Kamate", "Mjesecna ili otplatna kvota je novac koji duznik placa za otplatu iznosa kredita bez otplacivanja kamate. Ova vrijednost se povecava kroz vrijeme.");
        ItemDescription item6 = new ItemDescription(KEY_VARIABLE_KAMATE_TOTAL, "UKUPNE KAMATE", "= Ukupne kamate + Mjesecne kamate", "Ova varijabla predstavlja ukupne kamate koje je duznik platio na kraju nekog razdoblja.");
        ItemDescription item7 = new ItemDescription(KEY_VARIABLE_ANUITET, "ANUITET", "= TODO", "Anuitet je varijabla koja se racuna na samom pocetku otplate kredita i predstavlja novcanu ratu koju duznik placa svaki mjesec vjerovniku. Ona predstavlja sumu mjesecne kamate i mjesecne kvote te dok se te dvije vrijednosti mjenjaju anuitet ostaje isti.");
        ItemDescription item8 = new ItemDescription(KEY_VARIABLE_AMOUNT, "IZNOS", "= User input", "Iznos predstavlja novac koji je korisnik posudio od banke i na temelju kojeg se racuna kamata koju je potreban platiti. Takoder predstavlja i pocetnu vrijednost varijable 'Ostatak duga' tj. dug koji duznik otplacuje svaki mjesec.");
        ItemDescription item9 = new ItemDescription(KEY_VARIABLE_TIME, "VRIJEME", "= User input", "Vrijeme je jedan od ulaznih parametra modela i izrazeno je u godinama.");
        ItemDescription item10 = new ItemDescription(KEY_VARIABLE_RATE, "KAMATNA STOPA", "= User input", "Kamatna stopa predstavlja postotak od posudenog iznosa koji banka uzima kao proviziju za izdavanje kredita. Ovo je jedna od ulaznih vrijednosti modela koja se postavlja prilikom unosa podataka i predstavlja kamatnu stopu na godisnjoj razini.");

        Map<String, ItemDescription> map = new HashMap<>();
        map.put(KEY_STOCK_OSTATAK_DUGA, item1);
        map.put(KEY_FLOW_OSTATAK_DUGA, item2);
        map.put(KEY_VARIABLE_RELATIVNA_KAM_STOPA, item3);
        map.put(KEY_VARIABLE_KAMATE, item4);
        map.put(KEY_VARIABLE_KVOTA, item5);
        map.put(KEY_VARIABLE_KAMATE_TOTAL, item6);
        map.put(KEY_VARIABLE_ANUITET, item7);
        map.put(KEY_VARIABLE_AMOUNT, item8);
        map.put(KEY_VARIABLE_TIME, item9);
        map.put(KEY_VARIABLE_RATE, item10);

        return map;
    }

    private void showItemDescription(Actor a) {
        try {
            String key = a.getName();
            if (key != null && !key.equals(KEY_ARROW) && !key.equals(KEY_IMG_CLOUD) && !key.equals(KEY_SIMULATION_TIME)) {
                ItemDescription item = descHolder.get(key);
                itemTitle.setText(item.getTitle());
                itemDescription.setText(item.getDescription());
            }
        } catch (Exception e) {
        }
    }
}
