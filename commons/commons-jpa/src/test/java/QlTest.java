import com.github.platform.jpa.base.Conditions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QlTest {

    @Test
    public void qlTesets() {
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");


        Conditions ne = Conditions.where("sf").is("f").and("fjf").like("").and("ff").ne("f");
        System.out.println(ne.toString());


        Conditions cs = Conditions.where();
        cs.and("entCode").is("234324");
        cs.and("inventoryCode").ne("333");
        cs.or(Conditions.where("name").in(strings)
                .and("id").gt("1111")
                .and("fjfjjfssss").lt("222")
                .and("entCode").like("%8888%")
        );
        Map<String, Object> params = new HashMap<>();
        System.out.println(cs.toQL(params));
        System.out.println(params);


        Conditions and = Conditions.where("a").is("1").and(Conditions.where("b").is(1).or(Conditions.where("c").is(2).and("d").ne(3)));
        System.out.println(and.toQL(new HashMap<>()));
    }


}
