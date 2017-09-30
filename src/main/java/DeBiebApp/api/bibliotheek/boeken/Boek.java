package DeBiebApp.api.bibliotheek.boeken;

public class Boek {
    private int ISBN;
    private String titel;
    private String auteur;
    private int editie;
    public Boek(int ISBN, String titel, String auteur, int editie){
        this.ISBN=ISBN;
        this.titel=titel;
        this.auteur=auteur;
        this.editie=editie;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitel() {
        return titel;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getEditie() {
        return editie;
    }
}
