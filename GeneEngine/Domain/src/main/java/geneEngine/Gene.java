package geneEngine;

public class Gene extends Entity<String>{
    private final String summary;
    private final String organism;
    private final String maplocation;
    private final String chromosome;
    private final String otherdesignations;
    private final String description;
    private final String otheraliases;
    private final String nomenclaturename;
    private final String uid;
    private final String chrsort;
    private final String geneticsource;
    private final String nomenclaturesymbol;
    private final String status;
    private final Integer geneweight;
    private final Integer chrstart;
    private final String diseases;

    public Gene(String name,String summary, String organism, String maplocation, String chromosome, String otherdesignations, String description, String otheraliases, String nomenclaturename, String uid, String chrsort, String geneticsource, String nomenclaturesymbol, String status, Integer geneweight, Integer chrstart, String diseases) {
        setId(name);
        this.summary = summary;
        this.organism = organism;
        this.maplocation = maplocation;
        this.chromosome = chromosome;
        this.otherdesignations = otherdesignations;
        this.description = description;
        this.otheraliases = otheraliases;
        this.nomenclaturename = nomenclaturename;
        this.uid = uid;
        this.chrsort = chrsort;
        this.geneticsource = geneticsource;
        this.nomenclaturesymbol = nomenclaturesymbol;
        this.status = status;
        this.geneweight = geneweight;
        this.chrstart = chrstart;
        this.diseases = diseases;
    }

    public String getNume() {
        return getId();
    }

    public String getSummary() {
        return summary;
    }

    public String getOrganism() {
        return organism;
    }

    public String getMaplocation() {
        return maplocation;
    }

    public String getChromosome() {
        return chromosome;
    }

    public String getOtherdesignations() {
        return otherdesignations;
    }

    public String getDescription() {
        return description;
    }

    public String getOtheraliases() {
        return otheraliases;
    }

    public String getNomenclaturename() {
        return nomenclaturename;
    }

    public String getUid() {
        return uid;
    }

    public String getChrsort() {
        return chrsort;
    }

    public String getGeneticsource() {
        return geneticsource;
    }

    public String getNomenclaturesymbol() {
        return nomenclaturesymbol;
    }

    public String getStatus() {
        return status;
    }

    public Integer getGeneweight() {
        return geneweight;
    }

    public Integer getChrstart() {
        return chrstart;
    }

    public String getDiseases() {
        return diseases;
    }
}
