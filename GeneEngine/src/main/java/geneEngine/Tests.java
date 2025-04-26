package geneEngine;

import geneEngine.ApiCalls.NcbiAPICaller;

public class Tests {
    public static void main(String[] args) {
        NcbiAPICaller api = new NcbiAPICaller("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/");
        try {
            api.getGeneInfoFromID(api.getGeneIdFromSymbol("TP53"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
