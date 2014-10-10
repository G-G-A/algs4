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
        for (int i = 0; i < N - 1; i++)
        {
            Arrays.sort(a, i, N);
            Arrays.sort(a, i, N, a[i].SLOPE_ORDER);
            lineSlope = a[i].slopeTo(a[i+1]);
            lineSeqCount = 2;
            for (int j = i + 2; j < N; j++)
            {
                if (a[i].slopeTo(a[j]) == lineSlope)
                {
                    lineSeqCount += 1;
                }
                else
                {
                    lineSlope = a[i].slopeTo(a[j]);
                    if (lineSeqCount >= 4)
                    {
                        processLinePoints(a, j + 1 - lineSeqCount, j - 1, i);
                    }
                    lineSeqCount = 2;
                }
            }
            if (lineSeqCount >= 4)
            {
                processLinePoints(a, N + 1 - lineSeqCount, N-1, i);
            }
        }
    }
    
    private static void processLinePoints(Point[] a, int first, int last, int pivot)
    {
        Arrays.sort(a, first, last + 1);
        a[pivot].drawTo(a[last]);
        StdOut.print(a[pivot].toString() + " -> ");
        for (int i = first; i < last; i++)
        {
            StdOut.print(a[i].toString() + " -> ");
        }
        StdOut.print(a[last].toString()+"\n");
    }
}
