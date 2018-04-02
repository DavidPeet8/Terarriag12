
package gdx.game.ScrLoad;

import java.util.Random;

public class Noise {
    //----------------------------------------------Declare-------------------------------------------------------------

    int nMaxVerticies = 256;
    double dMaxVerticiesMask = nMaxVerticies - 1;
    double dAmplitude;
    double dScale;
    double[] ardRanVectors = new double[nMaxVerticies];
    Long lSeed;

    Random ran = new Random();

    //----------------------------------------------Constructor---------------------------------------------------------

    public Noise(double dAmplitude, double dScale, Long seed) {
        this.dAmplitude = dAmplitude;
        this.dScale = dScale;
        lSeed = seed;

        if(seed == null){
            seed = ran.nextLong();
        }

        ran.setSeed(seed);

        for (int i = 0; i < nMaxVerticies; i++) {
            ardRanVectors[i] = ran.nextDouble();
        }
    }

    //----------------------------------------------My Methods----------------------------------------------------------

    public double Noise(int x){
        double dScaledX = x * dScale;
        double dxFloor = Math.floor(dScaledX);
        double dt = dScaledX - dxFloor;
        double dtRemapSmoothstep = dt * dt * ( 3 - 2 * dt );

        int nXMin = (int)( dxFloor % dMaxVerticiesMask);
        int nXMax = (int)(( nXMin + 1 ) % dMaxVerticiesMask);

        double dY = lerp( ardRanVectors[ nXMin ], ardRanVectors[ nXMax ], dtRemapSmoothstep );

        return dY * dAmplitude;
    }

    public double lerp(double a, double b, double t){
        return a * ( 1 - t ) + b * t;
    }
}

//----------------------------------------------Links-------------------------------------------------------------------

//https://www.youtube.com/watch?v=eevjZsMYx6M