package geneEngine;

import geneEngine.Repository.DiseaseDBRepository;
import geneEngine.Service.PopulateTables;

import java.io.FileReader;
import java.util.Properties;

public class Tests {
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.load(new FileReader("bd.config"));
            PopulateTables populator = new PopulateTables(new DiseaseDBRepository(props));
            populator.populateDiseases("1000067");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
