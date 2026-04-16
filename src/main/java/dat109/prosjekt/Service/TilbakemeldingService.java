package dat109.prosjekt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dat109.prosjekt.Repo.TilbakemeldingRepo;
import dat109.prosjekt.entity.Forelesning;
import dat109.prosjekt.entity.Tilbakemelding;
import dat109.prosjekt.entity.TilbakemeldingVerdi;

import java.util.Map;
import java.time.LocalDateTime;

@Service
public class TilbakemeldingService {

    @Autowired TilbakemeldingRepo tilbakemeldingRepo;

    /**
     * @param forelesning
     * @param vurdering
     * @param studentToken
     * @return
     * @throws IllegalStateException
     */
    public Tilbakemelding registrer(Forelesning forelesning, TilbakemeldingVerdi vurdering, String studentToken) {
        String hash = hashToken(studentToken);
        if (tilbakemeldingRepo.existsByForelesningIdAndStudentTokenHash(forelesning.getId(), hash)) {
            throw new IllegalStateException("Studenten har allereie gitt tilbakemelding.");
        }
        Tilbakemelding t = new Tilbakemelding(vurdering, LocalDateTime.now(), forelesning, hash);
        return tilbakemeldingRepo.save(t);
    }

    /**
     * @param forelesningId
     * @return
     */
    public Map<String, Long> hentStatistikk(Long forelesningId) {
        long g = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.GRONN);
        long u = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.GUL);
        long r = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.ROD);
        return Map.of("gronn", g, "gul", u, "rod", r, "totalt", g + u + r);
    }

    /**
     * @param token
     * @return
     */
    public String hashToken(String token) {
        return String.valueOf(token.hashCode());
    }
}
