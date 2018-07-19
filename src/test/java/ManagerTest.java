import models.Manager;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ManagerTest {

    Manager manager;

    @Before
    public void before(){
        manager = new Manager("Lionel Spencer-Clark", "01847 873245", "lionel.lad@btinternet.com");
    }

    @Test
    public void hasName(){
        assertEquals("Lionel Spencer-Clark", manager.getName());
    }

    @Test
    public void hasPhoneNumber(){
        assertEquals("01847 873245", manager.getPhoneNo());
    }

    @Test
    public void hasEmailAddress(){
        assertEquals("lionel.lad@btinternet.com", manager.getEmail());
    }

}
