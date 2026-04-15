package dat109.prosjekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dat109.prosjekt.Controller.TilbakemeldingService;
import dat109.prosjekt.Repo.StatistikkResponse;
import dat109.prosjekt.Repo.TilbakemeldingRepo;
import dat109.prosjekt.Repo.TilbakemeldingVerdi;
import dat109.prosjekt.entity.Forelesning;
import dat109.prosjekt.entity.Tilbakemelding;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TilbakemeldingServiceTest {

    @Mock TilbakemeldingRepo tilbakemeldingRepo;
    @InjectMocks TilbakemeldingService tjeneste;

    Forelesning forelesning;

    @BeforeEach
    void setUp() {
        forelesning = new Forelesning("DAT109 – Java", LocalDateTime.of(2026, 4, 14, 10, 15), "E401");
        forelesning.setId(1L);
    }

    @Test
    @DisplayName("Student kan ikkje gi meir enn éi tilbakemelding per forelesning")
    void dobbeltTilbakemelding() {
        String token = "test-student-uuid-123";
        String hash = tjeneste.hashToken(token);

        when(tilbakemeldingRepo.existsByForelesningIdAndStudentTokenHash(1L, hash)).thenReturn(false);
        when(tilbakemeldingRepo.save(any(Tilbakemelding.class))).thenAnswer(inv -> inv.getArgument(0));
        tjeneste.registrer(forelesning, TilbakemeldingVerdi.GROENN, token);

        when(tilbakemeldingRepo.existsByForelesningIdAndStudentTokenHash(1L, hash)).thenReturn(true);
        assertThrows(IllegalStateException.class,
                () -> tjeneste.registrer(forelesning, TilbakemeldingVerdi.GUL, token));
    }

    @Test
    @DisplayName("Tilbakemelding vert lagra med riktig vurdering")
    void riktigVurdering() {
        String token = "student-abc";
        String hash = tjeneste.hashToken(token);

        when(tilbakemeldingRepo.existsByForelesningIdAndStudentTokenHash(1L, hash)).thenReturn(false);
        when(tilbakemeldingRepo.save(any(Tilbakemelding.class))).thenAnswer(inv -> inv.getArgument(0));
        Tilbakemelding t = tjeneste.registrer(forelesning, TilbakemeldingVerdi.ROED, token);

        assertEquals(TilbakemeldingVerdi.ROED, t.getVurdering());
        assertEquals(forelesning, t.getForelesning());
        assertEquals(hash, t.getStudentTokenHash());
        assertNotNull(t.getInnsendt());
    }

    @Test
    @DisplayName("Statistikk returnerer korrekte teljarar")
    void statistikk() {
        when(tilbakemeldingRepo.countByForelesningIdAndVurdering(1L, TilbakemeldingVerdi.GROENN)).thenReturn(5L);
        when(tilbakemeldingRepo.countByForelesningIdAndVurdering(1L, TilbakemeldingVerdi.GUL)).thenReturn(3L);
        when(tilbakemeldingRepo.countByForelesningIdAndVurdering(1L, TilbakemeldingVerdi.ROED)).thenReturn(1L);
        StatistikkResponse s = tjeneste.hentStatistikk(1L);

        assertEquals(5, s.getGroenn());
        assertEquals(3, s.getGul());
        assertEquals(1, s.getRoed());
        assertEquals(9, s.getTotalt());
    }

    @Test
    @DisplayName("hashToken produserer deterministisk SHA-256")
    void hashTest() {
        String hash1 = tjeneste.hashToken("min-test-token");
        String hash2 = tjeneste.hashToken("min-test-token");
        assertEquals(hash1, hash2);
        assertEquals(64, hash1.length());
    }
}
