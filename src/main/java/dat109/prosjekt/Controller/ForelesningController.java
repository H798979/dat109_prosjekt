package dat109.prosjekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dat109.prosjekt.Repo.ForelesningRepo;
import dat109.prosjekt.entity.Forelesning;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/forelesninger")
public class ForelesningController {

    @Autowired ForelesningRepo forelesningRepo;

    @PostMapping
    public ResponseEntity<?> opprett(@RequestBody Forelesning forelesning) {
        Forelesning lagra = forelesningRepo.save(forelesning);
        return ResponseEntity.status(HttpStatus.CREATED).body(lagra);
    }

    @GetMapping
    public ResponseEntity<List<Forelesning>> hentAlle() {
        return ResponseEntity.ok(forelesningRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> hentMedId(@PathVariable Long id) {
        Forelesning f = forelesningRepo.findById(id).orElse(null);
        if (f == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("feil", "Forelesning ikkje funnen."));
        return ResponseEntity.ok(f);
    }
}
