
public class AsciiImage extends Image
{

	/**
	 * Class to display the resultant ASCII image 
	 */
	
	
	void displayASCII(Image image,int width, int length)
	{
		// displaying value pixel by pixel
		int counter =0;
		
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<width;j++)
			{
				
				System.out.print(image.output[counter++]+" ");
				
			}
			
			System.out.println();
		}
	}

}
