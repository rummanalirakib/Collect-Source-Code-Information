import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContentReader {

	private File inputFile;
	public ContentReader(File file) {
	  this.inputFile = file;
	}

	public File getInputFile() {
	  return inputFile;
	}

	public void setInputFile(File inputFile) {
	 this.inputFile = inputFile;
	}
	
	public int add(int x, int y, int z) {
		return x+y+z;
	}
	
	public void getString(String str)
	{
		
	}
	
	public void anotherString(BufferedReader br) {
		
	}

	public String read() throws IOException {
		 BufferedReader br = new BufferedReader(new FileReader(this.getInputFile()));
		 StringBuilder sb = new StringBuilder();
		 try {
		 String line = br.readLine();
		 while (line != null) {
			 sb.append(line);
			 sb.append(System.lineSeparator());
			 line = br.readLine();
		 }
		 } finally {
			 br.close();
		 }
		 getInputFile();
		 sb.trimToSize();
		 anotherString(br);
		 getString(br.readLine());
		 String everything = sb.toString();
		 return everything;
	 }

	public static void main (String args[]) {
		ContentReader reader = new ContentReader(new File ("Person.java"));
		int addition = reader.add(5, 6, 7);
		 try {
			 System.out.println(reader.read());
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
    }
}
