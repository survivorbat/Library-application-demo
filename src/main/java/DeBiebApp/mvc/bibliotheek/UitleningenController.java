package DeBiebApp.mvc.bibliotheek;


import DeBiebApp.api.DB;
import DeBiebApp.api.bibliotheek.exemplaren.APIExemplaarController;
import DeBiebApp.api.bibliotheek.uitleningen.APIUitleningController;
import DeBiebApp.api.bibliotheek.leden.APILidController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UitleningenController {
    private APIUitleningController data = new APIUitleningController();
    private APIExemplaarController exemplaren = new APIExemplaarController();
    private APILidController leden = new APILidController();
    private DB database = new DB();

    @RequestMapping("/uitleningen")
    public String uitleningenlijst(@RequestParam(value="order", defaultValue="LidNr") String order, Model model) {
        model.addAttribute("uitleningen",data.uitleningen(order));
        model.addAttribute("exemplaren",exemplaren.exemplaren("ExemplaarID"));
        model.addAttribute("leden",leden.leden("LidNr"));
        return "CRUD/uitleningenlijst";
    }
    @RequestMapping("/uitleningen/del")
    public void deleteUitlening(HttpServletResponse response, @RequestParam(value="exemplaarid") String exemplaarID, @RequestParam(value="lidnr") String LidNr){
        data.deleteUitlening(LidNr,exemplaarID);
        try {
            response.sendRedirect("/uitleningen");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/uitleningen/ed")
    public String editUitlening(@RequestParam(value="exemplaarid") String ExemplaarID, @RequestParam(value="lidnr") String LidNr, Model model){
        model.addAttribute("uitlening", data.getUitlening(LidNr,ExemplaarID));
        return "CRUD/uitleningenlijst_edit";
    }
    @RequestMapping("/uitleningen/edC")
    public void editUitleningC(HttpServletResponse response, @RequestParam(value="exemplaarid") String exemplaarID, @RequestParam(value="lidnr") String Lidnr, @RequestParam(value="datum") String datum){
        data.updateUitlening(exemplaarID, Lidnr, datum);
        try {
            response.sendRedirect("/uitleningen");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/uitleningen/add")
    public void addUitlening(HttpServletResponse response, @RequestParam(value="exemplaarid") String exemplaarID, @RequestParam(value="lidnr") String lidnr, @RequestParam(value="datum") String datum){
        data.insertUitlening(exemplaarID, lidnr, datum);
        try {
            response.sendRedirect("/uitleningen");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}