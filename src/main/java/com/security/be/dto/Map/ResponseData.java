package com.security.be.dto.Map;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseData {
	List<Document> documents = new ArrayList<> ();
}


