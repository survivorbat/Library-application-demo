package DeBiebApp.mvc.bibliotheek;


import DeBiebApp.api.bibliotheek.exemplaren.APIExemplaarController;
import DeBiebApp.api.bibliotheek.uitleningen.APIUitleningController;
import DeBiebApp.api.bibliotheek.boeken.APIBoekenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ExemplarenController {
    private APIExemplaarController data = new APIExemplaarController();
    private APIBoekenController boeken = new APIBoekenController();
    private APIUitleningController uitleningen = new APIUitleningController();

    @RequestMapping("/exemplaren")
    public String exemplarenlijst(@RequestParam(value="order", defaultValue="ExemplaarID") String order, Model model) {
        model.addAttribute("exemplaren",data.exemplaren(order));
        model.addAttribute("boeken",boeken.boeken("ISBN"));
        model.addAttribute("uitleningen",uitleningen.getExemplaren());
        return "CRUD/exemplarenlijst";
    }
    @RequestMapping("/exemplaren/del")
    public void deleteExemplaar(HttpServletResponse response, @RequestParam(value="exemplaarid") String exemplaarid){
        data.deleteExemplaar(exemplaarid);
        try {
            response.sendRedirect("/exemplaren");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/exemplaren/ed")
    public String editExemplaar(@RequestParam(value="exemplaarid") String ExemplaarID, Model model){
        model.addAttribute("exemplaar", data.getExemplaar(ExemplaarID));
        return "CRUD/exemplarenlijst_edit";
    }
    @RequestMapping("/exemplaren/edC")
    public void editExemplaarC(HttpServletResponse response, @RequestParam(value="exemplaarid") String exemplaarid, @RequestParam(value="uitleenperiode") String uitleenperiode){
        data.updateExemplaar(exemplaarid, uitleenperiode);
        try {
            response.sendRedirect("/exemplaren");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/exemplaren/add")
    public void addExemplaar(HttpServletResponse response, @RequestParam(value="exemplaarid") String exemplaarid, @RequestParam(value="ISBN") String ISBN, @RequestParam(value="uitleenperiode") String uitleenperiode){
        data.insertExemplaar(ISBN, exemplaarid, uitleenperiode);
        try {
            response.sendRedirect("/exemplaren");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}