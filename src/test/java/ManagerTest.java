import models.Manager;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ManagerTest{

    Manager manager;

    @Before
    public void setUp(){
        manager = new Manager("John", 0760456,"john.gmail.com");

    }

    @Test
    public void hasName(){
        assertEquals("John", manager.getName());
    }

    @Test
    public void hasMobileNumber(){
        assertEquals(0760456, manager.getPhoneNo());
    }

}