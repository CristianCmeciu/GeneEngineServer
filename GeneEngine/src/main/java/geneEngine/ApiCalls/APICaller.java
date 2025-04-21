package geneEngine.ApiCalls;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

public interface APICaller {
    HttpURLConnection getConnection(String link) throws IOException, URISyntaxException;
}
