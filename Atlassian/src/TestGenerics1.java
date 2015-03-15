import java.util.ArrayList;
import java.util.List;


public class TestGenerics1 {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	
	
//	List<Apple> appleList = new ArrayList<Apple>();
//	
//	if (appleList instanceof List<Apple>) { System.out.println("true"); }

	
	String a = "foo";
	String b = "food".substring(0, 3);
	String c = b.intern();

	if (a.equals(b)) {
	    if (a == b) {
	        System.out.println("1");
	    } else if (a == c) {
	        System.out.println("2");
	    } else {
	        System.out.println("3");
	    }
	} else {
	    System.out.println("4");
	}
	
    }

}
