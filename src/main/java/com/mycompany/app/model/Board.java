package com.mycompany.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board {
	
	private int  num;
	private String title;
	private String contents;
	
	

}
