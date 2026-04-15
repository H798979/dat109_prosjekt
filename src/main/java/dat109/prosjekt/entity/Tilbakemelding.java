package dat109.prosjekt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tilbakemelding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TilbakemeldingVerdi vurdering;
    private LocalDateTime innsendt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forelesning_id", nullable = false)
    private Forelesning forelesning;

    @Column(nullable = false)
    private String studentTokenHash;

    public Tilbakemelding() {}
    public Tilbakemelding(TilbakemeldingVerdi vurdering, LocalDateTime innsendt,
                          Forelesning forelesning, String studentTokenHash) {
        this.vurdering = vurdering;
        this.innsendt = innsendt;
        this.forelesning = forelesning;
        this.studentTokenHash = studentTokenHash;
    }

    public Long getId() {
        return id;
        }

    public void setId(Long id) {
        this.id = id;
        }

    public TilbakemeldingVerdi getVurdering() {
        return vurdering;
        }

    public void setVurdering(TilbakemeldingVerdi vurdering) {
        this.vurdering = vurdering;
        }

    public LocalDateTime getInnsendt() {
        return innsendt;
        }

    public void setInnsendt(LocalDateTime innsendt) {
        this.innsendt = innsendt;
        }

    public Forelesning getForelesning() {
        return forelesning;
        }

    public void setForelesning(Forelesning forelesning) {
        this.forelesning = forelesning;
        }

    public String getStudentTokenHash() {
        return studentTokenHash;
        }

    public void setStudentTokenHash(String s) {
        this.studentTokenHash = s;
        }
}
