package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewReq {
	private int userid;
	private int bookid;
	private String review;
	private double rating;
	private String creator;
	private String booktitle;
}
