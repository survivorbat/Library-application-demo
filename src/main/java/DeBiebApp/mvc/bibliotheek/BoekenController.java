package DeBiebApp.mvc.bibliotheek;


import DeBiebApp.api.bibliotheek.exemplaren.APIExemplaarController;
import DeBiebApp.api.bibliotheek.boeken.APIBoekenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class BoekenController {
    private APIBoekenController data = new APIBoekenController();
    private APIExemplaarController exemplaren = new APIExemplaarController();

    @RequestMapping("/boeken")
    public String boekenlijst(@RequestParam(value="order", defaultValue="ISBN") String order, Model model) {
        model.addAttribute("boeken",data.boeken(order));
        model.addAttribute("boekExemplaren", exemplaren.getBoekenlijst());
        return "CRUD/boekenlijst";
    }
    @RequestMapping("/boeken/del")
    public void deleteBoek(HttpServletResponse response, @RequestParam(value="ISBN") String ISBN){
        data.deleteBoek(ISBN);
        try {
            response.sendRedirect("/boeken");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @RequestMapping("/boeken/ed")
    public String editBoek(@RequestParam(value="ISBN") String ISBN, Model model){
        model.addAttribute("boek", data.getBoek(ISBN));
        return "CRUD/boekenlijst_edit";
    }
    @RequestMapping("/boeken/edC")
    public void editBoekC(HttpServletResponse response, @RequestParam(value="ISBN") String isbn, @RequestParam(value="titel") String titel, @RequestParam(value="auteur") String auteur, @RequestParam(value="editie", defaultValue="1") String editie){
        data.updateBoek(isbn,titel,auteur,editie);
        try {
            response.sendRedirect("/boeken");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping("/boeken/add")
    public void addBoek(HttpServletResponse response, @RequestParam(value="ISBN") String isbn, @RequestParam(value="titel") String titel, @RequestParam(value="auteur") String auteur, @RequestParam(value="editie", defaultValue="1") String editie){
        data.insertBoek(isbn,titel,auteur,editie);
        try {
            response.sendRedirect("/boeken");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}