package dat109.prosjekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dat109.prosjekt.Repo.ForelesningRepo;
import dat109.prosjekt.Service.TilbakemeldingService;
import dat109.prosjekt.entity.Forelesning;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("admin") == null) {
            ra.addFlashAttribute("feil", "Du må logge inn først.");
            return "redirect:/login";
        }
        List<Forelesning> forelesninger = forelesningRepo.findAll();
        model.addAttribute("forelesninger", forelesninger);
        return "admin";
    }

    @GetMapping("/login")
    public String visLogin() {
        return "login";
    }

    /**
     * @param brukarnavn
     * @param passord
     * @param session
     * @param ra
     * @return
     */

    @PostMapping("/login")
    public String login(@RequestParam String brukarnavn,
                        @RequestParam String passord,
                        HttpSession session,
                        RedirectAttributes ra) {
        if ("admin".equals(brukarnavn) && "12345".equals(passord)) {
            session.setAttribute("admin", true);
            return "redirect:/admin";
        }
        ra.addFlashAttribute("feil", "Feil brukarnavn eller passord.");
        return "redirect:/login";
    }

    @GetMapping("/loggut")
    public String loggUt(HttpSession session) {
        session.invalidate();
        return "redirect:/forelesninger";
    }

    /**
     * @param id
     * @param model
     * @param ra
     * @return
     */


    @GetMapping("/forelesninger/vis")
    public String hentMedId(@RequestParam Long id, Model model, RedirectAttributes ra) {
        Optional<Forelesning> opt = forelesningRepo.findById(Objects.requireNonNull(id));
        if (opt.isEmpty()) {
            ra.addFlashAttribute("feil", "Forelesning ikkje funnen.");
            return "redirect:/forelesninger";
        }
        model.addAttribute("forelesning", opt.get());
        model.addAttribute("statistikk", tilbakemeldingService.hentStatistikk(id));
        return "forelesning";
    }

    /**
     * @param navn
     * @param tidspunkt
     * @param sted
     * @param ra
     * @return
     */
    

    @PostMapping("/forelesninger/opprett")
    public String opprett(@RequestParam String navn,
                          @RequestParam String tidspunkt,
                          @RequestParam String sted,
                          HttpSession session,
                          RedirectAttributes ra) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        LocalDateTime dt = LocalDateTime.parse(tidspunkt);
        Forelesning f = new Forelesning(navn, dt, sted);
        forelesningRepo.save(f);
        ra.addFlashAttribute("melding", "Forelesning oppretta!");
        return "redirect:/admin";
    }

    /**
     * @param id
     * @param ra
     * @return
     */

    @PostMapping("/forelesninger/slett")
    public String slett(@RequestParam Long id, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        if (forelesningRepo.existsById(Objects.requireNonNull(id))) {
            forelesningRepo.deleteById(id);
            ra.addFlashAttribute("melding", "Forelesning sletta!");
        } else {
            ra.addFlashAttribute("feil", "Forelesning ikkje funnen.");
        }
        return "redirect:/admin";
    }
}
