package DeBiebApp.api.bibliotheek.leden;

import DeBiebApp.api.DB;
import DeBiebApp.api.bibliotheek.boeken.APIBoekenController;
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
public class APILidController {
    private DB database;
    public APILidController(){
        database = new DB();
    }
    //Lijst
    @ApiOperation(value = "findAllLeden", notes = "Alle leden opvragen")
    @RequestMapping(value="/leden",method=GET)
    public List<Lid> leden(@RequestParam(value="order", defaultValue = "LidNr") String order){
        List<Lid> lidLijst = new ArrayList<Lid>();
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Lid ORDER BY "+order+";");
            while (data.next()) {
                lidLijst.add(new Lid(   data.getInt(1),
                                        data.getString(2),
                                        data.getString(3),
                                        data.getString(4),
                                        data.getInt(5),
                                        data.getString(6),
                                        data.getInt(7),
                                        data.getString(8),
                                        data.getDouble(9)
                ));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return lidLijst;
    }
    //Insert query
    @ApiOperation(value = "addLid", notes = "Een lid toevoegen")
    @RequestMapping(value="/lid", method=POST)
    public void insertLid(@RequestParam(value="lidnr") String id,@RequestParam(value="voornaam") String voornaam,@RequestParam(value="achternaam") String achternaam,@RequestParam(value="straat") String straat, @RequestParam(value="huisnummer") String huisnummer, @RequestParam(value="stad") String stad, @RequestParam(value="telefoon") String telefoon, @RequestParam(value="email") String email, @RequestParam(value="boete") String boete){
        try {
            database.DSLQuery("INSERT INTO Lid (Lidnr,Voornaam,Achternaam,Straat,Huisnummer,Stad,Telefoonnummer,Email,Boete) VALUES ("+id+",'"+voornaam+"','"+achternaam+"','"+straat+"',"+huisnummer+",'"+stad+"',"+telefoon+",'"+email+"',"+boete+");");
        }
        catch(Exception e){}
    }
    //Get query
    @ApiOperation(value = "findLid", notes = "Vind lid")
    @RequestMapping(value="/lid", method=GET)
    public Lid getLid(@RequestParam(value="lidnr") String id){
        Lid lid = null;
        APIBoekenController boeken = new APIBoekenController();
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Lid WHERE LidNr = "+Integer.parseInt(id)+";");
            while (data.next()) {
                lid = new Lid(   data.getInt(1),
                        data.getString(2),
                        data.getString(3),
                        data.getString(4),
                        data.getInt(5),
                        data.getString(6),
                        data.getInt(7),
                        data.getString(8),
                        data.getDouble(9)
                );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lid;
    }

    //Delete query
    @ApiOperation(value = "deleteLid", notes = "Lid verwijderen")
    @RequestMapping(value="/lid", method=DELETE)
    public void deleteBoek(@RequestParam(value="lidnr") String id){
        try {
            database.DSLQuery("DELETE FROM Lid WHERE LidNr ="+Integer.parseInt(id)+";");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //Update query
    @ApiOperation(value = "updateLid", notes = "Update een lid")
    @RequestMapping(value="/lid", method=PUT)
    public void updateLid(@RequestParam(value="lidnr") String id,@RequestParam(value="voornaam") String voornaam,@RequestParam(value="achternaam") String achternaam,@RequestParam(value="straat") String straat, @RequestParam(value="huisnummer") String huisnummer, @RequestParam(value="stad") String stad, @RequestParam(value="telefoon") String telefoon, @RequestParam(value="email") String email, @RequestParam(value="boete") String boete){
        try {
            database.DSLQuery("UPDATE Lid SET Voornaam = '"+voornaam+"', Achternaam='"+achternaam+"',Straat='"+straat+"',Huisnummer="+huisnummer+",Stad='"+stad+"',Telefoonnummer="+telefoon+",Email='"+email+"',Boete = "+boete+" WHERE LidNr = "+id+";");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
