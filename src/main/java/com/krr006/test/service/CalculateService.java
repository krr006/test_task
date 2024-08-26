package com.krr006.test.service;

import com.krr006.test.exception.MathException;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    public double calculateDiscriminant(double a, double b, double c) throws MathException {
        if (a == 0) {
            throw new MathException("Коэффициент 'a' не может быть равен нулю");
        }
        return b * b - 4 * a * c;
    }

    public double calculateX(double a, double b, double discriminant) throws MathException {
        if (discriminant < 0) {
            throw new MathException("Дискриминант отрицательный, извлечение корня невозможно");
        }
        return (b + Math.sqrt(discriminant)) / (2 * a);
    }
}