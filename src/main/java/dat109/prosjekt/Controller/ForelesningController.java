package dat109.prosjekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dat109.prosjekt.Repo.ForelesningRepo;
import dat109.prosjekt.Service.TilbakemeldingService;
import dat109.prosjekt.entity.Forelesning;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ForelesningController {

    @Autowired ForelesningRepo forelesningRepo;
    @Autowired TilbakemeldingService tilbakemeldingService;

    @GetMapping("/")
    public String root() {
        return "redirect:/forelesninger";
    }

    @GetMapping("/forelesninger")
    public String hentAlle(Model model) {
        List<Forelesning> forelesninger = forelesningRepo.findAll();
        model.addAttribute("forelesninger", forelesninger);
        return "forelesninger";
    }

    /**
     * @param id
     * @param model
     * @param ra
     * @return
     */


    @GetMapping("/forelesninger/vis")
    public String hentMedId(@RequestParam Long id, Model model, RedirectAttributes ra) {
        Optional<Forelesning> opt = forelesningRepo.findById(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("feil", "Forelesning ikkje funnen.");
            return "redirect:/forelesninger";
        }
        model.addAttribute("forelesning", opt.get());
        model.addAttribute("statistikk", tilbakemeldingService.hentStatistikk(id));
        return "forelesning";
    }

    /**
     * @param namn
     * @param tidspunkt
     * @param sted
     * @param ra
     * @return
     */
    

    @PostMapping("/forelesninger/opprett")
    public String opprett(@RequestParam String namn,
                          @RequestParam String tidspunkt,
                          @RequestParam String sted,
                          RedirectAttributes ra) {
        LocalDateTime dt = LocalDateTime.parse(tidspunkt);
        Forelesning f = new Forelesning(namn, dt, sted);
        forelesningRepo.save(f);
        ra.addFlashAttribute("melding", "Forelesning oppretta!");
        return "redirect:/forelesninger";
    }
}
