package frc.robot.utils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class Utils {
    public static final double EPSILON = 1e-9;

    public static boolean epsilonEquals(double a, double b) {
        return epsilonEquals(a, b, EPSILON);
    }

    public static boolean epsilonEquals(double a, double b, double maxError) {
        return Math.abs(a - b) < maxError;
    }

    public static double[] pose2dToArray(Pose2d pose) {
        return new double[]{pose.getX(), pose.getY(), pose.getRotation().getDegrees()};
    }

    public static double[] chassisSpeedsToArray(ChassisSpeeds speeds) {
        return new double[]{speeds.vxMetersPerSecond, speeds.vyMetersPerSecond, speeds.omegaRadiansPerSecond};
    }

    public static ChassisSpeeds arrayToChassisSpeeds(double[] array) {
        return new ChassisSpeeds(array[0], array[1], array[2]);
    }

    public static double[] swerveModuleStatesToArray(SwerveModuleState[] states) {
        double[] array = new double[states.length * 2];
        for (int i = 0; i < states.length; i++) {
            array[i * 2] = states[i].angle.getRadians();
            array[i * 2 + 1] = states[i].speedMetersPerSecond;
        }
        return array;
    }

    public static boolean speedsEpsilonEquals(ChassisSpeeds speeds) {
        return epsilonEquals(speeds.vxMetersPerSecond, 0) &&
                epsilonEquals(speeds.vyMetersPerSecond, 0) &&
                epsilonEquals(speeds.omegaRadiansPerSecond, 0);
    }
}