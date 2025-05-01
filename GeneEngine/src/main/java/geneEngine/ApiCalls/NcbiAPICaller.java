package geneEngine.ApiCalls;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

public class NcbiAPICaller extends AbstractAPICaller{

    public NcbiAPICaller(String base_url) {
        super(base_url);
    }

    public Integer getGeneIdFromSymbol(String symbol) throws IOException, URISyntaxException {
        HttpURLConnection conn = getConnection( "esearch.fcgi?db=gene&term=" + symbol + "[gene]+AND+Homo+sapiens[orgn]&retmode=json&api_key=f48faf88b7b516ba12be179267a8130fdd08");
        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        JSONObject jsonObject = new JSONObject(reader.readLine());
        return Integer.parseInt(jsonObject.getJSONObject("esearchresult").getJSONArray("idlist").get(0).toString());
    }

    public JSONObject getGeneInfoFromID(Integer id) throws Exception {
        HttpURLConnection conn = getConnection("esummary.fcgi?db=gene&id=" + id + "&retmode=json&api_key=f48faf88b7b516ba12be179267a8130fdd08");

        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        JSONObject jsonObject = new JSONObject(reader.readLine());
        JSONObject test = jsonObject.getJSONObject("result").getJSONObject(id.toString());
        test.put("diseases",getDiseasesByGeneId(id.toString()));
        return test;
    }

    public String getDiseasesByGeneId(String geneId) throws Exception {
        HttpURLConnection conn = getConnection("elink.fcgi?dbfrom=gene&id=" + geneId + "&db=omim&tool=GeneExplorer&retmode=json&api_key=f48faf88b7b516ba12be179267a8130fdd08");

        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        JSONObject response = new JSONObject(reader.readLine());
        JSONArray linkSets = response.getJSONArray("linksets");
        if (linkSets.isEmpty()) {return "";}
        JSONArray links = linkSets.getJSONObject(0).getJSONArray("linksetdbs").getJSONObject(0).getJSONArray("links");
        if (links.isEmpty()) {return "";}
        String ids = String.join(",", links.toList().stream().map(Object::toString).toArray(String[]::new));
        conn = getConnection("esummary.fcgi?db=omim&id=" + ids + "&tool=GeneExlporer&retmode=json&api_key=f48faf88b7b516ba12be179267a8130fdd08");
        is = conn.getInputStream();
        reader = new BufferedReader(new InputStreamReader(is));
        JSONObject result = new JSONObject(reader.readLine()).getJSONObject("result");
        StringBuilder names = new StringBuilder();
        for (String id : ids.split(",")) {
            String name = result.getJSONObject(id).getString("title");
            names.append(name);
        }
        return names.toString();
    }

    public JSONObject meshQueryId(String id) throws IOException, URISyntaxException {
        JSONObject result = new JSONObject();
        HttpURLConnection conn = getConnection("esummary.fcgi?db=mesh&id=" + id + "&retmode=json&api_key=f48faf88b7b516ba12be179267a8130fdd08");
        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        JSONObject response = new JSONObject(reader.readLine());
        result.put("children",response.getJSONObject("result").getJSONObject(id).getJSONArray("ds_idxlinks").getJSONObject(0).getJSONArray("children"));
        result.put("name",response.getJSONObject("result").getJSONObject(id).getJSONArray("ds_meshterms").getString(0));
        return result;
    }
}
