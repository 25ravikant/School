package practical;

import java.util.ArrayList;
import java.util.LinkedList;

public class Practical {

    public static void main(String[] args) {
        ArrayList aryList = new ArrayList();
        LinkedList list = new LinkedList();

        Long st = System.nanoTime();
        for(int i=0;i<10000;i++)
        {
            aryList.add(i);
        }
        Long ed = System.nanoTime();
        System.out.println("aryList="+(ed-st));

        Long st1 = System.nanoTime();
        for(int i=0;i<10000;i++)
        {
            list.add(i);
        }
        Long ed1 = System.nanoTime();
        System.out.println("List="+(ed1-st1));

        Long st11 = System.nanoTime();
        for(int i=0;i<10000;i++)
        {
            aryList.get(i);
        }
        Long ed11 = System.nanoTime();
        System.out.println("aryList="+(ed11-st11));

        Long st12 = System.nanoTime();
        for(int i=0;i<10000;i++)
        {
            list.get(i);
        }
        Long ed12 = System.nanoTime();
        System.out.println("List="+(ed12-st12));

    }

}
