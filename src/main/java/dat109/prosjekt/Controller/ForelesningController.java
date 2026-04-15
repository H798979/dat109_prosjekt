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

/**
 * Controller som handterer visning og oppretting av forelesningar.
 * Bruker JSP-views via Spring MVC sin view resolver.
 */
@Controller
@RequestMapping("/forelesninger")
public class ForelesningController {

    @Autowired ForelesningRepo forelesningRepo;
    @Autowired TilbakemeldingService tilbakemeldingService;

    /**
     * Hentar alle forelesningar og sender dei til forelesninger.jsp.
     *
     * @param model modellobjekt for å sende data til JSP
     * @return view-namn "forelesninger"
     */
    @GetMapping
    public String hentAlle(Model model) {
        List<Forelesning> forelesninger = forelesningRepo.findAll();
        model.addAttribute("forelesninger", forelesninger);
        return "forelesninger";
    }

    /**
     * Hentar ei enkelt forelesning med tilhøyrande statistikk og sender til forelesning.jsp.
     * Dersom forelesinga ikkje finst, blir brukaren omdirigert med feilmelding.
     *
     * @param id  ID-en til forelesinga
     * @param model modellobjekt for data til JSP
     * @param ra   RedirectAttributes for flash-meldingar ved redirect
     * @return view-namn "forelesning", eller redirect til /forelesninger ved feil
     */
    @GetMapping("/vis")
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
     * Opprettar ei ny forelesning basert på skjemadata og lagrar ho i databasen.
     *
     * @param namn       namnet på forelesinga
     * @param tidspunkt  dato og klokkeslett frå datetime-local inputfeltet
     * @param sted       staden der forelesinga blir halden
     * @param ra         RedirectAttributes for kvitteringsmelding
     * @return redirect til forelesningslista
     */
    @PostMapping("/opprett")
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
