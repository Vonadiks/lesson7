public class MyClass {
    @BeforeSuite
    public void begin() {
        System.out.println("begin");
    }

    @Test(priority = 3)
    public void test1() {
        System.out.println("test1 priority 3");
    }

    @Test(priority = 7)
    public void test2() {
        System.out.println("test2 priority 7");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("test3 priority 1");
    }

    @Test(priority = 5)
    public void test4() {
        System.out.println("test4 priority 5");
    }

    @AfterSuite
    public void end() {
        System.out.println("end");
    }
}
