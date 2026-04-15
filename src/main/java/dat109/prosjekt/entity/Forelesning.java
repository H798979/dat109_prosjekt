package dat109.prosjekt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entitet som representerer ei forelesning.
 * Kvar forelesning har eit namn, tidspunkt, stad, og ei liste med tilbakemeldingar.
 */
@Entity
public class Forelesning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String navn;
    private LocalDateTime tidspunkt;
    private String sted;

    @OneToMany(mappedBy = "forelesning", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tilbakemelding> tilbakemeldinger = new ArrayList<>();

    /** Standardkonstruktør kravd av JPA. */
    public Forelesning() {}

    /**
     * Opprettar ei ny forelesning.
     *
     * @param navn       namnet på forelesinga
     * @param tidspunkt  dato og klokkeslett for forelesinga
     * @param sted       staden der forelesinga blir halden
     */
    public Forelesning(String navn, LocalDateTime tidspunkt, String sted) {
        this.navn = navn;
        this.tidspunkt = tidspunkt;
        this.sted = sted;
    }

    /** @return unik ID for forelesinga */
    public Long getId() {
        return id;
        }

    /** @param id unik ID */
    public void setId(Long id) {
        this.id = id;
        }

    /** @return namnet på forelesinga */
    public String getNavn() {
        return navn;
        }

    /** @param navn namnet på forelesinga */
    public void setNavn(String navn) {
        this.navn = navn;
        }

    /** @return tidspunktet for forelesinga */
    public LocalDateTime getTidspunkt() {
        return tidspunkt;
        }

    /** @param tidspunkt dato og klokkeslett for forelesinga */
    public void setTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        }

    /** @return staden for forelesinga */
    public String getSted() {
        return sted;
        }

    /** @param sted staden for forelesinga */
    public void setSted(String sted) {
        this.sted = sted;
        }

    /** @return lista over tilbakemeldingar knytt til forelesinga */
    public List<Tilbakemelding> getTilbakemeldinger() {
        return tilbakemeldinger;
        }

    /** @param t lista over tilbakemeldingar */
    public void setTilbakemeldinger(List<Tilbakemelding> t) {
        this.tilbakemeldinger = t;
        }
}
