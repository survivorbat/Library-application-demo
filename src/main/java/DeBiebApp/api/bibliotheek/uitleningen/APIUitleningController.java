package DeBiebApp.api.bibliotheek.uitleningen;

import DeBiebApp.api.bibliotheek.exemplaren.APIExemplaarController;
import io.swagger.annotations.ApiOperation;
import DeBiebApp.api.DB;
import DeBiebApp.api.bibliotheek.leden.APILidController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class APIUitleningController {
    private DB database;
    private APIExemplaarController exemplaren;
    private APILidController leden;
    public APIUitleningController(){
        database = new DB();
        exemplaren = new APIExemplaarController();
        leden = new APILidController();
    }
    //Lijst
    @ApiOperation(value = "findAllUitleningen", notes = "Alle uitleningen opvragen")
    @RequestMapping(value="/uitleningen",method=GET)
    public List<Uitlening> uitleningen(@RequestParam(value="order", defaultValue = "LidNr") String order){
        List<Uitlening> uitleningLijst = new ArrayList<Uitlening>();
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Uitlening ORDER BY "+order+";");
            while (data.next()) {
                uitleningLijst.add(new Uitlening(exemplaren.getExemplaar(data.getString(3)),leden.getLid(data.getString(2)),data.getString(1)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return uitleningLijst;
    }
    //Insert query
    @ApiOperation(value = "addUitlening", notes = "Een uitlening toevoegen")
    @RequestMapping(value="/uitlening", method=POST)
    public void insertUitlening(@RequestParam(value="exemplaarid") String ExemplaarID,@RequestParam(value="lidnr") String LidNr,@RequestParam(value="datum") String datum){
        try {
            database.DSLQuery("INSERT INTO Uitlening (ExemplaarID,LidNr,DatumTerugave) VALUES ("+ExemplaarID+",'"+LidNr+"','"+datum+"');");
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Get query
    @ApiOperation(value = "findUitlening", notes = "Vind uitlening")
    @RequestMapping(value="/uitlening", method=GET)
    public Uitlening getUitlening(@RequestParam(value="lidnr") String lidnr, @RequestParam(value="exemplaarid") String ExemplaarID){
        Uitlening uitlening = null;
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Uitlening WHERE lidnr = "+lidnr+" AND ExemplaarID = "+ExemplaarID+";");
            while (data.next()) {
                uitlening = new Uitlening(exemplaren.getExemplaar(data.getString(3)),leden.getLid(data.getString(2)),data.getString(1));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return uitlening;
    }

    //Delete query
    @ApiOperation(value = "deleteUitlening", notes = "Uitlening verwijderen")
    @RequestMapping(value="/uitlening", method=DELETE)
    public void deleteUitlening(@RequestParam(value="lidnr") String lidnr,@RequestParam(value="exemplaarid") String exemplaarID){
        try {
            database.DSLQuery("DELETE FROM Uitlening WHERE LidNr ="+lidnr+" AND ExemplaarID = "+exemplaarID+";");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //Update query
    @ApiOperation(value = "updateUitlening", notes = "Update een uitlening")
    @RequestMapping(value="/uitlening", method=PUT)
    public void updateUitlening(@RequestParam(value="exemplaarid") String ExemplaarID,@RequestParam(value="lidnr") String lidnr, @RequestParam(value="datum") String datum){
        try {
            database.DSLQuery("UPDATE Uitlening SET DatumTerugave = '"+datum+"' WHERE ExemplaarID = "+ExemplaarID+" AND LidNr = "+lidnr+";");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "getUitleningLeden", notes="Lijst aan mensen met uitleningen")
    @RequestMapping(value="/uitleningen/leden", method=GET)
    public List<Integer> getLeden(){
        List<Integer> leden = new ArrayList<Integer>();
        ResultSet lidlijst = database.selectQuery("SELECT DISTINCT Lid.LidNr FROM Lid INNER JOIN Uitlening ON Uitlening.LidNr = Lid.LidNr");
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
    @ApiOperation(value = "getUitleningExemplaar", notes="Lijst aan exemplaren met uitleningen")
    @RequestMapping(value="/uitleningen/exemplaren", method=GET)
    public List<Integer> getExemplaren(){
        List<Integer> leden = new ArrayList<Integer>();
        ResultSet lidlijst = database.selectQuery("SELECT DISTINCT Exemplaar.ExemplaarID FROM Exemplaar INNER JOIN Uitlening ON Uitlening.ExemplaarID = Exemplaar.ExemplaarID");
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
