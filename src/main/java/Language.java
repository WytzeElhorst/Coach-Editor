package main.java;

/**
 * Class that represents a language
 */
public class Language {
	private String name;
	
	/**
	 * @param n
	 * 		Name of the Language
	 */
	public Language(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
