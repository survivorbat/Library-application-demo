package DeBiebApp.mvc.bibliotheek;


import DeBiebApp.api.bibliotheek.uitleningen.APIUitleningController;
import DeBiebApp.api.bibliotheek.leden.APILidController;
import DeBiebApp.api.bibliotheek.reserveringen.APIReserveringController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LedenController {
    private APILidController data = new APILidController();
    private APIUitleningController uitleningen = new APIUitleningController();
    private APIReserveringController reserveringen = new APIReserveringController();

    @RequestMapping("/leden")
    public String ledenlijst(@RequestParam(value="order", defaultValue="LidNr") String order, Model model) {
        model.addAttribute("leden",data.leden(order));
        model.addAttribute("uitleningLeden",uitleningen.getLeden());
        model.addAttribute("reserveringLeden",reserveringen.getLeden());
        return "CRUD/ledenlijst";
    }
    @RequestMapping("/leden/del")
    public void deleteLid(HttpServletResponse response, @RequestParam(value="lidnr") String LidNr){
        data.deleteBoek(LidNr);
        try {
            response.sendRedirect("/leden");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/leden/ed")
    public String editLid(@RequestParam(value="lidnr") String LidNr, Model model){
        model.addAttribute("lid", data.getLid(LidNr));
        return "CRUD/ledenlijst_edit";
    }
    @RequestMapping("/leden/edC")
    public void editLidC(HttpServletResponse response, @RequestParam(value="lidnr") String id,@RequestParam(value="voornaam") String voornaam,@RequestParam(value="achternaam") String achternaam,@RequestParam(value="straat") String straat, @RequestParam(value="huisnummer") String huisnummer, @RequestParam(value="stad") String stad, @RequestParam(value="telefoon") String telefoon, @RequestParam(value="email") String email, @RequestParam(value="boete") String boete){
        data.updateLid(id, voornaam, achternaam, straat, huisnummer, stad, telefoon, email, boete);
        try {
            response.sendRedirect("/leden");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/leden/add")
    public void addLid(HttpServletResponse response, @RequestParam(value="lidnr") String id,@RequestParam(value="voornaam") String voornaam,@RequestParam(value="achternaam") String achternaam,@RequestParam(value="straat") String straat, @RequestParam(value="huisnummer") String huisnummer, @RequestParam(value="stad") String stad, @RequestParam(value="telefoon") String telefoon, @RequestParam(value="email") String email, @RequestParam(value="boete") String boete){
        data.insertLid(id, voornaam, achternaam, straat, huisnummer, stad, telefoon, email, boete);
        try {
            response.sendRedirect("/leden");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}