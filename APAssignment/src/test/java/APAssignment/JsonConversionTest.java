package APAssignment;

import APAssignment.Model.Zoo.Zoo;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonConversionTest {

    @Test
    public void test1() {
        Initialise.basicZoo();

        Zoo zoo1 = new Zoo();
        System.out.println(zoo1);
        System.out.println();

        JsonConversion.createJsonFile(zoo1, "converted.json");
        Zoo zoo2 = (Zoo) JsonConversion.getZooFromDefaultJsonFile();
        System.out.println(zoo2);

        System.out.println( zoo1.getPenListById().get("Dry1").getClass() );
        System.out.println( zoo2.getPenListById().get("Dry2").getClass() );
        assertEquals(zoo1.getPenListById().get("Dry1").getClass(), zoo2.getPenListById().get("Dry2").getClass());
    }

}