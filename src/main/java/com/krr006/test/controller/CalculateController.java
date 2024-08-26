package com.krr006.test.controller;

import com.krr006.test.service.CalculateService;
import com.krr006.test.exception.MathException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class CalculateController {

    private final CalculateService calculateService;

    @GetMapping("/calculate")
    public String showForm() {
        return "form";
    }

    @PostMapping("/calculate")
    public String calculateX(@RequestParam double a,
                             @RequestParam double b,
                             @RequestParam double c,
                             Model model) {
        try {
            double discriminant = calculateService.calculateDiscriminant(a, b, c);
            var x = Math.round(calculateService.calculateX(a, b, discriminant) * 10.0) / 10.0;

            model.addAttribute("a", a);
            model.addAttribute("b", b);
            model.addAttribute("c", c);
            model.addAttribute("discriminant", discriminant);
            model.addAttribute("x", x);
            return "input_y";
        } catch (MathException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @PostMapping("/result")
    public String calculateYAndShowGraph(@RequestParam double a,
                                         @RequestParam double b,
                                         @RequestParam double c,
                                         @RequestParam double discriminant,
                                         @RequestParam double x,
                                         @RequestParam double y,
                                         Model model) {
        model.addAttribute("result", new Result(a, b, c, discriminant, x, y));
        return "result";
    }

    public static class Result {
        private double a;
        private double b;
        private double c;
        private double discriminant;
        private double x;
        private double y;

        public Result(double a, double b, double c, double discriminant, double x, double y) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.discriminant = discriminant;
            this.x = x;
            this.y = y;
        }

        public double getA() {
            return a;
        }

        public double getB() {
            return b;
        }

        public double getC() {
            return c;
        }

        public double getDiscriminant() {
            return discriminant;
        }


        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
}
