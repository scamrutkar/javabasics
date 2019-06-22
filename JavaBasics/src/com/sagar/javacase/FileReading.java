package com.sagar.javacase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileReading {
	
	public static void main(String[] args) {
		
		try {
			FileReader reader = new FileReader("Sagar_Amrutkar.pdf");
			
			BufferedReader read = new BufferedReader(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
