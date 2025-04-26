package geneEngine.Service;

import geneEngine.ApiCalls.NcbiAPICaller;
import geneEngine.Gene;
import geneEngine.JSONUtils;
import geneEngine.Repository.GeneRepository;
import org.json.JSONObject;

@org.springframework.stereotype.Service
public class Service {
    GeneRepository geneRepo;

    public Service(GeneRepository geneRepo) {
        this.geneRepo = geneRepo;
    }

    NcbiAPICaller ncbiAPICaller = new NcbiAPICaller("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/");
    public String getGene(String name) {
        JSONObject x;
        Gene gene;
        try {
            gene = geneRepo.findById(name);
            if (gene == null) {
                x = ncbiAPICaller.getGeneInfoFromID(ncbiAPICaller.getGeneIdFromSymbol(name));
                gene = JSONUtils.JSONtoGene(x);
                x = JSONUtils.GenetoJSON(gene);
                geneRepo.save(gene);
            }
            else {
                x = JSONUtils.GenetoJSON(gene);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return x.toString();
    }
}
