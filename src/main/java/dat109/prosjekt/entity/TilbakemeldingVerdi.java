package dat109.prosjekt.entity;

/**
 * Enum som representerer dei tre mogleg vurderingane ein student kan gi.
 * <ul>
 *   <li>{@link #GROENN} – Bra / forstår godt</li>
 *   <li>{@link #GUL} – Middels / noko usikker</li>
 *   <li>{@link #ROED} – Dårleg / forstår ikkje</li>
 * </ul>
 */
public enum TilbakemeldingVerdi {

    /** Grøn – studenten forstår stoffet godt. */
    GROENN,

    /** Gul – studenten er noko usikker. */
    GUL,

    /** Raud – studenten forstår ikkje stoffet. */
    ROED
}
