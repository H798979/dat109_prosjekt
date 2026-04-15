package dat109.prosjekt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entitet som representerer ei tilbakemelding frå ein student på ei forelesning.
 * Kvar tilbakemelding inneheld ei vurdering (grøn/gul/raud), tidspunkt for innsending,
 * referanse til forelesinga, og ein hasha student-token for å hindre duplikat.
 */
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

    /** Standardkonstruktør kravd av JPA. */
    public Tilbakemelding() {}

    /**
     * Opprettar ei ny tilbakemelding.
     *
     * @param vurdering        vurderinga studenten gir (GROENN, GUL eller ROED)
     * @param innsendt         tidspunktet tilbakemeldinga blei sendt inn
     * @param forelesning      forelesinga tilbakemeldinga gjeld
     * @param studentTokenHash SHA-256-hash av studentens unike token
     */
    public Tilbakemelding(TilbakemeldingVerdi vurdering, LocalDateTime innsendt,
                          Forelesning forelesning, String studentTokenHash) {
        this.vurdering = vurdering;
        this.innsendt = innsendt;
        this.forelesning = forelesning;
        this.studentTokenHash = studentTokenHash;
    }

    /** @return unik ID for tilbakemeldinga */
    public Long getId() {
        return id;
        }

    /** @param id unik ID */
    public void setId(Long id) {
        this.id = id;
        }

    /** @return vurderinga studenten gav */
    public TilbakemeldingVerdi getVurdering() {
        return vurdering;
        }

    /** @param vurdering vurderinga som skal settast */
    public void setVurdering(TilbakemeldingVerdi vurdering) {
        this.vurdering = vurdering;
        }

    /** @return tidspunktet tilbakemeldinga blei sendt inn */
    public LocalDateTime getInnsendt() {
        return innsendt;
        }

    /** @param innsendt tidspunkt for innsending */
    public void setInnsendt(LocalDateTime innsendt) {
        this.innsendt = innsendt;
        }

    /** @return forelesinga tilbakemeldinga er knytt til */
    public Forelesning getForelesning() {
        return forelesning;
        }

    /** @param forelesning forelesinga tilbakemeldinga gjeld */
    public void setForelesning(Forelesning forelesning) {
        this.forelesning = forelesning;
        }

    /** @return SHA-256-hash av studentens token */
    public String getStudentTokenHash() {
        return studentTokenHash;
        }

    /** @param s SHA-256-hash av studentens token */
    public void setStudentTokenHash(String s) {
        this.studentTokenHash = s;
        }
}
