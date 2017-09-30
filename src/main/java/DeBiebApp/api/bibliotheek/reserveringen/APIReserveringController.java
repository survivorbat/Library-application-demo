package DeBiebApp.api.bibliotheek.reserveringen;

import DeBiebApp.api.DB;
import DeBiebApp.api.bibliotheek.boeken.APIBoekenController;
import DeBiebApp.api.bibliotheek.leden.APILidController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class APIReserveringController {
    private DB database;
    private APIBoekenController boeken;
    private APILidController leden;
    public APIReserveringController(){
        database = new DB();
        boeken = new APIBoekenController();
        leden = new APILidController();
    }
    //Lijst
    @ApiOperation(value = "findAllReserveringen", notes = "Alle reserveringen opvragen")
    @RequestMapping(value="/reserveringen",method=GET)
    public List<Reservering> reserveringen(@RequestParam(value="order", defaultValue = "ISBN") String order){
        List<Reservering> reserveringLijst = new ArrayList<Reservering>();
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Reservering ORDER BY "+order+";");
            while (data.next()) {
                reserveringLijst.add(new Reservering(leden.getLid(data.getString(2)),data.getString(1),boeken.getBoek(data.getString(3))));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return reserveringLijst;
    }
    //Insert query
    @ApiOperation(value = "addReservering", notes = "Een reservering toevoegen")
    @RequestMapping(value="/reservering", method=POST)
    public void insertReservering(@RequestParam(value="ISBN") String ISBN,@RequestParam(value="lidnr") String lidNr,@RequestParam(value="reserveringsdatum") String datum){
        try {
            database.DSLQuery("INSERT INTO Reservering (ISBN,ReserveringsDatum,LidNr) VALUES ("+ISBN+",'"+datum+"',"+lidNr+");");
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Get query
    @ApiOperation(value = "findReservering", notes = "Vind reservering")
    @RequestMapping(value="/reservering", method=GET)
    public Reservering getReservering(@RequestParam(value="lidnr") String lidnr, @RequestParam(value="ISBN") String isbn){
        Reservering reservering = null;
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Reservering WHERE lidnr = "+lidnr+" AND ISBN = "+isbn+";");
            while (data.next()) {
                reservering = new Reservering(leden.getLid(data.getString(2)),data.getString(1),boeken.getBoek(data.getString(3)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return reservering;
    }

    //Delete query
    @ApiOperation(value = "deleteReservering", notes = "Reservering verwijderen")
    @RequestMapping(value="/reservering", method=DELETE)
    public void deleteBoek(@RequestParam(value="ISBN") String isbn,@RequestParam(value="lidnr") String lidnr){
        try {
            database.DSLQuery("DELETE FROM Reservering WHERE ISBN ="+isbn+" AND LidNr = "+lidnr+";");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //Update query
    @ApiOperation(value = "updateReservering", notes = "Update een reservering")
    @RequestMapping(value="/reservering", method=PUT)
    public void updateReservering(@RequestParam(value="ISBN") String isbn,@RequestParam(value="lidnr") String lidnr, @RequestParam(value="datum") String datum){
        try {
            database.DSLQuery("UPDATE Reservering SET ReserveringsDatum = '"+datum+"' WHERE ISBN = "+isbn+" AND LidNr = "+lidnr+";");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //ledenlijst
    @ApiOperation(value = "getReserveringLeden", notes="Lijst aan mensen met reserveringen")
    @RequestMapping(value="/reserveringen/leden", method=GET)
    public List<Integer> getLeden(){
        List<Integer> leden = new ArrayList<Integer>();
        ResultSet lidlijst = database.selectQuery("SELECT DISTINCT Lid.LidNr FROM Lid INNER JOIN Reservering ON Reservering.LidNr = Lid.LidNr");
        try {
            while (lidlijst.next()) {
                leden.add(lidlijst.getInt(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return leden;
    }
}
