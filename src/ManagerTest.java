import org.junit.jupiter.api.Test;

class ManagerTest {

    @Test
    void getFirstName() {
   // not working

        //given
        Manager manager = new Manager("A","B");
        String expectedFirstName = "A";

        // when

        //then
        assert(expectedFirstName.equals(manager.getFirstName()));
    }
}