package DeBiebApp.mvc;

import DeBiebApp.api.bibliotheek.APIStatsController;
import DeBiebApp.api.bibliotheek.exemplaren.APIExemplaarController;
import DeBiebApp.api.bibliotheek.uitleningen.APIUitleningController;
import DeBiebApp.api.bibliotheek.boeken.APIBoekenController;
import DeBiebApp.api.bibliotheek.leden.APILidController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    private APIStatsController stats = new APIStatsController();
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String frontPage(Model model){
        model.addAttribute("stats",stats.stats());
        model.addAttribute("uitleningen",new APIUitleningController().uitleningen("ExemplaarID"));
        model.addAttribute("boeken",new APIBoekenController().boeken("ISBN"));
        model.addAttribute("exemplaren",new APIExemplaarController().exemplaren("ExemplaarID"));
        model.addAttribute("leden",new APILidController().leden("LidNr"));
        return "index";
    }
}
