package dat109.prosjekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dat109.prosjekt.Repo.ForelesningRepo;
import dat109.prosjekt.Service.TilbakemeldingService;
import dat109.prosjekt.entity.Forelesning;
import dat109.prosjekt.entity.TilbakemeldingVerdi;

import java.util.Optional;

@Controller
@RequestMapping("/tilbakemelding")
public class TilbakemeldingController {

    @Autowired TilbakemeldingService tilbakemeldingService;
    @Autowired ForelesningRepo forelesningRepo;

    /**
     * @param forelesningId
     * @param model
     * @param ra
     * @return
     */
    @GetMapping("/skjema")
    public String visSkjema(@RequestParam Long forelesningId, Model model, RedirectAttributes ra) {
        Optional<Forelesning> opt = forelesningRepo.findById(forelesningId);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("feil", "Forelesning ikkje funnen.");
            return "redirect:/forelesninger";
        }
        model.addAttribute("forelesning", opt.get());
        model.addAttribute("vurderinger", TilbakemeldingVerdi.values());
        return "giTilbakemelding";
    }

    /**
     * @param forelesningId
     * @param vurdering
     * @param studentToken
     * @param ra
     * @return
     */
    @PostMapping("/send")
    public String giTilbakemelding(@RequestParam Long forelesningId,
                                   @RequestParam String vurdering,
                                   @RequestParam String studentToken,
                                   RedirectAttributes ra) {
        Optional<Forelesning> opt = forelesningRepo.findById(forelesningId);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("feil", "Forelesning ikkje funnen.");
            return "redirect:/forelesninger";
        }

        try {
            TilbakemeldingVerdi verdi = TilbakemeldingVerdi.valueOf(vurdering);
            tilbakemeldingService.registrer(opt.get(), verdi, studentToken);
            ra.addFlashAttribute("melding", "Tilbakemelding registrert!");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("feil", "Ugyldig vurdering.");
        } catch (IllegalStateException e) {
            ra.addFlashAttribute("feil", e.getMessage());
        }
        return "redirect:/forelesninger/vis?id=" + forelesningId;
    }
}
