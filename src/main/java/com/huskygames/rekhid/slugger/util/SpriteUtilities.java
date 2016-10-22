package com.huskygames.rekhid.slugger.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class SpriteUtilities {

    /**
     * Creates a reversing affine transformation that "mirrors" the image
     *
     * @param width the width of the image.
     * @return
     */
    public static AffineTransform buildReversal(int width) {
        AffineTransform temp = new AffineTransform();
        temp.translate(width / 2, 0);
        temp.scale(-1, 1);
        temp.translate(-width / 2, 0);
        return temp;
    }

    private static AffineTransform buildScale(int currWidth, int desiredWidth) {
        AffineTransform temp = new AffineTransform();
        double factor = ((double) desiredWidth) / currWidth;
        temp.scale(factor, factor);
        return temp;
    }

    public static AffineTransformOp buildScaleOp(int currHeight, int desiredheight) {
        return new AffineTransformOp(buildScale(currHeight, desiredheight),
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }
}
