import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestRegexxx {

    
    public static void main(String[] args) {
	
	String source = "AASAS,CALLMED,ADAD,ASAS,";
	
//	Pattern p = Pattern.compile(("^+(,\\w+,)?(CALL)(,\\w+,).*"));
	Pattern p = Pattern.compile(("^.*,(CALL\\w+),.*$"));
	Matcher m = p.matcher(source);
	
        if(m.find()) {
            System.out.println("Matching group found 0: " +m.group(0));
            System.out.println("Matching group found 1: " +m.group(1));
//            System.out.println("Matching group found 2: " +m.group(2));
//            System.out.println("Matching group found 3: " +m.group(3));
        }
	
    }
}
