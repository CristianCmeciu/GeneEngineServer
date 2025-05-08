package geneEngine.Service;

import geneEngine.ApiCalls.NcbiAPICaller;
import geneEngine.Disease;
import geneEngine.Gene;
import geneEngine.JSONUtils;
import geneEngine.Repository.DiseaseRepository;
import geneEngine.Repository.GeneRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class Service {
    GeneRepository geneRepo;
    DiseaseRepository diseaseRepo;

    public Service(GeneRepository geneRepo,DiseaseRepository diseaseRepo) {
        this.geneRepo = geneRepo;
        this.diseaseRepo = diseaseRepo;
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

    public String getDiseases(String diseasesId) {
        JSONArray array = new JSONArray();
        ArrayList<Disease> diseases = (ArrayList<Disease>) diseaseRepo.findAllById(diseasesId);
        for(var x: diseases){
            JSONObject disease = new JSONObject();
            disease.put("id", x.getId());
            disease.put("name",x.getName());
            array.put(disease);
        }
        return array.toString();
    }
}
