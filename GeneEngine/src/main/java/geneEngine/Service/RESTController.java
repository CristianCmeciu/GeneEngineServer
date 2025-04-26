package geneEngine.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genes")
public class RESTController {
    private final Service service;

    @Autowired
    public RESTController(Service service) {
        this.service = service;
    }

    @GetMapping("/{geneName}")
    public String getGene(@PathVariable String geneName) {
        return service.getGene(geneName);
    }
}
