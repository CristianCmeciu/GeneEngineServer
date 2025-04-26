package geneEngine;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JSONUtils {
    public static Gene JSONtoGene(JSONObject obj){
        return new Gene(
                obj.getString("name"),
                obj.getString("summary"),
                obj.getJSONObject("organism").getString("commonname"),
                obj.getString("maplocation"),
                obj.getString("chromosome"),
                obj.getString("otherdesignations"),
                obj.getString("description"),
                obj.getString("otheraliases"),
                obj.getString("nomenclaturename"),
                obj.getString("uid"),
                obj.getString("chrsort"),
                obj.getString("geneticsource"),
                obj.getString("nomenclaturesymbol"),
                obj.getString("status"),
                obj.getInt("geneweight"),
                obj.getInt("chrstart"),
                obj.getString("diseases")
        );
    }

    public static Gene ResultSetToGene(ResultSet rs) throws SQLException {
        return new Gene(
                rs.getString("name"),
                rs.getString("summary"),
                rs.getString("organism"),
                rs.getString("maplocation"),
                rs.getString("chromosome"),
                rs.getString("otherdesignations"),
                rs.getString("description"),
                rs.getString("otheraliases"),
                rs.getString("nomenclaturename"),
                rs.getString("uid"),
                rs.getString("chrsort"),
                rs.getString("geneticsource"),
                rs.getString("nomenclaturesymbol"),
                rs.getString("status"),
                rs.getInt("geneweight"),
                rs.getInt("chrstart"),
                rs.getString("diseases")
        );
    }

    public static JSONObject GenetoJSON(Gene gene){
        JSONObject obj = new JSONObject();
        obj.put("name", gene.getNume());
        obj.put("chromosome", gene.getChromosome());
        obj.put("chrsort", gene.getChrsort());
        obj.put("chrstart", gene.getChrstart());
        obj.put("description", gene.getDescription());
        obj.put("geneticsource", gene.getGeneticsource());
        obj.put("geneweight", gene.getGeneweight());
        obj.put("maplocation", gene.getMaplocation());
        obj.put("nomenclaturename", gene.getNomenclaturename());
        obj.put("nomenclaturesymbol", gene.getNomenclaturesymbol());
        obj.put("organism", gene.getOrganism());
        obj.put("otheraliases", gene.getOtheraliases());
        obj.put("otherdesignations", gene.getOtherdesignations());
        obj.put("status", gene.getStatus());
        obj.put("summary", gene.getSummary());
        obj.put("uid", gene.getUid());
        obj.put("diseases", gene.getDiseases());
        return obj;
    }
}
