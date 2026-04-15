package dat109.prosjekt.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dat109.prosjekt.entity.Tilbakemelding;
import dat109.prosjekt.entity.TilbakemeldingVerdi;

import java.util.List;

/**
 * Repository-grensesnitt for {@link Tilbakemelding}-entiteten.
 * Tilbyr CRUD-operasjonar og tilpassa spørjemetoder for tilbakemeldingar.
 */
@Repository
public interface TilbakemeldingRepo extends JpaRepository<Tilbakemelding, Long> {

    /**
     * Sjekkar om det finst ei tilbakemelding for ein gitt forelesning og student-token-hash.
     *
     * @param forelesningId    ID-en til forelesinga
     * @param studentTokenHash SHA-256-hash av studentens token
     * @return true dersom ei tilbakemelding allereie finst
     */
    boolean existsByForelesningIdAndStudentTokenHash(Long forelesningId, String studentTokenHash);

    /**
     * Finn alle tilbakemeldingar knytt til ei forelesning.
     *
     * @param forelesningId ID-en til forelesinga
     * @return liste over tilbakemeldingar
     */
    List<Tilbakemelding> findByForelesningId(Long forelesningId);

    /**
     * Tel antal tilbakemeldingar med ei gitt vurdering for ei forelesning.
     *
     * @param forelesningId ID-en til forelesinga
     * @param vurdering     vurderinga å telje (GROENN, GUL eller ROED)
     * @return antal tilbakemeldingar med denne vurderinga
     */
    long countByForelesningIdAndVurdering(Long forelesningId, TilbakemeldingVerdi vurdering);
}
