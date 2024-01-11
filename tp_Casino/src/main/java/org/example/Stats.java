package org.example;

import java.util.Map;

public class Stats {

    private int jeton;
    private int partieJoue;
    private int partieGagne;
    private int jetonDepense;

    public Map<String, Double> getProbaGains() {
        return probaGains;
    }

    public void setProbaGains(Map<String, Double> probaGains) {
        this.probaGains = probaGains;
    }

    private Map<String, Double> probaGains;

    public int getJeton() {
        return jeton;
    }

    public void setJeton(int jeton) {
        this.jeton = jeton;
    }

    public int getPartieJoue() {
        return partieJoue;
    }

    public void setPartieJoue(int partieJoue) {
        this.partieJoue = partieJoue;
    }

    public int getPartieGagne() {
        return partieGagne;
    }

    public void setPartieGagne(int partieGagne) {
        this.partieGagne = partieGagne;
    }

    public int getJetonDepense() {
        return jetonDepense;
    }

    public void setJetonDepense(int jetonDepense) {
        this.jetonDepense = jetonDepense;
    }

    public Stats() {

    }
}
