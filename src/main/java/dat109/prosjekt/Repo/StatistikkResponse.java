package dat109.prosjekt3.Repo;

public class StatistikkResponse {

    private long groenn;
    private long gul;
    private long roed;
    private long totalt;

    public StatistikkResponse(long groenn, long gul, long roed, long totalt) {
        this.groenn = groenn;
        this.gul = gul;
        this.roed = roed;
        this.totalt = totalt;
    }

    public long getGroenn() { 
        return groenn;
        }

     public long getGul() { 
        return gul; 
        }

    public long getRoed() { 
        return roed; 
        }

    public long getTotalt() { 
        return totalt; 
        }

    public void setGroenn(long groenn) { 
        this.groenn = groenn; 
        }

    public void setGul(long gul) { 
        this.gul = gul; 
        }

    public void setRoed(long roed) { 
        this.roed = roed; 
        }

    public void setTotalt(long totalt) { 
        this.totalt = totalt;
 }
}
