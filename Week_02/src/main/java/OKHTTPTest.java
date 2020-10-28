import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OKHTTPTest {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
