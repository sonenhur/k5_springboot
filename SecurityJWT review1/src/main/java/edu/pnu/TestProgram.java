package edu.pnu;

@FunctionalInterface
interface TestInterface {
    public void test(String s);
}

public class TestProgram {
    public static void maintest(TestInterface ti) {
        ti.test("abcd1234");
    }

    public static void main(String[] args) {
        TestInterface ti = new TestInterface() {
            @Override
            public void test(String s) {
                System.out.println("Test:" + s);
            }
        };
        ti.test("abcd");

        maintest(s -> System.out.println("this is Test: " + s));
    }
}
