package geneEngine.Service;

import geneEngine.ApiCalls.NcbiAPICaller;
import geneEngine.Disease;
import geneEngine.Repository.DiseaseRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class PopulateTables {
    DiseaseRepository diseaseRepo;
    NcbiAPICaller ncbiAPICaller;

    public PopulateTables(DiseaseRepository diseaseRepo) {
        this.diseaseRepo = diseaseRepo;
        this.ncbiAPICaller = new NcbiAPICaller("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/");
    }

    public void populateDiseases(String id){
        try {
            JSONObject object = ncbiAPICaller.meshQueryId(id);
            JSONArray categories = object.getJSONArray("children");
            if (categories != null) {
                for (var childrenid : categories) {
                    JSONObject category = ncbiAPICaller.meshQueryId((String) childrenid);
                    if (category.getString("name").equals("Animal Diseases"))
                        continue;
                    diseaseRepo.save(new Disease(category.getString("name"), (String) childrenid, id));
                    populateDiseases((String) childrenid);
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
