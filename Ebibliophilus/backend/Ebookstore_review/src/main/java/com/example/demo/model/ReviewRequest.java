package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
	private int userid;
	private int bookid;
	private String review;
	private double rating;
	private String booktitle;
	private String creator;
}
