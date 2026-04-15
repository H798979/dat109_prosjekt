package dat109.prosjekt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Forelesning() {}

    /**
     * @param navn
     * @param tidspunkt
     * @param sted
     */
    public Forelesning(String navn, LocalDateTime tidspunkt, String sted) {
        this.navn = navn;
        this.tidspunkt = tidspunkt;
        this.sted = sted;
    }

    /** @return */
    public Long getId() {
        return id;
        }

    /** @param id */
    public void setId(Long id) {
        this.id = id;
        }

    /** @return */
    public String getNavn() {
        return navn;
        }

    /** @param navn */
    public void setNavn(String navn) {
        this.navn = navn;
        }

    /** @return */
    public LocalDateTime getTidspunkt() {
        return tidspunkt;
        }

    /** @param tidspunkt */
    public void setTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        }

    /** @return */
    public String getSted() {
        return sted;
        }

    /** @param sted */
    public void setSted(String sted) {
        this.sted = sted;
        }

    /** @return */
    public List<Tilbakemelding> getTilbakemeldinger() {
        return tilbakemeldinger;
        }

    /** @param t */
    public void setTilbakemeldinger(List<Tilbakemelding> t) {
        this.tilbakemeldinger = t;
        }
}
