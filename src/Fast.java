import java.util.Arrays;
public class Fast {
    public static void main(String[] args)
    {
        String filePath = args[0];
        In fileInput = new In(filePath);
        int N = fileInput.readInt();
        Point[] a = new Point[N];
        int x = 0;
        int y = 0;
        int lineSeqCount = 0;
        double lineSlope = 0;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < N; i++)
        {
            x = fileInput.readInt();
            y = fileInput.readInt();
            a[i] = new Point(x, y);
            a[i].draw();
        }       
        boolean ignored = false;
        for (int i = 0; i < N - 1; i++)
        {
            Arrays.sort(a);
            Arrays.sort(a, a[i].SLOPE_ORDER);
            lineSlope = a[0].slopeTo(a[1]);
            lineSeqCount = 2;
            if (a[0].compareTo(a[1]) > 0)
                ignored = true;
            else
                ignored = false;
            for (int j = 2; j < N; j++)
            {
                if (a[0].slopeTo(a[j]) == lineSlope)
                {
                    lineSeqCount += 1;
                }
                else
                {
                    lineSlope = a[0].slopeTo(a[j]);
                    if (lineSeqCount >= 4 && !ignored)
                    {
                        processLinePoints(a, j + 1 - lineSeqCount, j - 1);
                    }
                    ignored = false;
                    lineSeqCount = 2;
                }
                if (a[0].compareTo(a[j]) > 0)
                {
                    ignored = true;
                }
            }
            if (lineSeqCount >= 4 && !ignored)
            {
                processLinePoints(a, N + 1 - lineSeqCount, N-1);
            }
        }
    }
    
    private static void processLinePoints(Point[] a, int first, int last)
    {
        Arrays.sort(a, first, last + 1);
        a[0].drawTo(a[last]);
        StdOut.print(a[0].toString() + " -> ");
        for (int i = first; i < last; i++)
        {
            StdOut.print(a[i].toString() + " -> ");
        }
        StdOut.print(a[last].toString()+"\n");
    }
}
