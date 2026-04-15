package dat109.prosjekt.Repo;

public class StatistikkResponse {

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
    public StatistikkResponse(long gronn, long gul, long rod, long totalt) {
        this.gronn = gronn;
        this.gul = gul;
        this.rod = rod;
        this.totalt = totalt;
    }

    /** @return */
    public long getgronn() { 
        return gronn;
        }

    /** @return */
     public long getGul() { 
        return gul; 
        }

    /** @return */
    public long getrod() { 
        return rod; 
        }

    /** @return */
    public long getTotalt() { 
        return totalt; 
        }

    /** @param gronn */
    public void setgronn(long gronn) { 
        this.gronn = gronn; 
        }

    /** @param gul */
    public void setGul(long gul) { 
        this.gul = gul; 
        }

    /** @param rod */
    public void setrod(long rod) { 
        this.rod = rod; 
        }

    /** @param totalt */
    public void setTotalt(long totalt) { 
        this.totalt = totalt;
 }
}
