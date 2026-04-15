package dat109.prosjekt.Repo;

/**
 * Dataklasse som held statistikk for tilbakemeldingar på ei forelesning.
 * Inneheld antal grøne, gule og raude vurderingar, samt totalt antal.
 */
public class StatistikkResponse {

    private long groenn;
    private long gul;
    private long roed;
    private long totalt;

    /**
     * Opprettar eit nytt statistikk-objekt.
     *
     * @param groenn antal grøne tilbakemeldingar
     * @param gul    antal gule tilbakemeldingar
     * @param roed   antal raude tilbakemeldingar
     * @param totalt totalt antal tilbakemeldingar
     */
    public StatistikkResponse(long groenn, long gul, long roed, long totalt) {
        this.groenn = groenn;
        this.gul = gul;
        this.roed = roed;
        this.totalt = totalt;
    }

    /** @return antal grøne tilbakemeldingar */
    public long getGroenn() { 
        return groenn;
        }

    /** @return antal gule tilbakemeldingar */
     public long getGul() { 
        return gul; 
        }

    /** @return antal raude tilbakemeldingar */
    public long getRoed() { 
        return roed; 
        }

    /** @return totalt antal tilbakemeldingar */
    public long getTotalt() { 
        return totalt; 
        }

    /** @param groenn antal grøne tilbakemeldingar */
    public void setGroenn(long groenn) { 
        this.groenn = groenn; 
        }

    /** @param gul antal gule tilbakemeldingar */
    public void setGul(long gul) { 
        this.gul = gul; 
        }

    /** @param roed antal raude tilbakemeldingar */
    public void setRoed(long roed) { 
        this.roed = roed; 
        }

    /** @param totalt totalt antal tilbakemeldingar */
    public void setTotalt(long totalt) { 
        this.totalt = totalt;
 }
}
