package dat109.prosjekt.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dat109.prosjekt.entity.Tilbakemelding;
import dat109.prosjekt.entity.TilbakemeldingVerdi;

import java.util.List;

@Repository
public interface TilbakemeldingRepo extends JpaRepository<Tilbakemelding, Long> {

   
   
    /**
     * @param forelesningId
     * @param studentTokenHash
     * @return
     */
    boolean existsByForelesningIdAndStudentTokenHash(Long forelesningId, String studentTokenHash);

   
   
    /**
     * @param forelesningId
     * @return
     */
    List<Tilbakemelding> findByForelesningId(Long forelesningId);

   
   
    /**
     * @param forelesningId 
     * @param vurdering     
     * @return 
     */
    long countByForelesningIdAndVurdering(Long forelesningId, TilbakemeldingVerdi vurdering);
}
