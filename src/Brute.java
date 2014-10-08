
public class Brute {
    public static void main(String[] args)
    {
        int N = StdIn.readInt();
        Point[] a = new Point[N];
        int x = 0;
        int y = 0;
        for (int i = 0; i < N; i++)
        {
            x = StdIn.readInt();
            y = StdIn.readInt();
            a[i] = new Point(x, y);
        }
        
        for (int i = 0; i < N; i++)
            for (int j = i; j < N; j++)
                for (int k = j; k < N; k++)
                    for (int l = k; l < N; l++)
                    {
                        if (a[i].slopeTo(a[j]) == a[i].slopeTo(a[k])
                            && a[i].slopeTo(a[k]) == a[i].slopeTo(a[l]))
                            StdOut.println(a[i].toString() + " -> " + a[j].toString() 
                                    +" -> " + a[k].toString() + " -> "+a[l].toString());
                    }
    }
}
