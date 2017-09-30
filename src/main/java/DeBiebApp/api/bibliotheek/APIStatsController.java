package DeBiebApp.api.bibliotheek;

import DeBiebApp.api.DB;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api")
public class APIStatsController {
    private DB db;
    public APIStatsController(){
        this.db=new DB();
    }
    @RequestMapping(value="stats", method=GET)
    public Stats stats(){
        Stats stats = new Stats();
        try {
            ResultSet res1 = db.selectQuery("SELECT COUNT(*) AS total FROM Boek");
            if(res1.next()){
                stats.setBoeken(res1.getInt("total"));
            }
            ResultSet res2 = db.selectQuery("SELECT COUNT(*) AS total FROM Exemplaar");
            if(res2.next()){
                stats.setExemplaren(res2.getInt("total"));
            }
            ResultSet res3 = db.selectQuery("SELECT COUNT(*) AS total FROM Uitlening");
            if(res3.next()){
                stats.setUitleningen(res3.getInt("total"));
            }
            ResultSet res4 = db.selectQuery("SELECT COUNT(*) AS total FROM Reservering");
            if(res4.next()){
                stats.setReserveringen(res4.getInt("total"));
            }
            ResultSet res5 = db.selectQuery("SELECT COUNT(*) AS total FROM Lid");
            if(res5.next()){
                stats.setLeden(res5.getInt("total"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return stats;
    }
}
