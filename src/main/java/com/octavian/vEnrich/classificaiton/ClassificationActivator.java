package com.octavian.vEnrich.classificaiton;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ClassificationActivator {


    private static Logger LOG;
    public static final String RES_PATH = "D:\\Repo\\vEnrich\\src\\main\\resources\\";
    public static final String IMAGES_PATH = "D:\\Repo\\vEnrich\\src\\main\\resources\\images\\";
    public static final String TEST_PATH = "D:\\Repo\\vEnrich\\src\\main\\resources\\images\\test\\";
    public static final String RAW_PATH = "D:\\Repo\\vEnrich\\src\\main\\resources\\images\\test\\raw\\";
    public static final String CLASSIFIED_PATH = "D:\\Repo\\vEnrich\\src\\main\\resources\\images\\test\\classified\\";
    public static final String CROPPED_PATH = "D:\\Repo\\vEnrich\\src\\main\\resources\\images\\test\\cropped\\";
    public static final String LICENCEPLATE_LBP = "D:\\Repo\\vEnrich\\src\\main\\resources\\classifiers\\licenceplateClassifier.xml";
    public static final String STREET_SIGN_LBP = "D:\\Repo\\vEnrich\\src\\main\\resources\\classifiers\\lbpStreetSignCascade.xml";
    public static final String STREET_SIGN_HAAR = "D:\\Repo\\vEnrich\\src\\main\\resources\\classifiers\\haarStreetSignCascade.xml";

    public ClassificationActivator() {
    }

    public static List<String> readFileNames(String inputDirName, boolean includeDirPrefix) {
        File f = new File(inputDirName);
        return (List)(includeDirPrefix ? (List)(new ArrayList(Arrays.asList(f.list()))).stream().map((s) -> {
            return inputDirName + "\\" + s;
        }).collect(Collectors.toList()) : new ArrayList(Arrays.asList(f.list())));
    }

    public static Mat readImage(String sourceFileName) {
        return Imgcodecs.imread(sourceFileName);
    }

    public static Mat toGrayscale(Mat source) {
        Mat target = new Mat();
        Imgproc.cvtColor(source, target, 6);
        return target;
    }

    public static void printImage(String targetFileDestination, Mat image) {
        Imgcodecs.imwrite(targetFileDestination, image);
    }

    public List<Integer> convertMatToIntegers(Mat m) {
        List<Integer> values = new ArrayList();

        for(int i = 0; i < m.rows(); ++i) {
            for(int j = 0; j < m.cols(); ++j) {
                values.add((int)m.get(i, j)[0]);
            }
        }

        return values;
    }

    public static void convertToPNGfromEntireDir(String inputDirName, String outputDirName) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File f = new File(inputDirName);
        List<String> files = new ArrayList(Arrays.asList(f.list()));
        Iterator var4 = files.iterator();

        while(var4.hasNext()) {
            String file = (String)var4.next();
            Mat img = Imgcodecs.imread(inputDirName + '\\' + file);
            Imgcodecs.imwrite(outputDirName + '\\' + file.substring(0, file.length() - 4) + ".PNG", img);
        }

    }

    public static Mat resize(Mat source, double newWidth, double newHeight) {
        Mat target = new Mat();
        Imgproc.resize(source, target, new Size(newWidth, newHeight));
        return target;
    }

    public static Mat equalize(Mat source) {
        Mat target = new Mat();
        Imgproc.equalizeHist(source, target);
        return target;
    }

    public static Mat threshold(Mat source, double thresholdValue, double maxIntensity) {
        Mat target = new Mat();
        Imgproc.threshold(source, target, thresholdValue, maxIntensity, 0);
        return target;
    }

    public static Mat adaptiveThreshold(Mat source, double maxIntensity, int blockSize, double c) {
        Mat target = new Mat();
        Imgproc.adaptiveThreshold(source, target, 255.0D, 0, 0, blockSize, c);
        return target;
    }

    public static Mat inverse(Mat source) {
        Mat target = new Mat();
        Core.subtract(source, Scalar.all(225.0D), target);
        return target;
    }

    public static Mat detectWithClassifier(Mat source, String classifierLocation) {
        CascadeClassifier classifier = new CascadeClassifier(classifierLocation);
        MatOfRect signsDet = new MatOfRect();
        classifier.detectMultiScale(source, signsDet);
        System.out.println(String.format("Detected %s plates", signsDet.toArray().length));
        Rect[] var4 = signsDet.toArray();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Rect rect = var4[var6];
            Imgproc.rectangle(source, new Point((double)rect.x, (double)rect.y), new Point((double)(rect.x + rect.width), (double)(rect.y + rect.height)), new Scalar(0.0D, 0.0D, 255.0D));
        }

        return source;
    }

    public static void detectFromRaw() {
        List<String> fileNames = readFileNames(RAW_PATH, false);
        List<String> rawImages = readFileNames(RAW_PATH, true);

        for(int i = 0; i < rawImages.size(); ++i) {
            Mat img = readImage((String)rawImages.get(i));
            Mat classifiedImg = detectWithClassifier(img, LICENCEPLATE_LBP);
            printImage(CLASSIFIED_PATH+ (String)fileNames.get(i), classifiedImg);
        }

    }

    public static void main(String[] args) throws IOException {
        detectFromRaw();
    }

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        LOG = LoggerFactory.getLogger(ClassificationActivator.class);
    }
}
