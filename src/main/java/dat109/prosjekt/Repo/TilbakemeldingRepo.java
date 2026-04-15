package dat109.prosjekt.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dat109.prosjekt.entity.Tilbakemelding;

import java.util.List;

@Repository
public interface TilbakemeldingRepo extends JpaRepository<Tilbakemelding, Long> {

    boolean existsByForelesningIdAndStudentTokenHash(Long forelesningId, String studentTokenHash);

    List<Tilbakemelding> findByForelesningId(Long forelesningId);

    long countByForelesningIdAndVurdering(Long forelesningId, TilbakemeldingVerdi vurdering);
}
