package com.mygdx.test;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import static com.mygdx.test.DLevel1.KEY_SIMULATION_TIME;
import static com.mygdx.test.DLevel1.KEY_STOCK_OSTATAK_DUGA;
import static com.mygdx.test.DLevel1.KEY_VARIABLE_KAMATE;
import static com.mygdx.test.DLevel1.KEY_VARIABLE_KAMATE_TOTAL;
import static com.mygdx.test.DLevel1.KEY_VARIABLE_KVOTA;


public class MyThread implements Runnable {

    private String id;
    private DLevel1 d;
    private Group group;

    private ArrayList<Otplata> data;

    public MyThread(String id, ArrayList<Otplata> data, Map<String, Group> grpHolder, DLevel1 d) {
        this.id = id;
        this.d = d;
        this.group = grpHolder.get(id);
        this.data = data;
        Thread t = new Thread(this, id);
        t.start();
    }

    @Override
    public void run() {

        try {

            Label label = group.findActor(id + "LBL");

            int totalSleep = Utils.totalSleep;
            int numberFrom = 0;
            int numberTo = data.size();
            double value = 0;
            if (id.equals(KEY_SIMULATION_TIME)) {
                value++;
            }

            int sleepStep = totalSleep / data.size();
            DecimalFormat df = new DecimalFormat("#0.00");
            DecimalFormat df2 = new DecimalFormat("#0");

            while (numberFrom < numberTo) {
                try {
                    Thread.sleep(sleepStep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (id.equals(KEY_VARIABLE_KAMATE_TOTAL)) {
                    value += data.get(numberFrom).getKamate();
                } else if (id.equals(KEY_STOCK_OSTATAK_DUGA)) {
                    value = data.get(numberFrom).getOstatakDuga();
                } else if (id.equals(KEY_VARIABLE_KAMATE)) {
                    value = data.get(numberFrom).getKamate();
                } else if (id.equals(KEY_VARIABLE_KVOTA)) {
                    value = data.get(numberFrom).getKvota();
                } else if (id.equals(KEY_SIMULATION_TIME)) {
                    value = numberFrom + 1;
                    d.updateCounters(label, "Mjesec: " + df2.format(value));
                }

                if (!id.equals(KEY_SIMULATION_TIME)) {
                    d.updateCounters(label, df.format(value));
                }
                numberFrom++;
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
