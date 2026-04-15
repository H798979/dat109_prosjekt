package dat109.prosjekt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dat109.prosjekt.Repo.StatistikkResponse;
import dat109.prosjekt.Repo.TilbakemeldingRepo;
import dat109.prosjekt.entity.Forelesning;
import dat109.prosjekt.entity.Tilbakemelding;
import dat109.prosjekt.entity.TilbakemeldingVerdi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public StatistikkResponse hentStatistikk(Long forelesningId) {
        long g = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.gronn);
        long u = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.GUL);
        long r = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.rod);
        return new StatistikkResponse(g, u, r, g + u + r);
    }

    /**
     * @param token
     * @return
     * @throws RuntimeException
     */
    public String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 ikkje tilgjengeleg", e);
        }
    }
}
