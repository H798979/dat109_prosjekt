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

    /**
     * @param vurdering
     * @param innsendt
     * @param forelesning
     * @param studentTokenHash
     */
    public Tilbakemelding(TilbakemeldingVerdi vurdering, LocalDateTime innsendt,
                          Forelesning forelesning, String studentTokenHash) {
        this.vurdering = vurdering;
        this.innsendt = innsendt;
        this.forelesning = forelesning;
        this.studentTokenHash = studentTokenHash;
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
    public TilbakemeldingVerdi getVurdering() {
        return vurdering;
        }

    /** @param vurdering */
    public void setVurdering(TilbakemeldingVerdi vurdering) {
        this.vurdering = vurdering;
        }

    /** @return */
    public LocalDateTime getInnsendt() {
        return innsendt;
        }

    /** @param innsendt */
    public void setInnsendt(LocalDateTime innsendt) {
        this.innsendt = innsendt;
        }

    /** @return */
    public Forelesning getForelesning() {
        return forelesning;
        }

    /** @param forelesning */
    public void setForelesning(Forelesning forelesning) {
        this.forelesning = forelesning;
        }

    /** @return */
    public String getStudentTokenHash() {
        return studentTokenHash;
        }

    /** @param s */
    public void setStudentTokenHash(String s) {
        this.studentTokenHash = s;
        }
}
