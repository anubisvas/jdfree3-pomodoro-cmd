package org.example;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите время работы и отдыха");
        var cmd = new Scanner(System.in).nextLine().split(" ");
        // [-w, 1, -b, 1]
        int worktime = 1;
        int breaktime = 1;
        boolean isHelp = false;
        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-w" -> worktime = Integer.parseInt(cmd[++i]);
                case "-b" -> breaktime = Integer.parseInt(cmd[++i]);
                case "--help" -> {
                    System.out.println("""
                            \nPomodoro timer программа для работы 
                            -w сколько работать
                            -b сколько отдыхать
                            --help - Вызов помощи
                            """);
                    isHelp = true;
                }

            }
        }
        if (isHelp) return;
        long startTime = System.currentTimeMillis();
        timer(worktime, breaktime);
        long endTime = System.currentTimeMillis();
        System.out.println("Таймер работал " + (endTime - startTime)/(1000 * 60) + " min");
    }

    public static void timer(int worktime, int breaktime) throws InterruptedException {
        printProgress("Work Progress::  ", worktime);
        printProgress("Break Progress:: ", breaktime);
    }

    private static void printProgress(String process, int time) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / 30;
        rep = 60 * time / length;
        int stretch = 30 / (3 * time);
        for (int i = 1; i <= rep; i++) {
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretch;
            double percent = (x / w) * 1000;
            x /= stretch;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + "% " + (" ")
                    .repeat(5 - (String.valueOf(percent).length())) + "[" + ("#")
                    .repeat(i) + ("-").repeat(rep - i) + "]    ( " + x + "min / " + time + "min )" + "\r");
            TimeUnit.SECONDS.sleep(length);
        }
    }
}