package my.practies;

import java.util.ArrayList;
import java.util.List;

public class SimpleJavaPrac {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		String h = "hello";

		List<Integer> ages = new ArrayList<Integer>();
		ages.add(22);
		ages.add(30);

		List<String> al = new ArrayList<String>();
		al.add("Amit");
		al.add("Vijay");
		al.add("Kumar");
		al.add(1, "Sachin");

		for (int i = 0; i < ages.size(); i++) {
			System.out.println("ages list eliments are " + ages.get(i));
			for (String s : al) {
				System.out.println(h + " " + s);

			}

		}

		/*
		 * System.out.println("An element at 2nd position: " + al.get(2));
		 * System.out.println(h+" "+al.get(3));
		 * 
		 * for (String s : al) { System.out.println(h+" "+s);
		 * 
		 * }
		 * 
		 * for (String s : al) { if(s.equals("Vijay")) { System.out.println(h+" "+s+
		 * "!  you are  "+ ages.get(1)+" years old."); }else {
		 * System.out.println(h+" "+s);
		 * 
		 * }
		 * 
		 * }
		 */
	}

}
