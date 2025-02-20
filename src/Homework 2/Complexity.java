//John Graham
//I pledge my honor that I have abided by the Stevens Honor System.

public class Complexity {

    public static void method1(int n) {
        int counter=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.println("Operations: "+counter);
                counter++;
            }
        }
    }

    public static void method2(int n) {
        int counter=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    System.out.println("Operations: "+counter);
                    counter++;
                }
            }
        }
    }

    public static void method3(int n) {
        int counter=0;
        for(int i=1; i<n; i=i*2) {
            System.out.println("Operations: "+counter);
            counter++;
        }
    }


    /*  Question #4

        Iteration       Start       End
        1               0           31
        2               16          31
        3               24          31
        4               28          31
        5               30          31
        6				31			31   (last iteration of while loop)


        Iteration       Start       End
        1               0           63
        2               32          63
        3               48          63
        4               56          63
        5               60          63
        6               62          63
        7				63			63   (last iteration of while loop)
    */

    //Question 5
    //The number of iterations is equal to the logarithm (base 2) of n (or the size of list a).

    //Question 6
    //bSearch has a time complexity of O(log(n)) (logarithmic time complexity).

    public static void method4(int n) {
        int counter=0;
        for(int i=0; i<n; i++) {
            for(int j=1; j<n; j=j*2) {
                System.out.println("Operations: "+counter);
                counter++;
            }
        }
    }

    public static void method5(int n) {
        int counter=0;
        for(int i=2; i<n; i=i*i) {
            System.out.println("Operations: "+counter);
            counter++;
        }
    }
}
