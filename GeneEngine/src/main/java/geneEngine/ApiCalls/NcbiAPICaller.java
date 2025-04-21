package geneEngine.ApiCalls;

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
        /*
        Set<String> keysToKeep = Set.of("name", "summary","description");

        Set<String> keysToRemove = new HashSet<>();

        for (String key : test.keySet()) {
            if (!keysToKeep.contains(key)) {
                keysToRemove.add(key);
            }
        }

        for (String key : keysToRemove) {
            test.remove(key);
        }
        test.put("summary",test.getString("summary").split("\\.")[0] + '.');
        int n = 10;
        var lista = getDiseasesByGeneId(id.toString());
        JSONArray array = new JSONArray();
        for (var x: lista){
            array.put(x.split("[,;]")[0].replaceAll(" SUSCEPTIBILITY [0-9]",""));
            n--;
            if (n==0)
                break;
        }
        test.put("diseases",array);
         */
        return test;
    }
}
