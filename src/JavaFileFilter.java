import java.io.File;
import javax.swing.filechooser.FileFilter;

public class JavaFileFilter extends FileFilter {

	public boolean accept(File file) {
		if (file.getName().endsWith(".txt")) {
			return true;
		}
		return file.isDirectory();
	}

	public String getDescription() {
		return ".txt Files";
	}
}
