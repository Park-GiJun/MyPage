package com.security.be.controller;

import com.google.gson.Gson;
import com.security.be.dto.Map.Document;
import com.security.be.dto.Map.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MapController {

	private final String MAP_PAGE = "/map";

	@PostMapping("/getJson")
	public String getJson (@RequestBody String inp, Model model) throws Exception {
		String apiKey = "034588567022105f3259b71e004a84f5";
		String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=";
		String encodedInp = URLEncoder.encode (inp, StandardCharsets.UTF_8);

		URL url = new URL (apiUrl + encodedInp);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection ();
		conn.setRequestMethod ("GET");
		conn.setRequestProperty ("Authorization", "KakaoAK " + apiKey);

		int responseCode = conn.getResponseCode ();
		if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
			BufferedReader br = new BufferedReader (new InputStreamReader (conn.getInputStream ()));
			String inputLine;
			StringBuffer response = new StringBuffer ();

			while ((inputLine = br.readLine ()) != null) {
				response.append (inputLine);
			}
			br.close ();

			Gson gson = new Gson ();

			ResponseData responseData = gson.fromJson (response.toString (), ResponseData.class);
			List<Document> simplifiedDocuments = new ArrayList<> ();
			for (Document doc : responseData.getDocuments ()) {
				Document simplifiedDoc = new Document ();
				simplifiedDoc.setAddress_name (doc.getAddress_name ());
				simplifiedDoc.setX (doc.getX ());
				simplifiedDoc.setY (doc.getY ());

				simplifiedDocuments.add (simplifiedDoc);
			}

			System.out.println (simplifiedDocuments);

			String responseJson = gson.toJson (simplifiedDocuments);
			model.addAttribute ("data", responseJson);

			return MAP_PAGE;
		} else {
			System.out.println ("API 호출 실패 : " + responseCode);
			return MAP_PAGE;
		}

	}
}

