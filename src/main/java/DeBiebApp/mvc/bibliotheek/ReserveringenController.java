package DeBiebApp.mvc.bibliotheek;


import DeBiebApp.api.bibliotheek.boeken.APIBoekenController;
import DeBiebApp.api.bibliotheek.leden.APILidController;
import DeBiebApp.api.bibliotheek.reserveringen.APIReserveringController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ReserveringenController {
    private APIReserveringController data = new APIReserveringController();
    private APILidController leden = new APILidController();
    private APIBoekenController boeken = new APIBoekenController();

    @RequestMapping("/reserveringen")
    public String reserveringenlijst(@RequestParam(value="order", defaultValue="ISBN") String order, Model model) {
        model.addAttribute("reserveringen",data.reserveringen(order));
        model.addAttribute("leden",leden.leden("LidNr"));
        model.addAttribute("boeken",boeken.boeken("ISBN"));
        return "CRUD/reserveringenlijst";
    }
    @RequestMapping("/reserveringen/del")
    public void deleteReservering(HttpServletResponse response, @RequestParam(value="ISBN") String ISBN, @RequestParam(value="lidnr") String LidNr){
        data.deleteBoek(ISBN,LidNr);
        try {
            response.sendRedirect("/reserveringen");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/reserveringen/ed")
    public String editReservering(@RequestParam(value="ISBN") String ISBN, @RequestParam(value="lidnr") String LidNr, Model model){
        model.addAttribute("reservering", data.getReservering(LidNr,ISBN));
        return "CRUD/reserveringenlijst_edit";
    }
    @RequestMapping("/reserveringen/edC")
    public void editReserveringC(HttpServletResponse response, @RequestParam(value="isbn") String ISBN, @RequestParam(value="lidnr") String Lidnr, @RequestParam(value="datum") String datum){
        data.updateReservering(ISBN, Lidnr, datum);
        try {
            response.sendRedirect("/reserveringen");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/reserveringen/add")
    public void addReservering(HttpServletResponse response, @RequestParam(value="isbn") String isbn, @RequestParam(value="lidnr") String lidnr, @RequestParam(value="datum") String datum){
        data.insertReservering(isbn, lidnr, datum);
        try {
            response.sendRedirect("/reserveringen");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}