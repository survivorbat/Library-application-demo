package DeBiebApp.api.bibliotheek.exemplaren;

import DeBiebApp.api.bibliotheek.boeken.Boek;

public class Exemplaar {
    private Boek boek;
    private int exemplaarID;
    private int uitleenperiode;
    public Exemplaar(Boek boek, int exemplaarID, int uitleenperiode){
        this.exemplaarID=exemplaarID;
        this.boek=boek;
        this.uitleenperiode=uitleenperiode;
    }
    public Boek getBoek() {
        return boek;
    }

    public int getExemplaarID() {
        return exemplaarID;
    }

    public int getUitleenperiode() {
        return uitleenperiode;
    }
}
