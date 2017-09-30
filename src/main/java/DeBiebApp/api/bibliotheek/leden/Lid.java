package DeBiebApp.api.bibliotheek.leden;

public class Lid {
    private int lidNr;
    private String voornaam;
    private String achternaam;
    private String straat;
    private int huisnummer;
    private String stad;
    private int telefoonnummer;
    private String email;
    private double boete;

    public Lid(int lidNr, String voornaam, String achternaam, String straat, int huisnummer, String stad, int telefoonnummer, String email, double boete) {
        this.lidNr = lidNr;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.stad = stad;
        this.telefoonnummer = telefoonnummer;
        this.email = email;
        this.boete = boete;
    }

    public int getLidNr() {
        return lidNr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getStraat() {
        return straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public String getStad() {
        return stad;
    }

    public int getTelefoonnummer() {
        return telefoonnummer;
    }

    public String getEmail() {
        return email;
    }

    public double getBoete() {
        return boete;
    }
}
