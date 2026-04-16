package dat109.prosjekt.dto;

public class StatistikkDto {

    private long gronn;
    private long gul;
    private long rod;
    private long totalt;

    /**
     * @param gronn
     * @param gul
     * @param rod
     * @param totalt
     */
    public StatistikkDto(long gronn, long gul, long rod, long totalt) {
        this.gronn = gronn;
        this.gul = gul;
        this.rod = rod;
        this.totalt = totalt;
    }

    /** @return */
    public long getGronn() { 
        return gronn;
        }

    /** @return */
     public long getGul() { 
        return gul; 
        }

    /** @return */
    public long getRod() { 
        return rod; 
        }

    /** @return */
    public long getTotalt() { 
        return totalt; 
        }

    /** @param gronn */
    public void setGronn(long gronn) { 
        this.gronn = gronn; 
        }

    /** @param gul */
    public void setGul(long gul) { 
        this.gul = gul; 
        }

    /** @param rod */
    public void setRod(long rod) { 
        this.rod = rod; 
        }

    /** @param totalt */
    public void setTotalt(long totalt) { 
        this.totalt = totalt;
 }
}
