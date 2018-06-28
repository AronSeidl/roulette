/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seidl.casino;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Áron
 */
public class UiPlayer extends Player {

    public UiPlayer(double money) {
        super(money);
    }

    @Override
    public void nextBet() throws NoEnoughMoneyException {
        Scanner sc = new Scanner(System.in);
        boolean thanksEnough = false;
        while (!thanksEnough) {
            System.out.println("Kérem adja meg melyik fajta játékot játszaná?!");
            System.out.println("1: RedBlack,  2:EvenOdd, 3: OneNumber");
            int choosenGameMode;
            do {
                choosenGameMode = sc.nextInt();
                if (!(choosenGameMode == 1 || choosenGameMode == 2 || choosenGameMode == 3)) {
                    System.out.println("Ilyen játékmód nincs! Kérjük, add meg újra!");
                }
            } while (!(choosenGameMode == 1 || choosenGameMode == 2 || choosenGameMode == 3));
            System.out.println("Mennyi pénzzel szeretné megjátszani a választott játékot?!");
            double betMoney = sc.nextDouble();
            switch (choosenGameMode) {
                case 1:
                    if (money == 0) {
                        throw new NoEnoughMoneyException();
                    } else if (money < betMoney) {
                        System.out.println("Mivel nincs már annyi pénzed amennyit fel szeretnél tenni, két lehetőséged maradt:");
                        System.out.println("1: Felteszed minden pénzed, amid van 2: Kiszállsz a játékból");
                        int agony = sc.nextInt();
                        switch (agony) {
                            case 1:
                                System.out.println("Kérem adja meg, hogy melyik színre szeretne tenni!\n(red: piros, black: fekete)");
                                String chosenColour = sc.nextLine();
                                do {
                                    chosenColour = sc.nextLine();
                                    if (!(chosenColour.equals("red") || chosenColour.equals("black"))) {
                                        System.out.println("Rossz színt adtál meg! Kérjük, add meg újra!");
                                    }
                                } while (!(chosenColour.equals("red") || chosenColour.equals("black")));
                                RedBlackBet r1 = new RedBlackBet(chosenColour, money);
                                betList.add(r1);
                                money = 0;
                                break;
                            case 2:
                                throw new NoEnoughMoneyException();
                            default:
                                System.out.println("Érvénytelen választ adtál meg, így ki vagy zárva a játékból.");
                                throw new NoEnoughMoneyException();
                        }
                    } else {
                        System.out.println("Kérem adja meg, hogy melyik színre szeretne tenni!\n(red: piros, black: fekete)");
                        String chosenColour = sc.nextLine();
                        do {
                            chosenColour = sc.nextLine();
                            if (!(chosenColour.equals("red") || chosenColour.equals("black"))) {
                                System.out.println("Rossz színt adtál meg! Kérjük, add meg újra!");
                            }
                        } while (!(chosenColour.equals("red") || chosenColour.equals("black")));
                        RedBlackBet r1 = new RedBlackBet(chosenColour, betMoney);
                        betList.add(r1);
                        money -= betMoney;
                    }
                    break;
                case 2:
                    if (money == 0) {
                        throw new NoEnoughMoneyException();
                    } else if (money < betMoney) {
                        System.out.println("Mivel nincs már annyi pénzed amennyit fel szeretnél tenni, két lehetőséged maradt:");
                        System.out.println("1: Felteszed minden pénzed, amid van 2: Kiszállsz a játékból");
                        int agony = sc.nextInt();
                        switch (agony) {
                            case 1:
                                System.out.println("Kérem adja meg, hogy párosra, vagy páratlanra szeretne tenni!\n(páros: even, páratlan: odd");
                                String parity = sc.nextLine();
                                do {
                                    parity = sc.nextLine();
                                    if (!(parity.equals("even") || parity.equals("odd"))) {
                                        System.out.println("Érvénytelen párosságot adtál meg! Kérjük, add meg újra!");
                                    }
                                } while (!(parity.equals("even") || parity.equals("odd")));
                                EvenOddBet e1 = new EvenOddBet(parity, money);
                                betList.add(e1);
                                money = 0;
                                break;
                            case 2:
                                throw new NoEnoughMoneyException();
                            default:
                                System.out.println("Érvénytelen választ adtál meg, így ki vagy zárva a játékból.");
                                throw new NoEnoughMoneyException();
                        }
                    } else {
                        System.out.println("Kérem adja meg, hogy párosra vagy páratlanra szeretne tenni!");
                        String parity = sc.nextLine();
                        do {
                            parity = sc.nextLine();
                            if (!(parity.equals("even") || parity.equals("odd"))) {
                                System.out.println("Érvénytelen párosságot adtál meg! Kérjük, add meg újra!");
                            }
                        } while (!(parity.equals("even") || parity.equals("odd")));
                        EvenOddBet e1 = new EvenOddBet(parity, betMoney);
                        betList.add(e1);
                        money -= betMoney;
                    }
                    break;
                case 3:
                    if (money == 0) {
                        throw new NoEnoughMoneyException();
                    } else if (money < betMoney) {
                        System.out.println("Mivel nincs már annyi pénzed amennyit fel szeretnél tenni, két lehetőséged maradt:");
                        System.out.println("1: Felteszed minden pénzed, amid van 2: Kiszállsz a játékból");
                        int agony = sc.nextInt();
                        switch (agony) {
                            case 1:
                                System.out.println("Kérem adja meg, hogy melyik számra szeretne tenni!");
                                int tip;
                                do {
                                    tip = sc.nextInt();
                                    if (tip > 36 || tip < 0) {
                                        System.out.println("Érvénytelen számot adtál meg! Kérjük, add meg újra!");
                                    }
                                } while (tip > 36 || tip < 0);
                                sc.nextLine();
                                OneNumberBet o1 = new OneNumberBet(tip, money);
                                betList.add(o1);
                                money = 0;
                                break;
                            case 2:
                                throw new NoEnoughMoneyException();
                            default:
                                System.out.println("Érvénytelen választ adtál meg, így ki vagy zárva a játékból.");
                                throw new NoEnoughMoneyException();
                        }
                    } else {
                        System.out.println("Kérem adja meg, hogy melyik számra szeretne tenni!");
                        int tip;
                        do {
                            tip = sc.nextInt();
                            if (tip > 36 || tip < 0) {
                                System.out.println("Érvénytelen számot adtál meg! Kérjük, add meg újra!");
                            }
                        } while (tip > 36 || tip < 0);
                        sc.nextLine();
                        OneNumberBet r1 = new OneNumberBet(tip, betMoney);
                        betList.add(r1);
                        money -= betMoney;
                        sc.nextLine();
                    }
                    break;
                default:
                    System.out.println("Ilyen játékmód nincs!");
                    break;
            }
            System.out.println("Szeretne még tétet felrakni?");
            System.out.println("y = yes, n = no");
            String answer = sc.nextLine();
            if (answer.charAt(0) == 'n') {
                thanksEnough = true;
            }
        }
    }

    @Override
    public List<Bet> getBetList() {
        return betList;
    }

}
