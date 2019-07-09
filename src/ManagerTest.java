import org.junit.jupiter.api.Test;

class ManagerTest {

    @Test
    void getFirstName() {
        //given
        Manager manager = new Manager("A","B");
        String expectedFirstName = "A";

        //then
        assert(expectedFirstName.equals(manager.getFirstName()));
    }
}