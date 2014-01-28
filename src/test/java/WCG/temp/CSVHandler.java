package WCG.temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.compro.automation.core.SetupDriver;
 
public class CSVHandler {
	
	int total=0;
	String filepath;

	public CSVHandler(String filepath)
	{ 
		this.filepath = filepath;
		File f = new File(filepath);
		try{
			if(!f.exists()){
				throw new IOException();}
			}
			catch(IOException e) {e.printStackTrace();}
	 }
	
	public CSVHandler(String filepath,String write)
	{ 
		this.filepath = filepath;
		int last=filepath.lastIndexOf("/");
		File dirpath = new File (filepath.substring(0, last));
		File f = new File(filepath);
		try{
			if(!dirpath.exists()){
				dirpath.mkdir();
			}
			if(!f.exists()){
				f.createNewFile();}
			}
			catch(IOException e) {e.printStackTrace();}
	 }	
	
//	==================
	
  public String readCSV(int rowNum, int colNum) throws FileNotFoundException, IOException{
	  
	  BufferedReader readCSVintobr = new BufferedReader(new FileReader(filepath));	   
		
		int linenum = 1;
		HashMap<Integer,String> filedata = new HashMap<Integer,String>();
		BufferedReader brCelldata = null;
		String returncelldata = null;
		int csvrowNum = rowNum-1;
		int csvcolNum = colNum-1;
		try {
			String line;
			brCelldata = readCSVintobr;
			line = brCelldata.readLine();
			while ((line = brCelldata.readLine()) != null) {	
				filedata.put(linenum, line);
				linenum++;
		}
	} 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		finally {
			if (brCelldata != null) {
				try { readCSVintobr.close(); } 
				catch (IOException e) { e.printStackTrace(); }
			}
		}
		if(filedata.get(csvrowNum).toString().split(",").length > csvcolNum)
			returncelldata = filedata.get(csvrowNum).toString().split(",")[csvcolNum];
		return returncelldata;
	  }

//  =============================
  
  public String readCSV(int rowNum) throws FileNotFoundException {
	  
	  BufferedReader readCSVintobr = new BufferedReader(new FileReader(filepath));	   
	  
		int linenum = 1;
		HashMap<Integer,String> filedata = new HashMap<Integer,String>();
		BufferedReader brCelldata = null;
		int csvrowNum = rowNum-1;
		try {
			String line;
			brCelldata = readCSVintobr;
			line = brCelldata.readLine();
			while ((line = brCelldata.readLine()) != null) {	
				filedata.put(linenum, line);
				linenum++;
		}
	} 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		finally {
			if (brCelldata != null) {
				try { readCSVintobr.close(); } 
				catch (IOException e) { e.printStackTrace(); }
			}
		}
	return filedata.get(csvrowNum).toString();
	  }
  
//============================= add 
  
  public String[][] CsvToArray(int maxColCount){
	  
	  BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(filepath));
		String line = "";
		while ((line = br.readLine()) != null) total++;
		br.close();
	} catch (Exception e1) {e1.printStackTrace();}
	
	   	  int maxRowCount = total;
	   	  String myData[][] = new String[maxRowCount][maxColCount];

	   	  int row, col;

	   	  for (row=2;row<=maxRowCount;row++){
	   		  for (col=1;col<=maxColCount;col++){
	   			    try {
	   			  		myData[(row-2)][(col-1)]= readCSV(row, col);
	   			  		//if (myData[(row-2)][(col-1)]== null){myData[(row-2)][(col-1)] = "";}
	   					System.out.println(myData[(row-2)][(col-1)]);
	   				} catch (FileNotFoundException e) {	e.printStackTrace();} 
	   			      catch (IOException e) {e.printStackTrace(); }
	   			    
	   			}
	   	  }
	   	  return myData;
	   }

  
  // ================
  
   public String[] readCSV_col(int colNum) throws FileNotFoundException, IOException{
	   
  
	  BufferedReader readCSVintobr = new BufferedReader(new FileReader(filepath));	  
	  String localLine = "";
	  while ((localLine = readCSVintobr.readLine()) != null) total++;
	  String cvsSplitBy = ",";
	  readCSVintobr = new BufferedReader(new FileReader(filepath));
	  localLine = null;
	  String[] rowData = null;
      String coldata[]=new String[total];
	  int linenum = 0;
	  String s[] = new String[total];
	  int csvcolNum=colNum-1;
      int k=0;
	    
		try {
            
            while ((localLine = readCSVintobr.readLine()) != null) {
            	
				s[linenum]=localLine;
				rowData = s[linenum].split(cvsSplitBy);
				if(rowData.length>=csvcolNum+1)
				{
				coldata[k]=rowData[csvcolNum];
				linenum++;
				k++;
				}
				else
					break;
			}
            
	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (readCSVintobr != null) {
				try {
					readCSVintobr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return coldata;
	  }
   
   public String getElementXpath(String element) throws FileNotFoundException {
		  
		String sr = SetupDriver.resolutionCategory;	
	   BufferedReader br = new BufferedReader(new FileReader(filepath));
			int linenum = 1;
			HashMap<Integer,String> filedata = new HashMap<Integer,String>();
			int i=0;
			String xp = "";
			try {
				String line;
				String cvsSplitBy = ",";
				line = br.readLine();
				String[] colData=line.split(cvsSplitBy);


				while(i<colData.length)
				{  
					if (colData[i].equalsIgnoreCase(sr))
						{ 
						while ((line = br.readLine()) != null) 
						{	
							filedata.put(linenum, line);
							String[] pathData=line.split(cvsSplitBy);
							if(pathData[0].equalsIgnoreCase(element) && !(filedata.get(linenum).toString().split(",")[i]).isEmpty())
								{  xp =  (filedata.get(linenum).toString().split(",")[i]); 
									break;
								}
							linenum++;
						} 	
				}
					i++;
				}
			}
			
			catch (IOException e) { e.printStackTrace(); } 
			
			finally {
				if (br != null) {
					try { br.close(); } 
					catch (IOException e) { e.printStackTrace(); }
				}
			}
			
			if(!xp.equals(""))
			return xp;
			else 
			{
				new WebElementPathNotFoundException("Xpath for " + element.toString() + " and " + sr + " not found");
				return null;
			}

		  }
   public void write(String csvdata) {
	  	try
	  	{
	  		if(filepath.isEmpty())
	  			throw new Exception();

	  		FileWriter writer = new FileWriter(filepath,true);
	  		
	  		/**to add line */
	  		PrintWriter out = new PrintWriter(new BufferedWriter(writer));
	  	    out.println(csvdata);
	  	    out.close();
	  	    writer.close();
	  	}
	  	catch(IOException e)
	  		{ e.printStackTrace(); }
	  	catch(Exception e)
			{ e.printStackTrace(); }
	 }	

	  
	  public class WebElementPathNotFoundException extends Exception {
		private static final long serialVersionUID = -1162708872371191332L;

			public WebElementPathNotFoundException(String message) {
		    	super.printStackTrace();
		    }
		}
}