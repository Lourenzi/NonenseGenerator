import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class SimplifySyntaxJson {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -cp json.jar:SimplifySyntaxJson.jar SimplifySyntaxJson input.json output.json");
            System.exit(1);
        }

        try {
            String input = readFile(args[0]);
            JSONObject json = new JSONObject(input);
            JSONArray tokens = json.getJSONArray("tokens");
            JSONArray simplified = new JSONArray();

            for (int i = 0; i < tokens.length(); i++) {
                JSONObject token = tokens.getJSONObject(i);
                JSONObject simpleToken = new JSONObject();

                JSONObject text = new JSONObject();
                text.put("content", token.getJSONObject("text").getString("content"));

                JSONObject pos = new JSONObject();
                pos.put("tag", token.getJSONObject("partOfSpeech").getString("tag"));

                JSONObject dep = new JSONObject();
                dep.put("headTokenIndex", token.getJSONObject("dependencyEdge").getInt("headTokenIndex"));

                simpleToken.put("text", text);
                simpleToken.put("partOfSpeech", pos);
                simpleToken.put("dependencyEdge", dep);

                simplified.put(simpleToken);
            }

            JSONObject outJson = new JSONObject();
            outJson.put("tokens", simplified);

            try (PrintWriter out = new PrintWriter(args[1])) {
                out.println(outJson.toString(2));
            }

            System.out.println("✅ Simplified output written to " + args[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
            sb.append(line);
        reader.close();
        return sb.toString();
    }
}
