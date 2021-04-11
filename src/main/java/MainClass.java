import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class MainClass {
    public static void main(String[] args) throws Exception{
        Class c = MyClass.class;
        Object testObj = c.newInstance();
        Method[] methods = c.getDeclaredMethods();
        ArrayList<Method> al = new ArrayList<>();
        Method bMethod = null;
        Method aMethod = null;
        for (Method o : c.getDeclaredMethods()) {
            if (o.isAnnotationPresent(Test.class)) {
                al.add(o);
            }
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                if (bMethod == null) bMethod = o;
                else throw new RuntimeException("Больше 1 метода @BeforeSuite");
            }
            if (o.isAnnotationPresent(AfterSuite.class)) {
                if (aMethod == null) aMethod = o;
                else throw new RuntimeException("Больше 1 метода @AfterSuite");
            }
            al.sort(new Comparator<Method>() {
                @Override
                public int compare(Method o1, Method o2) {
                    return o2.getAnnotation(Test.class).priority()-o1.getAnnotation(Test.class).priority();
                }
            });
        }
        if (bMethod != null) bMethod.invoke(testObj, null);
        for (Method o : al) o.invoke(testObj, null);
        if (aMethod != null) aMethod.invoke(testObj, null);

    }
}
