package main.java;

import java.io.File;

/**
 * Class that filters for text files
 */
public class TxtFilter extends javax.swing.filechooser.FileFilter{
	
	/**
	 * Filters the images that don't end with .txt or .xml
	 */
    public boolean accept(File f){
    return f.isDirectory()||(f.isFile()&&f.getName().toLowerCase().endsWith(".txt") ||(f.isFile()&&f.getName().toLowerCase().endsWith(".xml")));
    }

    public String getDescription(){
    return ".txt + .xml files";
    }
}
