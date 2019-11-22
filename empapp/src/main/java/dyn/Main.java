package dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Printable printable = (Printable) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{Printable.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return "return from proxy";
                    }
                });

        System.out.println(printable.print());

        Printable p = new LogPrintable(new Document());
        System.out.println(p.print());
    }
}
