package com.huskygames.rekhid.slugger.util.collison;

import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Circle;
import com.huskygames.rekhid.slugger.util.collison.shape.Rectangle;

public class CollisonChecker {

    public static boolean intersects(Circle a, Circle b) {
        DoublePair temp = a.getPosition().subtract(b.getPosition());
        double x = temp.getX();
        double y = temp.getY();

        return Math.sqrt(x * x + y * y) < a.getRadius() + b.getRadius();
    }

    public static boolean intersects(Rectangle a, Rectangle b) {
        return checkInterference(a, b) || checkInterference(b, a);
    }

    public static boolean intersects(Circle a, Rectangle b) {
        DoublePair rectMin = b.getPt1().getMin(b.getPt2());

        double rectWidth = Math.abs(b.getPt1().getX() - b.getPt2().getX());
        double rectHeight = Math.abs(b.getPt1().getY() - b.getPt2().getY());

        double cdX = Math.abs(a.getPosition().getX() - rectMin.getX());
        double cdY = Math.abs(a.getPosition().getY() - rectMin.getY());

        if (cdX > rectWidth / 2 + a.getRadius()) return false;
        if (cdY > rectHeight / 2 + a.getRadius()) return false;

        if (cdX <= (rectWidth / 2)) return true;
        if (cdY <= (rectHeight / 2)) return true;

        double tempX = (cdX - rectWidth/2);
        double tempY = (cdY - rectHeight/2);
        double cornerDistance = tempX * tempX + tempY * tempY;

        return cornerDistance <= a.getRadius() * a.getRadius();
    }

    private static boolean checkInterference(Rectangle a, Rectangle b) {
        DoublePair w = a.getPt1();
        DoublePair x = new DoublePair(a.getPt1().getX(), a.getPt2().getY());
        DoublePair y = a.getPt2();
        DoublePair z = new DoublePair(a.getPt2().getX(), a.getPt1().getY());

        DoublePair max = b.getPt1().getMax(b.getPt2());
        DoublePair min = b.getPt2().getMin(b.getPt2());

        return checkAabb(w, min, max) || checkAabb(x, min, max) ||
                checkAabb(y, min, max) || checkAabb(z, min, max);
    }

    private static boolean checkAabb(DoublePair point, DoublePair min, DoublePair max) {
        if (point.getX() > min.getX() && point.getY() > min.getY()) {
            if (point.getX() < max.getX() && point.getY() < max.getY()) {
                return true;
            }
        }
        return false;
    }
}
