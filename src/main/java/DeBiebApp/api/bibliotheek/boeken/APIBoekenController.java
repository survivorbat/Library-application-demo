package DeBiebApp.api.bibliotheek.boeken;

import DeBiebApp.api.DB;
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
public class APIBoekenController {
    private DB database;
    public APIBoekenController(){
        database = new DB();
    }
    //Lijst
    @ApiOperation(value = "findAllBooks", notes = "Alle boeken opvragen")
    @RequestMapping(value="/boeken",method=GET)
    public List<Boek> boeken(@RequestParam(value="order", defaultValue = "ISBN") String order){
        List<Boek> boekenLijst = new ArrayList<Boek>();
        try {
            boekenLijst.clear();
            ResultSet data = database.selectQuery("SELECT * FROM Boek ORDER BY "+order+";");
            while (data.next()) {
                boekenLijst.add(new Boek(data.getInt(1), data.getString(2), data.getString(3), data.getInt(4)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return boekenLijst;
    }
    //Insert query
    @ApiOperation(value = "addBoek", notes = "Een boek toevoegen")
    @RequestMapping(value="/boek", method=POST)
    public void insertBoek(@RequestParam(value="ISBN") String ISBN,@RequestParam(value="titel") String titel, @RequestParam(value="auteur") String auteur, @RequestParam(value="editie", defaultValue="1") String editie){
        try {
            database.DSLQuery("INSERT INTO Boek (ISBN,Titel,Auteur,Editie) VALUES ("+Integer.parseInt(ISBN)+",'"+titel+"','"+auteur+"',"+Integer.parseInt(editie)+")");
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Get query
    @ApiOperation(value = "findBook", notes = "Vind boek")
    @RequestMapping(value="/boek", method=GET)
    public Boek getBoek(@RequestParam(value="ISBN") String ISBN){
        Boek boek = null;
        try {
            ResultSet data = database.selectQuery("SELECT * FROM Boek WHERE ISBN = "+ISBN+";");
            while (data.next()) {
                boek = new Boek(data.getInt(1),data.getString(2),data.getString(3),data.getInt(4));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return boek;
    }
    //Delete query
    @ApiOperation(value = "deleteBoek", notes = "Boek verwijderen")
    @RequestMapping(value="/boek", method=DELETE)
    public void deleteBoek(@RequestParam(value="ISBN") String isbn){
        try {
            database.DSLQuery("DELETE FROM Boek WHERE ISBN ="+Integer.parseInt(isbn)+";");
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Update query
    @ApiOperation(value = "updateBoek", notes = "Update een boek")
    @RequestMapping(value="/boek", method=PUT)
    public void updateBoek(@RequestParam(value="ISBN") String isbn, @RequestParam(value="titel") String titel, @RequestParam(value="auteur") String auteur, @RequestParam(value="editie", defaultValue="1") String editie){
        try {
            database.DSLQuery("UPDATE Boek SET Titel = '"+titel+"', Auteur = '"+auteur+"', Editie = "+Integer.parseInt(editie)+" WHERE ISBN ="+Integer.parseInt(isbn)+";");
        }
        catch(Exception e){e.printStackTrace();}
    }
}
