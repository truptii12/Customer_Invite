package Customer_Invitation.Customer_Invitation;

import java.io.File;

import junit.framework.TestCase;

public class TestClass extends TestCase {
	public void FileOpening() {
		TestClass tester = new TestClass(); // MyClass is tested
		File f = new File("H:\\Customer_Text_JSON\\19896c50afa89ad4dec3-6c11047887a03483c50017c1d451667fd62a53ca\\gistfile1.txt");
        // assert statements
        assertEquals("File Read Sucessfull", f!=null, f);
        assertEquals("File Read UnSucessfull", (null), f); 
    }
}
