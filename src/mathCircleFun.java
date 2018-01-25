public class mathCircleFun {

    private static void cardioid(double[][] points, double multiplier, int depth) {
        // draws lines from point to point
        // depth acts as counter    -> (depth % points.length) is used for enumerating the points
        for (int i = 0; i <= depth; i++) {

            // POINT 1  -> i
            double[] point1 = new double[2];
            int pickPoint1 = i % (points.length);
            point1[0] = points[pickPoint1][0]; // xCoordinate - Point1
            point1[1] = points[pickPoint1][1]; // yCoordinate - Point1
            //System.out.print("point1 ->\t" + pickPoint1 + "\t#\t");   // textual output

            // POINT 2  -> (i * multiplier) % points.length
            double[] point2 = new double[2];
            int pickPoint2 = (int) ((i * multiplier) % points.length);
            point2[0] = points[pickPoint2][0];   // xCoordinate - Point2
            point2[1] = points[pickPoint2][1];   // yCoordinate - Point2
            //System.out.println("point2 ->\t" + pickPoint2);   // textual output

            // draw line
            drawLine(point1, point2);
            //StdDraw.pause(10);  // drawing speed
        }
    }

    private static double[][] createPoints(double[] circleCenter, double circleRadius, int howMany) {
        double[][] points = new double[howMany][2];
        double angle = 0;

        // add points to point-Array
        for (int i = 0; i < points.length; i++) {

            // create point coordinates
            double xCoord = circleCenter[0] + (circleRadius * Math.cos(angle));
            double yCoord = circleCenter[1] + (circleRadius * Math.sin(angle));

            // put CoordVars into pointArray
            points[i][0] = xCoord;
            points[i][1] = yCoord;
            //System.out.println("points[" + i + "][0] -> " + points[i][0] + "\t#\tpoints[" + i + "][1] -> " + points[i][1]);

            // draw point
            drawPoint(points[i]);

            // update angle
            angle += (Math.PI / howMany) * 2;
        }
        return points;
    }

    private static void drawCircle(double[] center, double circleRadius) {
        // Background
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(center[0], center[1], center[0], center[1]);
        StdDraw.enableDoubleBuffering();
        // Circle
        //StdDraw.setPenRadius(0.02);
        //StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        //StdDraw.circle(center[0], center[1], circleRadius);
    }

    private static void drawPoint(double[] point) {
        // draw points
        StdDraw.setPenRadius(0.0075);
        // StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        // StdDraw.text(points[i][0] + 20, points[i][1] + 20, ("point" + (i + 1)));
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(point[0], point[1]);

        // draw it sloooow
        //StdDraw.pause(200);  // drawing speed
    }

    private static void drawLine(double[] point1, double[] point2) {
        StdDraw.setPenRadius(0.0015);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.line(point1[0], point1[1], point2[0], point2[1]);
    }

    public static void main(String[] args) {
        // output variables and configuration
        int width = 1000;
        int height = 1000;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        // VARIABLES ////////////////////////////////////////////////////////////////////////////////
        // circle Variables
        double[] center = {width / 2, height / 2};
        double radius = 475;

        // cardioid Variables
        int howMany = 1000;
        double multiplier = 2;
        // nice multipliers
        // multiplier   ->    300   - 5     - 63    - 92
        int depth = howMany * 1; // is the amount how many lines will be drawn for cardioid shape
        ////////////////////////////////////////////////////////////////////////////////////////////


        System.out.print("drawing... ");
        drawCircle(center, radius);
        double[][] points = createPoints(center, radius, howMany);
        cardioid(points, multiplier, depth);
        StdDraw.show();
        StdDraw.pause(5000);

        System.out.println("done");

        // draws circle

        for (double i = multiplier; i <= 100; i += 0.055) {
            if (i > 2) StdDraw.clear();
            drawCircle(center, radius);
            double[][] pointsMoving = createPoints(center, radius, howMany);
            cardioid(pointsMoving, i, depth);

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setPenRadius(0.01);
            StdDraw.text(width / 8, height / 8, "Multiplier between " + (int)i + " and " + ((int)i+1));

            StdDraw.show();
            //StdDraw.pause();
        }


        /**
         * TODO: Mouse input to draw again
         *
         */
    }
}
