package dat109.prosjekt3.entity;

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
    public Forelesning(String navn, LocalDateTime tidspunkt, String sted) {
        this.navn = navn;
        this.tidspunkt = tidspunkt;
        this.sted = sted;
    }

    public Long getId() {
        return id;
        }

    public void setId(Long id) {
        this.id = id;
        }

    public String getNavn() {
        return navn;
        }

    public void setNavn(String navn) {
        this.navn = navn;
        }

    public LocalDateTime getTidspunkt() {
        return tidspunkt;
        }

    public void setTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
        }

    public String getSted() {
        return sted;
        }

    public void setSted(String sted) {
        this.sted = sted;
        }

    public List<Tilbakemelding> getTilbakemeldinger() {
        return tilbakemeldinger;
        }

    public void setTilbakemeldinger(List<Tilbakemelding> t) {
        this.tilbakemeldinger = t;
        }
}
