package geneEngine.Repository;

import geneEngine.Disease;

public interface DiseaseRepository extends Repository<String,Disease>{
    public Iterable<Disease> findAllById(String id);
}
