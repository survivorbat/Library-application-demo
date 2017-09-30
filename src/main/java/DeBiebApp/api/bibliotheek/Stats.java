package DeBiebApp.api.bibliotheek;

public class Stats {
    private int boeken;
    private int exemplaren;
    private int uitleningen;
    private int reserveringen;
    private int leden;
    private int total;

    public int getBoeken() {
        return boeken;
    }

    public void setBoeken(int boeken) {
        this.boeken = boeken;
        total+=boeken;
    }

    public int getExemplaren() {
        return exemplaren;
    }

    public void setExemplaren(int exemplaren) {
        this.exemplaren = exemplaren;
        total+=exemplaren;
    }

    public int getUitleningen() {
        return uitleningen;
    }

    public void setUitleningen(int uitleningen) {
        this.uitleningen = uitleningen;
        total+=uitleningen;
    }

    public int getReserveringen() {
        return reserveringen;
    }

    public void setReserveringen(int reserveringen) {
        this.reserveringen = reserveringen;
        total+=reserveringen;
    }

    public int getLeden() {
        return leden;
    }

    public void setLeden(int leden) {
        this.leden = leden;
        total+=leden;
    }

    public int getTotal() {
        return total;
    }
}
