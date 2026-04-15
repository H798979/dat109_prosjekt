package dat109.prosjekt.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dat109.prosjekt.entity.Forelesning;

@Repository
public interface ForelesningRepo extends JpaRepository<Forelesning, Long> {
}
