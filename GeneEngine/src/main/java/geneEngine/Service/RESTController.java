package geneEngine.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RESTController {
    private final Service service;

    @Autowired
    public RESTController(Service service) {
        this.service = service;
    }

    @GetMapping("/gene/{geneName}")
    public String getGene(@PathVariable String geneName) {
        return service.getGene(geneName);
    }

    @GetMapping("/disease/{diseaseId}")
    public String getDiseases(@PathVariable String diseaseId) {
        return service.getDiseases(diseaseId);
    }

    @GetMapping("/disease/categories")
    public String getCategories(){
        return service.getDiseases("1000067");
    }
}
