public class NBody {
    public static double readRadius(String loc) {
        In in = new In(loc);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String loc) {
        In in = new In(loc);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[planetNum];
        for (int i = 0; i < planetNum; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            Planet planet = new Planet(xPos, yPos, xVel, yVel, m, img);
            planets[i] = planet;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int planetNum = planets.length;
        

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        String universe = "images/starfield.jpg";
        StdDraw.picture(0, 0, universe, radius*2, radius*2);
        for (Planet planet : planets) {
            planet.draw();
        }
		StdDraw.enableDoubleBuffering();


        double time;
        for (time = 0; time < T; time += dt) {
            double[] xForces = new double[planetNum];
            double[] yForces = new double[planetNum];
            for (int i = 0; i < planetNum; i++) {
                double netX = planets[i].calcNetForceExertedByX(planets);
                double netY = planets[i].calcNetForceExertedByY(planets);
                xForces[i] = netX;
                yForces[i] = netY;

            }
            for (int i = 0; i < planetNum; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);

                StdDraw.clear();
                StdDraw.picture(0, 0, universe, radius*2, radius*2);
                for (Planet planet : planets) {
                    planet.draw();
                }
                StdDraw.show();
                StdDraw.pause(10);
            }
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
