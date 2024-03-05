public class Planet {
    public static double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xPos, double yPos, double xVel, double yVel, double m, String img) {
        xxPos = xPos;
        yyPos = yPos;
        xxVel = xVel;
        yyVel = yVel;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet other) {
        double delta_x = xxPos - other.xxPos;
        double delta_y = yyPos - other.yyPos;
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance;
    }
    public double calcForceExertedBy(Planet other) {
        double r = calcDistance(other);
        double force = Planet.G * mass * other.mass / r / r;
        return force; 
    }
    public double calcForceExertedByX(Planet other) {
        double dx = - xxPos + other.xxPos;
        double r = calcDistance(other);
        double force = calcForceExertedBy(other);
        double Fx = force * dx / r;
        return Fx;
    }
    public double calcForceExertedByY(Planet other) {
        double dy = - yyPos + other.yyPos;
        double r = calcDistance(other);
        double force = calcForceExertedBy(other);
        double Fy = force * dy / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0.0;
        for (Planet other : planets) {
            if (!this.equals(other)) {
                netForceX += calcForceExertedByX(other);
            }
        }
        return netForceX;
    }
    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet other : planets) {
            if (!this.equals(other)) {
                netForceY += calcForceExertedByY(other);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
