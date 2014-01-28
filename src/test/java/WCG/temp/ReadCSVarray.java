package WCG.launchcomponent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;
 
public class ReadCSVarray {
	
	static BufferedReader br = null, brlinecount = null;
	static int total=0;
	int rowCount = 0;
	String line = "";
	HashMap filedatacount = new HashMap();
	
	ReadCSVarray(String filepath)
	{ 
		File f = new File(filepath);
		if(f.exists()){
	    try {

			br = new BufferedReader(new FileReader(filepath));
			while ((line = br.readLine()) != null) total++;
			br = new BufferedReader(new FileReader(filepath));
			
			brlinecount = br;
			String rc = null;
			while ((rc = brlinecount.readLine()) != null) {	
				filedatacount.put(rowCount, rc);
				rowCount++;
		}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		else System.out.println("CSV to be read does not exists!");	
	}
		
  public String readCSVarray(int rowNum, int colNum) {
	  
		int linenum = 0;
		HashMap filedata = new HashMap();
		BufferedReader brCelldata = null;
		String returncelldata = null;
		int csvrowNum = rowNum-1;
		int csvcolNum = colNum-1;
	
			String line;
			String cvsSplitBy = ",";
			brCelldata = br;
		
			filedata = filedatacount;
		
		if(filedata.get(csvrowNum).toString().split(",").length > csvcolNum)
		{
			returncelldata = filedata.get(csvrowNum).toString().split(",")[csvcolNum];
			
		}
		return returncelldata;
	  }

 
  public static String[][] CsvToArray(String filepath, int maxColCount){
	  ReadCSVarray r = new ReadCSVarray(filepath);
	  int maxRowCount = total;
	  String myData[][] = new String[maxRowCount][maxColCount];

	  int row;
	  int col;

	  
	  for (row=1;row<=maxRowCount;row++){
		  for (col=1;col<=maxColCount;col++){
			    myData[(row-1)][(col-1)]= r.readCSVarray(row, col);
			  //  System.out.println("row:col " + (row-1)+ (col-1));
				//  System.out.println("celldata "+ myData[row-1][col-1]);


		  }
	  }
	  

//	  for (int a=0;a<maxRowCount;a++){
//		  System.out.println("Row val: "+ a + " totrow: " + maxRowCount);
//		  for (int b=0;b<maxColCount;b++)
//		  {
//				  System.out.println("row:col " + a + b + " - " + 
//						  myData[a][b]);
//  
//
//		  }
//	  }
	  
	  
	  return myData;
	  
	  
  }
  

  
}