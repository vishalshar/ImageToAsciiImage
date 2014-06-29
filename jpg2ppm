import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

/**
 * <p>Converts .jpg images to .ppm images.</p>
 *
 * @author Pedro Tejada and Minghui Jiang
 * @version Wed Mar 25 10:45:58 MDT 2009
 */
class jpg2ppm {
	public static void main(String[] args) throws IOException {
		String inputFile = null, outputFile = null;
		boolean gray = false;
		int i = 0;

		if (i < args.length)
			inputFile = args[i++];
		if (i < args.length && !args[i].startsWith("-"))
			outputFile = args[i++];
		for (; i < args.length; i++) {
			if (args[i].equals("-g"))
				gray = true;
			else
				printHelpAndExit();
		}

		if (inputFile != null && outputFile != null)
			convert(inputFile, outputFile, gray);
		else if (inputFile != null && outputFile == null)
			view(inputFile, gray);
		else
			printHelpAndExit();
	}

	static void printHelpAndExit() {
		System.out.println("parameters:\n"
				+ "\tinput_file_name [output_file_name] [options]");
		System.out.println("options:\n"
				+ "\t-g\tconvert to grayscale");
		System.exit(1);
	}

	static BufferedImage getImage(String file) throws IOException {
		BufferedImage image = null;

		if (file.endsWith(".jpg") || file.endsWith(".jpeg")) {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(is);
			image = decoder.decodeAsBufferedImage();
		} else if (file.endsWith(".ppm"))
			image = decodeImage(file);
		return image;
	}

	static void makeGray(BufferedImage image) {
		ColorConvertOp op = new ColorConvertOp(
				ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		op.filter(image, image);
	}

	static void view(String file, boolean gray) throws IOException {
		BufferedImage image = getImage(file);
		JFrame frame = new JFrame(file);
		if (gray)
			makeGray(image);
		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	static void convert(String inputFile, String outputFile,
			boolean gray) throws IOException {
		BufferedImage image = getImage(inputFile);
		if (gray)
			makeGray(image);
		encodeImage(image, outputFile);
	}

	static void encodeImage(BufferedImage image,
			String file) throws IOException {
		PrintWriter writer = new PrintWriter(
				new BufferedWriter(new FileWriter(file)));
		writer.println("P3");	// signature
		writer.println("# created by jpg2ppm");
		writer.println(image.getWidth() + " " + image.getHeight() + " 255");

		for (int y = 0; y < image.getHeight(); y++)
			for (int x = 0; x < image.getWidth(); x++)
				for (int rgb = 0; rgb <= 2; rgb++) {
                    writer.print(image.getRaster()
						.getSample(x, y, rgb % image.getRaster().getNumBands()));
					if (rgb < 2)
						writer.print(" ");
					else
						writer.println();
				}
		writer.close();
	}

	static BufferedImage decodeImage(String file) throws IOException {
		StreamTokenizer parser = new StreamTokenizer(new BufferedReader(
					new InputStreamReader(new FileInputStream(file))));
		parser.commentChar('#');

		parser.nextToken();	// signature
		if (parser.ttype != StreamTokenizer.TT_WORD && parser.sval != "P3")
			throw new IOException("Invalid file format");

		parser.nextToken();
		int width = (int) parser.nval;

		parser.nextToken();
		int height = (int) parser.nval;

		parser.nextToken();	// max-value

		BufferedImage image = new BufferedImage(
				width, height, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				for (int rgb = 0; rgb <= 2 ; rgb++) {
					parser.nextToken();
					if (parser.ttype == StreamTokenizer.TT_EOF)
						throw new EOFException("Unexpected end of file");
					if (parser.ttype != StreamTokenizer.TT_NUMBER)
						throw new IOException("Invalid file format");
					image.getRaster().setSample(x, y, rgb, (int) parser.nval);
				}
		return image;
	}
}
