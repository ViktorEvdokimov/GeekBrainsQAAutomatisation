package ru.education.evdokimov;

import javax.lang.model.UnknownEntityException;

public class AreaCalculator {

    public static double calculateTriangleArea (int a, int b, int c){
        long al = (long) a;
        long bl = (long) b;
        long cl = (long) c;
        if (a <= 0 || b <= 0 || c <= 0){
            throw new IllegalArgumentException("Введенные числа должны быть положительными");
        }
        if (al >= (bl + cl) || b >= (al + cl) || cl >= (bl + al)) {
            throw new IllegalArgumentException("Введенный набор чисел не явзяется треугольником, поскольку одна из сторон больше суммы двух других");
        }

        double p = (al + bl + cl)/2f;
        return Math.sqrt(p * (p - al) * (p - bl) * (p - cl));
    }
}
