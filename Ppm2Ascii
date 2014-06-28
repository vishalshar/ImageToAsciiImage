
import java.io.*;


public class Ppm2Ascii extends Image
{

	/**
	 * Program to read a PPM file and convert it into a ASCII Image 
	 */
	
	// array to store the gray scale value using average algorithm
	private int ppmAvg [];
	private int width; // width of the image
	private int length; // length of the image
	private int lengthInput;// total number of pixel
	private int maximumIntensity; // maximum intensity of the image
	private Image image; 
	
	
	
	// loading PPM values to the image
	void loadPPM(String strLine,int index)
	{
	    String split[] = strLine.split(" ");
	    image.ppmValue[0][index]= Integer.parseInt(split[0]);
	    image.ppmValue[1][index]= Integer.parseInt(split[1]);
	    image.ppmValue[2][index]= Integer.parseInt(split[2]);
	}
	
	
	
	// loading ppmAvg with gray scale values by taking average of the RGB value of each pixel
	void ppmToGray()
	{
		for(int i=0;i<lengthInput;i++)
		{
		     this.ppmAvg[i]=((this.image.ppmValue[0][i]+this.image.ppmValue[1][i]+this.image.ppmValue[2][i])/3);
		}
	}
	
	
	// mapping the gray scale to a character according to its intensity
	void grayToAscii()
	{
		
		for(int i=0;i<this.lengthInput;i++)
		{
			if (this.ppmAvg[i] <= 50)
				image.output[i]= '#';
			else if(this.ppmAvg[i] > 50 && this.ppmAvg[i] <= 75)
				image.output[i]='&';
			else if(this.ppmAvg[i] > 75 && this.ppmAvg[i] <= 100)
				image.output[i]='$';
			else if (this.ppmAvg[i] > 100 && this.ppmAvg[i] <= 125)
				image.output[i]='+';
			else if (this.ppmAvg[i] > 125 && this.ppmAvg[i] <= 150)
				image.output[i]='/';
			else if (this.ppmAvg[i] > 150 && this.ppmAvg[i] <= 175)
				image.output[i]=':';
			else if (this.ppmAvg[i] > 175 && this.ppmAvg[i] <= 200)
				image.output[i]='~';
			else if (this.ppmAvg[i] > 200 && this.ppmAvg[i] <= 225)
				image.output[i]='`';
			else if (this.ppmAvg[i] > 225)
				image.output[i]=' ';
		}
	}
	
	
	
	// mapping gray scale intensity to a character on the basis of threshold value
	void grayToAsciiThreshold(int threshold)
	{
		for(int i=0;i<this.lengthInput;i++)
		{
			if (this.ppmAvg[i] <= threshold)
			{
				image.output[i] = '*';
			}
			else
			{
				image.output[i]=' ';
			}
		}
	}

	
	
	// MAIN
    public static void main(String[] args) throws IOException 
    {
    
    	 AsciiImage asciiImage = new AsciiImage();
   	     Ppm2Ascii ppm2Ascii = new Ppm2Ascii();
   	  
   	     // Open the file that is the first string
		 // command line parameter
		 FileInputStream filestream = new FileInputStream(args[0]);
		 // Get the object of DataInputStream
		 DataInputStream in = new DataInputStream(filestream);
		 BufferedReader br = new BufferedReader(new InputStreamReader(in));
		 
		 
    try
    {
		  
		  String strLine1;
		  String strLine2;
		  String strLine3;
		  
		  // read the first line
		  strLine1 = br.readLine();
		  // read the second line
		  strLine2 = br.readLine();
		  int index=0;
		  String check[] = strLine2.split(" ");
		  
		  // check whether it a comment line
		  // if YES then read length, width and maximum intensity
		  if(!check[0].equals("#"))
			 {
				 ppm2Ascii.width = Integer.parseInt(check[0]);
				 ppm2Ascii.length = Integer.parseInt(check[1]);
				 ppm2Ascii.maximumIntensity = Integer.parseInt(check[2]);
				 
				 // initialize length of inputs
				 ppm2Ascii.lengthInput = ppm2Ascii.width*ppm2Ascii.length;
				 
				 // initialize image and ppmAvg array
				 ppm2Ascii.image = new Image(ppm2Ascii.width,ppm2Ascii.length);
				 ppm2Ascii.ppmAvg = new int[ppm2Ascii.lengthInput];
				 
				 // reading the file and calling the load method to load PPM
				 while ((strLine3 = br.readLine()) != null)   
				  {
					 ppm2Ascii.loadPPM(strLine3,index);
					 index++;
				  }
				 
			 }
		  
		  // if its a comment line then read the next line for length, width and maximum intensity
		  else
		  {
			  strLine3 = br.readLine();
			  check = strLine3.split(" ");
			  ppm2Ascii.width = Integer.parseInt(check[0]);
			  ppm2Ascii.length = Integer.parseInt(check[1]);
			  ppm2Ascii.maximumIntensity = Integer.parseInt(check[2]);
			  
			  // initialize length of inputs
			  ppm2Ascii.lengthInput = ppm2Ascii.width*ppm2Ascii.length;
			  
			  // initialize image and ppmAvg array
			  ppm2Ascii.image = new Image(ppm2Ascii.width,ppm2Ascii.length);
			  ppm2Ascii.ppmAvg = new int[ppm2Ascii.lengthInput];
			  
			  // reading the file and calling the load method to load PPM
			  while ((strLine3 = br.readLine()) != null)   
			  {
				  ppm2Ascii.loadPPM(strLine3,index);
				  index++;
			  }
			  
		  }
		  
		  //checking for the number of arguments in command line
		  // 1 shows NO threshold
		  if(args.length==1)
		  {
		      ppm2Ascii.ppmToGray();
		      ppm2Ascii.grayToAscii();
		      asciiImage.displayASCII(ppm2Ascii.image, ppm2Ascii.width, ppm2Ascii.length);
		  }
		  
		  // 3 length of command line argument shows threshold
		  if(args.length==3 && args[1].equals("-t"))
		  {
			  ppm2Ascii.ppmToGray();
			  ppm2Ascii.grayToAsciiThreshold(Integer.parseInt(args[2]));
			  asciiImage.displayASCII(ppm2Ascii.image, ppm2Ascii.width, ppm2Ascii.length);
		  }


    }
    catch(Exception e)
    {
      System.out.println("Unhandled input...");
    }
    finally
    {
        //Close the input stream
    	in.close();
    }
    
  }
}

