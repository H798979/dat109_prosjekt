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

/**
 * Controller som handterer innsending av tilbakemeldingar frå studentar.
 * Viser tilbakemeldingsskjema og tek imot vurderingar via POST.
 */
@Controller
@RequestMapping("/tilbakemelding")
public class TilbakemeldingController {

    @Autowired TilbakemeldingService tilbakemeldingService;
    @Autowired ForelesningRepo forelesningRepo;

    /**
     * Viser skjemaet for å gi tilbakemelding på ei forelesning.
     *
     * @param forelesningId ID-en til forelesinga det gjeld
     * @param model         modellobjekt for data til JSP
     * @param ra            RedirectAttributes for flash-meldingar ved feil
     * @return view-namn "giTilbakemelding", eller redirect ved ugyldig forelesning
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
     * Tek imot ei tilbakemelding frå ein student og registrerer ho i databasen.
     * Validerer at forelesinga finst, at vurderinga er gyldig, og at studenten
     * ikkje allereie har gitt tilbakemelding (via hasha token).
     *
     * @param forelesningId ID-en til forelesinga
     * @param vurdering     vurderinga som streng (GROENN, GUL eller ROED)
     * @param studentToken  studentens unike token
     * @param ra            RedirectAttributes for kvittering eller feilmelding
     * @return redirect til forelesningssida med resultatmelding
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
