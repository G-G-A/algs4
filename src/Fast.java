import java.util.Arrays;
public class Fast {
    public static void main(String[] args)
    {
        String filePath = StdIn.readString();
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
        Quick.sort(a);
        for (int i = 0; i < N; i++)
        {
            Arrays.sort(a, i, N, a[i].SLOPE_ORDER);
            for (int j = i + 1; j < N; j++)
            {
                if (lineSeqCount > 0)
                {
                    if (a[i].slopeTo(a[j]) == lineSlope )
                    {
                        lineSeqCount+=1;
                    }
                    else
                    {
                        
                    }
                }
                
            }
        }
        
    }
    
    private void processLinePoints(Point[] a, int first, int last)
    {
        a[first].drawTo(a[last]);
        for (int i = first; i<last; i++)
        {
            StdOut.print(a[i].toString() + " -> ");
        }
        StdOut.print(a[last].toString()+"\n");
    }
}
