/*
 * C343 Summer 2018
 * Lab mini-assignment 04
 * Ian Polito
 * ipolito
 */

public class Lab04 {
	
	public static void main(String[] args) {
		System.out.println("java.vm.version is " + System.getProperty("java.vm.version"));
		System.out.println("java.vm.vendor is " + System.getProperty("java.vm.vendor"));
		System.out.println("java.vm.name is " + System.getProperty("java.vm.name"));
		System.out.println("java.vm.specification.version is " + System.getProperty("java.vm.specification.version"));
		System.out.println("java.vm.specification.vendor is " + System.getProperty("java.vm.specification.vendor"));
		System.out.println("java.vm.specification.name is " + System.getProperty("java.vm.specification.name"));
		System.out.println("java.version is " +  System.getProperty("java.version"));
		System.out.println("java.vendor is " + System.getProperty("java.vendor"));
		
		//test Student class
		Student me = new Student("Ian Polito");
		me.setDepartment("Computer Science");
		me.display();
		Student[] courseList = new Student[3];
		courseList[0] = me;
		courseList[1] = new Student("Joe");
		courseList[1].setDepartment("Computer Science");
		courseList[1].setName("Joel");
		courseList[2] = new Student("Moira");
		courseList[2].setDepartment("Computer Science");
		System.out.println(courseList[2].getDepartment());
	}
}
