
public class Brute {
    public static void main(String[] args)
    {
        String filePath = StdIn.readString();
        In fileInput = new In(filePath);
        int N = fileInput.readInt();
        Point[] a = new Point[N];
        int x = 0;
        int y = 0;
        for (int i = 0; i < N; i++)
        {
            x = fileInput.readInt();
            y = fileInput.readInt();
            a[i] = new Point(x, y);
        }
        
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    for (int l = k + 1; l < N; l++)
                    {
                        if (a[i].slopeTo(a[j]) == a[i].slopeTo(a[k])
                            && a[i].slopeTo(a[k]) == a[i].slopeTo(a[l]))
                            StdOut.println(a[i].toString() + " -> " + a[j].toString() 
                                    +" -> " + a[k].toString() + " -> "+a[l].toString());
                    }
    }
}
