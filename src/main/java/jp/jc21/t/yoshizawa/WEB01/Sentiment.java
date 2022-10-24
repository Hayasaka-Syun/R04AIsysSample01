package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		sentiment2 message = getSentiment("Stepover Toehold With Facelock");
		if (message != null) {
			System.out.println("negative = " + message.documents[0].confidenceScores.negative);
			System.out.println("neutral = " + message.documents[0].confidenceScores.neutral);
			System.out.println("positive = " + message.documents[0].confidenceScores.positive);
		}
	}

	static sentiment2 getSentiment(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3a18-text.cognitiveservices.azure.com//" + "text/analytics/v3.0/sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "c4ec0e7691784ea990ec5052dd07d071");

		Docs2 doc = new Docs2();
		doc.id = "1";
		doc.text = s;

		Source2 src = new Source2();
		src.documents = new Docs2[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		sentiment2 message = null;
		if (reader != null) {
			message = gson.fromJson(reader, sentiment2.class);
			reader.close();
		}
		return message;
	}

}

class sentiment2 {
	Documents2[] documents;
	String[] errors;
	String modelVersion;
}

class Documents2 {
	ConfidenceScores confidenceScores;
	String id;
}

class ConfidenceScores {
	float negative;
	float neutral;
	float positive;
}

class Source2 {
	Docs2[] documents;
}

class Docs2 {
	String id;
	String text;
}
