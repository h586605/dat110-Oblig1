package no.hvl.dat110;

public class TODO {

	public static String method() {
		
		//lmao test
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		return "Metoden " + methodName + " er ikke implementert";
	}
	
	
	public static String construtor(String className) {
				
	   return "Konstruktøren for klassen " + className + " er ikke implementert";
		
	}

}
