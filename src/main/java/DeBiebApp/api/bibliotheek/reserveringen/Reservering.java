package DeBiebApp.api.bibliotheek.reserveringen;

import DeBiebApp.api.bibliotheek.leden.Lid;
import DeBiebApp.api.bibliotheek.boeken.Boek;

public class Reservering {
    private Lid lid;
    private String reserveringsDatum;
    private Boek boek;

    public Lid getLid() {
        return lid;
    }

    public String getReserveringsDatum() {
        return reserveringsDatum;
    }

    public Boek getBoek() {
        return boek;
    }

    public Reservering(Lid lid, String reserveringsDatum, Boek boek) {
        this.lid = lid;
        this.reserveringsDatum = reserveringsDatum;
        this.boek = boek;
    }
}
