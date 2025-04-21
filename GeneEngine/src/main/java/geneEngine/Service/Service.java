package geneEngine.Service;

import geneEngine.ApiCalls.APICaller;
import geneEngine.ApiCalls.NcbiAPICaller;
import org.json.JSONObject;

public class Service {
    NcbiAPICaller ncbiAPICaller = new NcbiAPICaller("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/");

    public String getGene(String name) {
        JSONObject x;
        try {
            x = ncbiAPICaller.getGeneInfoFromID(ncbiAPICaller.getGeneIdFromSymbol(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (x==null){
            x = new JSONObject();
            x.put("Error", "No matching Gene Name");
        }
        return x.toString();
    }
}
