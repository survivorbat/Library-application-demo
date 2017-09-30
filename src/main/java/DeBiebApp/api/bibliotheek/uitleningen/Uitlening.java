package DeBiebApp.api.bibliotheek.uitleningen;

import DeBiebApp.api.bibliotheek.exemplaren.Exemplaar;
import DeBiebApp.api.bibliotheek.leden.Lid;

public class Uitlening {
    private Exemplaar exemplaar;
    private Lid lid;
    private String datumTerugave;

    public Uitlening(Exemplaar exemplaar, Lid lid, String datumTerugave) {
        this.exemplaar = exemplaar;
        this.lid = lid;
        this.datumTerugave = datumTerugave;
    }

    public Exemplaar getExemplaar() {
        return exemplaar;
    }

    public Lid getLid() {
        return lid;
    }

    public String getDatumTerugave() {
        return datumTerugave;
    }

    public int getLidNummer(){
        return lid.getLidNr();
    }
}
