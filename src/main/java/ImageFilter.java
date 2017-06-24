package main.java;

import java.io.File;
/**
 * Class that filters for images
 */
public class ImageFilter extends javax.swing.filechooser.FileFilter{
	
	/**
	 * Filters the images that don't end with .png or .jpg
	 */
    public boolean accept(File f){
    return f.isDirectory()||(f.isFile()&&f.getName().toLowerCase().endsWith(".png") ||(f.isFile()&&f.getName().toLowerCase().endsWith(".jpg")));
    }

    public String getDescription(){
    return "image files";
    }
}
