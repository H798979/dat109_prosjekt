package dat109.prosjekt3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dat109.prosjekt3.Repo.ForelesningRepo;
import dat109.prosjekt3.Repo.TilbakemeldingVerdi;
import dat109.prosjekt3.entity.Forelesning;
import dat109.prosjekt3.entity.Tilbakemelding;

import java.util.Map;

@RestController
@RequestMapping("/api/forelesninger/{forelesningId}")
public class TilbakemeldingController {

    @Autowired TilbakemeldingService tilbakemeldingService;
    @Autowired ForelesningRepo forelesningRepo;

    @PostMapping("/tilbakemelding")
    public ResponseEntity<?> giTilbakemelding(@PathVariable Long forelesningId, @RequestBody Map<String, String> body) {
        Forelesning f = forelesningRepo.findById(forelesningId).orElse(null);
        if (f == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("feil", "Forelesning ikkje funnen."));

        try {
            TilbakemeldingVerdi vurdering = TilbakemeldingVerdi.valueOf(body.get("vurdering"));
            Tilbakemelding t = tilbakemeldingService.registrer(f, vurdering, body.get("studentToken"));
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "id", t.getId(),
                    "vurdering", t.getVurdering(),
                    "innsendt", t.getInnsendt()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("feil", e.getMessage()));
        }
    }

    @GetMapping("/statistikk")
    public ResponseEntity<?> hentStatistikk(@PathVariable Long forelesningId) {
        Forelesning f = forelesningRepo.findById(forelesningId).orElse(null);
        if (f == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("feil", "Forelesning ikkje funnen."));
        return ResponseEntity.ok(tilbakemeldingService.hentStatistikk(forelesningId));
    }
}
