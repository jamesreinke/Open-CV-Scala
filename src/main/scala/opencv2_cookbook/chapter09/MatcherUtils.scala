/*
 * Copyright (c) 2011-2014 Jarek Sacha. All Rights Reserved.
 *
 * Author's e-mail: jpsacha at gmail.com
 */

package opencv2_cookbook.chapter09

import opencv2_cookbook.OpenCVUtils._
import org.bytedeco.javacpp.opencv_core.{Point2f, CvPoint2D32f}
import org.bytedeco.javacpp.opencv_features2d.{DMatch, KeyPoint}


object MatcherUtils {

  /** Convert from KeyPoint to Point2D32f representation */
  def toCvPoint2D32fPair(matches: DMatch, keyPoints1: KeyPoint, keyPoints2: KeyPoint): (CvPoint2D32f, CvPoint2D32f) = {
    toCvPoint2D32fPair(toArray(matches), keyPoints1, keyPoints2)
  }

  /** Convert from KeyPoint to Point2D32f representation */
  def toCvPoint2D32fPair(matches: Array[DMatch], keyPoints1: KeyPoint, keyPoints2: KeyPoint): (CvPoint2D32f, CvPoint2D32f) = {

    // Extract keypoints from each match, separate Left and Right
    val pointIndexes1 = new Array[Int](matches.length)
    val pointIndexes2 = new Array[Int](matches.length)
    for (i <- 0 until matches.length) {
      pointIndexes1(i) = matches(i).queryIdx()
      pointIndexes2(i) = matches(i).trainIdx()
    }

    // Convert keypoints into Point2f
    val points1 = new Point2f(matches.length)
    val points2 = new Point2f(matches.length)
    KeyPoint.convert(keyPoints1, points1, pointIndexes1)
    KeyPoint.convert(keyPoints2, points2, pointIndexes2)

    (toCvPoint2D32f(points1), toCvPoint2D32f(points2))
  }
}
