
public class Brute {
    public static void main(String[] args)
    {
        String filePath = StdIn.readString();
        In fileInput = new In(filePath);
        int N = fileInput.readInt();
        Point[] a = new Point[N];
        int x = 0;
        int y = 0;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < N; i++)
        {
            x = fileInput.readInt();
            y = fileInput.readInt();
            a[i] = new Point(x, y);
            a[i].draw();
        }
        Point[] linePoints = new Point[4];
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    for (int l = k + 1; l < N; l++)
                    {
                        if (a[i].slopeTo(a[j]) == a[i].slopeTo(a[k])
                            && a[i].slopeTo(a[k]) == a[i].slopeTo(a[l]))
                        {
                            linePoints[0] = a[i];
                            linePoints[1] = a[j];
                            linePoints[2] = a[k];
                            linePoints[3] = a[l];
                            Insertion.sort(linePoints);
                            StdOut.println(linePoints[0].toString() + " -> " + linePoints[1].toString() 
                                    +" -> " + linePoints[2].toString() + " -> "+ linePoints[3].toString());
                            linePoints[0].drawTo(linePoints[3]);
                        }
                    }
    }
}
