package DeBiebApp.api.bibliotheek.exemplaren;

import io.swagger.annotations.ApiOperation;
import DeBiebApp.api.DB;
import DeBiebApp.api.bibliotheek.boeken.APIBoekenController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class APIExemplaarController {
    private DB database;
    public APIExemplaarController(){
        database = new DB();
    }
    //Lijst
    @ApiOperation(value = "findAllExemplaren", notes = "Alle exemplaren opvragen")
    @RequestMapping(value="/exemplaren",method=GET)
    public List<Exemplaar> exemplaren(@RequestParam(value="order", defaultValue = "ExemplaarID") String order){
        List<Exemplaar> exemplaarLijst = new ArrayList<Exemplaar>();
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Exemplaar ORDER BY "+order+";");
            APIBoekenController boeken = new APIBoekenController();
            while (data.next()) {
                exemplaarLijst.add(new Exemplaar(boeken.getBoek(data.getString(3)), data.getInt(1), data.getInt(2)));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return exemplaarLijst;
    }
    //Insert query
    @ApiOperation(value = "addExemplaar", notes = "Een exemplaar toevoegen")
    @RequestMapping(value="/exemplaar", method=POST)
    public void insertExemplaar(@RequestParam(value="ISBN") String ISBN,@RequestParam(value="exemplaarid") String exemplaarid, @RequestParam(value="uitleenperiode") String uitleenperiode){
        try {
            database.DSLQuery("INSERT INTO Exemplaar (ISBN,ExemplaarID,Uitleenperiode) VALUES ("+Integer.parseInt(ISBN)+","+exemplaarid+","+uitleenperiode+");");
        }
        catch(Exception e){}
    }
    //Get query
    @ApiOperation(value = "findExemplaar", notes = "Vind exemplaar")
    @RequestMapping(value="/exemplaar", method=GET)
    public Exemplaar getExemplaar(@RequestParam(value="exemplaarid") String id){
        Exemplaar exemplaar = null;
        APIBoekenController boeken = new APIBoekenController();
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Exemplaar WHERE ExemplaarID = "+Integer.parseInt(id)+";");
            while (data.next()) {
                exemplaar = new Exemplaar(boeken.getBoek(data.getString(3)),data.getInt(1),data.getInt(2));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return exemplaar;
    }

    //Delete query
    @ApiOperation(value = "deleteExemplaar", notes = "Exemplaar verwijderen")
    @RequestMapping(value="/exemplaar", method=DELETE)
    public void deleteExemplaar(@RequestParam(value="exemplaarID") String id){
        try {
            database.DSLQuery("DELETE FROM Exemplaar WHERE ExemplaarID ="+Integer.parseInt(id)+";");
        }
        catch(Exception e){}
    }
    //Update query
    @ApiOperation(value = "updateExemplaar", notes = "Update een exemplaar")
    @RequestMapping(value="/exemplaar", method=PUT)
    public void updateExemplaar(@RequestParam(value="exemplaarid") String id, @RequestParam(value="uitleenperiode") String uitleenperiode){
        try {
            database.DSLQuery("UPDATE Exemplaar SET Uitleenperiode = "+Integer.parseInt(uitleenperiode)+" WHERE ExemplaarID = "+Integer.parseInt(id)+";");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    //get boeke
    @ApiOperation(value = "getExemplaarBoeken", notes="Lijst aan boeken met exemplaren")
    @RequestMapping(value="/exemplaren/boeken", method=GET)
    public List<Integer> getBoekenlijst(){
        List<Integer> boeken = new ArrayList<Integer>();
        ResultSet boeklijst = database.selectQuery("SELECT DISTINCT Boek.ISBN FROM Boek INNER JOIN Exemplaar ON Exemplaar.ISBN = Boek.ISBN");
        try {
            while (boeklijst.next()) {
                boeken.add(boeklijst.getInt(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return boeken;
    }
}
