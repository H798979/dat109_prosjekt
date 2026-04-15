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

/**
 * Teneste som handterer forretningslogikk knytt til tilbakemeldingar.
 * Inkluderer registrering av nye tilbakemeldingar, henting av statistikk,
 * og hashing av student-token for å sikre anonymitet og hindre duplikat.
 */
@Service
public class TilbakemeldingService {

    @Autowired TilbakemeldingRepo tilbakemeldingRepo;

    /**
     * Registrerer ei ny tilbakemelding på ei forelesning.
     * Hashar studentens token og sjekkar om studenten allereie har gitt tilbakemelding.
     *
     * @param forelesning forelesinga det gjeld
     * @param vurdering   vurderinga studenten gir (GROENN, GUL eller ROED)
     * @param studentToken den unike tokenen til studenten (blir hasha før lagring)
     * @return den lagra tilbakemeldinga
     * @throws IllegalStateException dersom studenten allereie har gitt tilbakemelding
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
     * Hentar statistikk for ei forelesning – antal grøne, gule og raude tilbakemeldingar.
     *
     * @param forelesningId ID-en til forelesinga
     * @return eit {@link StatistikkResponse}-objekt med tala fordelt på kvar vurdering
     */
    public StatistikkResponse hentStatistikk(Long forelesningId) {
        long g = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.GROENN);
        long u = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.GUL);
        long r = tilbakemeldingRepo.countByForelesningIdAndVurdering(forelesningId, TilbakemeldingVerdi.ROED);
        return new StatistikkResponse(g, u, r, g + u + r);
    }

    /**
     * Hashar ein student-token med SHA-256.
     * Tokenen blir aldri lagra i klartekst – berre hashen blir brukt.
     *
     * @param token studentens unike token
     * @return SHA-256-hash som heksadesimal streng
     * @throws RuntimeException dersom SHA-256 ikkje er tilgjengeleg
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
