public class NBody {
    private static String BackgroundImage = "images/starfield.jpg";
    private static double Scale_parameter = 5e8;
    private static int Pause_time = 10;
    public static double readRadius(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double xxPos,yyPos,xxVel,yyVel,mass;
        String imgFileName;
        Planet[] planets = new Planet[N];
        double Radius = in.readDouble();
        for(int i=0;i<N;i++){
            xxPos=in.readDouble();
            yyPos=in.readDouble();
            xxVel=in.readDouble();
            yyVel=in.readDouble();
            mass=in.readDouble();
            imgFileName=in.readString();
            planets[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,"./images/" + imgFileName);
        }
        return planets;
    }
    
    public static void main(String[] args){
        if(args.length != 3){
            System.out.println("You need 3 parameters!");
            return;
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        Planet[] planets = readPlanets(args[2]);
        double Radius = readRadius(args[2]);

        /* draw the background and all the planets*/
        StdDraw.setScale(-Radius/ Scale_parameter, Radius/ Scale_parameter);
        StdDraw.picture(0, 0, BackgroundImage);
        for(int i=0;i<planets.length;i++)
            planets[i].draw();

        /* create the animation */
        StdDraw.enableDoubleBuffering();
        double T_cur = 0;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        while(T_cur <= T){
            for(int i=0;i<planets.length;i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0, 0, BackgroundImage);
            for(int i=0;i<planets.length;i++)
                planets[i].draw();
            StdDraw.show();
            StdDraw.pause(Pause_time);
            T_cur+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}
