public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 6.67e-11;
    private static double Scale_parameter = 5e8;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet planet){
        double distance_x = this.xxPos-planet.xxPos;
        double distance_y = this.yyPos-planet.yyPos;
        return Math.sqrt(distance_x * distance_x + distance_y * distance_y);
    }

    public double calcForceExertedBy(Planet planet){
        double Distance = this.calcDistance(planet) * this.calcDistance(planet);
        double mass_multi = this.mass * planet.mass;
        return G*mass_multi/Distance;
    }

    public double calcForceExertedByX(Planet planet){
        double Radius = this.calcDistance(planet);
        if(Radius == 0)
            return 0;
        double Distance_x = planet.xxPos - this.xxPos;
        double Force = calcForceExertedBy(planet);
        return Force*Distance_x/Radius;
    }

    public double calcForceExertedByY(Planet planet){
        double Radius = this.calcDistance(planet);
        if (Radius == 0)
            return 0;
        double Force = calcForceExertedBy(planet);
        double Distance_y = planet.yyPos - this.yyPos;
        return Force*Distance_y/Radius;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        int length = planets.length;
        double sum = 0;
        for(int i=0;i<length;i++){
            sum+=this.calcForceExertedByX(planets[i]);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        int length = planets.length;
        double sum = 0;
        for (int i=0;i<length;i++){
            sum+=this.calcForceExertedByY(planets[i]);
        }
        return sum;
    }

    public void update(double dt, double force_x, double force_y){
        double acceleration_x = force_x/this.mass;
        double acceleration_y = force_y/this.mass;
        this.xxVel = this.xxVel + acceleration_x*dt;
        this.yyVel = this.yyVel + acceleration_y*dt;
        this.xxPos = this.xxPos + xxVel*dt;
        this.yyPos = this.yyPos + yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(this.xxPos/Scale_parameter,this.yyPos/Scale_parameter,imgFileName);
    }
}
