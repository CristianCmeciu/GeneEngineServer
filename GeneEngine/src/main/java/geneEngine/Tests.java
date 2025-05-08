package geneEngine;

import geneEngine.Repository.DiseaseDBRepository;
import geneEngine.Repository.GeneDBRepository;
import geneEngine.Service.PopulateTables;
import geneEngine.Service.Service;

import java.io.FileReader;
import java.util.Properties;

public class Tests {
    public static void main(String[] args) {
        try {
            /*
            Properties props = new Properties();
            props.load(new FileReader("bd.config"));
            PopulateTables populator = new PopulateTables(new DiseaseDBRepository());
            populator.populateDiseases("68005128");

             */
            Service service = new Service(new GeneDBRepository("jdbc:sqlite:GeneEngineDB"),new DiseaseDBRepository("jdbc:sqlite:GeneEngineDB"));
            System.out.println(service.getDiseases("1000067"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
