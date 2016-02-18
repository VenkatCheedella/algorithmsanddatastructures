package Round1;


class A
{
	private void func()
	{
		System.out.println("Base function");
	}
}

class B extends A
{
	public void func()
	{
		System.out.println("Derived function with public access specifier");
	}
}


public class CheckRestrictiveInheritance {
		
	public static void main(String[] args) {		
		B obj_b = new B();
//		obj_b.func();
	}

}
