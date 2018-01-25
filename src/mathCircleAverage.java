import java.awt.Polygon;

public class mathCircleAverage {

    private static boolean checkInstance(double[] circleCenter, double circleRadius, double[][] points) {
        // General Information:
        // creates triangle/Polygon with three given points
        // checks if triangle/Polygon contains circleCenter{0.5,0.5}
        //      if yes -> TRUE
        //      if no  -> FALSE
        // double[][] points contains pointCoordinates
        int[] xValuesPolygon = new int[points.length];
        int[] yValuesPolygon = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            xValuesPolygon[i] = (int) points[i][0];
            yValuesPolygon[i] = (int) points[i][1];
        }
        // actual check
        Polygon triangle = new Polygon(xValuesPolygon, yValuesPolygon, 3);
        if (triangle.contains(375, 375)) return true;
        return false;
    }

    private static double[][] choosePoints(double[] circleCenter, double circleRadius, int pointAmount) {
        // draw the circle
        drawCircle(circleCenter, circleRadius);

        // choose 3 random points on circle line
        double[][] points = new double[pointAmount][2];

        // add points to point-Array
        for (int i = 0; i < points.length; i++) {
            // choose random Angle
            double angle = (Math.random() * 1000) + 1; // random number 1-1000
            angle = (angle % 360);  // mod 360 defines angle -> numbers 0-359 (0==360)

            // use randPoint to determine random point on circle
            double xCoord = circleCenter[0] + (circleRadius * Math.cos(angle));
            double yCoord = circleCenter[1] + (circleRadius * Math.sin(angle));

            // put CoordVars into pointArray
            points[i][0] = xCoord;
            points[i][1] = yCoord;
            //System.out.println("points[" + i + "][0] -> " + points[i][0] + "\t#\tpoints[" + i + "][1] -> " + points[i][1]);

            // draw random points
            StdDraw.setPenRadius(0.025);
            StdDraw.point(points[i][0], points[i][1]);
        }

        // draw triangle
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i][0];
            yValues[i] = points[i][1];
        }
        StdDraw.polygon(xValues, yValues);

        return points;  // returns coordinates of three points
    }

    private static void drawCircle(double[] center, double circleRadius) {
        // circle
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(center[0], center[1], circleRadius);
        // circle Center
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(center[0], center[1]);
    }

    public static void main(String[] args) {
        // output variables and configuration
        int width = 750;
        int height = 750;
        StdDraw.setCanvasSize();
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();

        // circle Variables
        double[] circleCenter = new double[2];
        circleCenter[0] = width / 2;
        circleCenter[1] = height / 2;
        double circleRadius = width / 2;
        int pointAmount = 3;
        double howOften = 10000;
        double count = 0; // counts if circleCenter is in Polygon

        // draw circle and points
        System.out.print("Calculating... ");
        for (int i = 0; i < howOften; i++) {
            StdDraw.clear();
            double[][] points = choosePoints(circleCenter, circleRadius, pointAmount);
            boolean containsCenter = checkInstance(circleCenter, circleRadius, points);

            // checks if circleCenter is inside the triangle
            if (containsCenter) count++;

            // show i
            StdDraw.text(width / 2, height / 3, "i = " + (i + 1));

            StdDraw.show();
            //StdDraw.pause(100);
            //System.out.println();
        }
        System.out.println("done");
        System.out.println("count\t-> " + count);

        double probability = 100 * (count / howOften);
        System.out.println("Probability\t-> " + probability + "%");
    }
}
