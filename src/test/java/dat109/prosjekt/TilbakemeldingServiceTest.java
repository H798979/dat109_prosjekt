package dat109.prosjekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dat109.prosjekt.entity.Forelesning;
import dat109.prosjekt.entity.Tilbakemelding;
import dat109.prosjekt.entity.TilbakemeldingVerdi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TilbakemeldingServiceTest {

    List<Tilbakemelding> database;
    Forelesning forelesning;

    @BeforeEach
    void setUp() {
        database = new ArrayList<>();
        forelesning = new Forelesning("DAT109 – Java", LocalDateTime.of(2026, 4, 14, 10, 15), "E401");
        forelesning.setId(1L);
    }

    String hashToken(String token) {
        return String.valueOf(token.hashCode());
    }

    boolean eksisterer(Long forelesningId, String hash) {
        return database.stream().anyMatch(t ->
                t.getForelesning().getId().equals(forelesningId) &&
                t.getStudentTokenHash().equals(hash));
    }

    long tell(Long forelesningId, TilbakemeldingVerdi vurdering) {
        return database.stream().filter(t ->
                t.getForelesning().getId().equals(forelesningId) &&
                t.getVurdering() == vurdering).count();
    }

    @Test
    @DisplayName("Student kan ikkje gi meir enn éi tilbakemelding per forelesning")
    void dobbeltTilbakemelding() {
        String token = "test-student-uuid-123";
        String hash = hashToken(token);

        assertFalse(eksisterer(1L, hash));
        database.add(new Tilbakemelding(TilbakemeldingVerdi.GRONN, LocalDateTime.now(), forelesning, hash));

        assertTrue(eksisterer(1L, hash));
    }

    @Test
    @DisplayName("Tilbakemelding vert lagra med riktig vurdering")
    void riktigVurdering() {
        String token = "student-abc";
        String hash = hashToken(token);

        Tilbakemelding t = new Tilbakemelding(TilbakemeldingVerdi.ROD, LocalDateTime.now(), forelesning, hash);
        database.add(t);

        assertEquals(TilbakemeldingVerdi.ROD, t.getVurdering());
        assertEquals(forelesning, t.getForelesning());
        assertEquals(hash, t.getStudentTokenHash());
        assertNotNull(t.getInnsendt());
    }

    @Test
    @DisplayName("Statistikk returnerer korrekte teljarar")
    void statistikk() {
        for (int i = 0; i < 5; i++)
            database.add(new Tilbakemelding(TilbakemeldingVerdi.GRONN, LocalDateTime.now(), forelesning, "h" + i));
        for (int i = 0; i < 3; i++)
            database.add(new Tilbakemelding(TilbakemeldingVerdi.GUL, LocalDateTime.now(), forelesning, "h" + (5 + i)));
        database.add(new Tilbakemelding(TilbakemeldingVerdi.ROD, LocalDateTime.now(), forelesning, "h9"));

        assertEquals(5, tell(1L, TilbakemeldingVerdi.GRONN));
        assertEquals(3, tell(1L, TilbakemeldingVerdi.GUL));
        assertEquals(1, tell(1L, TilbakemeldingVerdi.ROD));
        assertEquals(9, database.size());
    }

    @Test
    @DisplayName("hashToken produserer deterministisk hash")
    void hashTest() {
        String hash1 = hashToken("min-test-token");
        String hash2 = hashToken("min-test-token");
        assertEquals(hash1, hash2);
    }
}
