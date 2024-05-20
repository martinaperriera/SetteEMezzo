package org.gen.italy;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;

public class Main { //SETTE E MEZZO

	public static void main(String[] args) {

				Scanner sc = new Scanner(System.in);
				Random r = new Random();
				// dichiarazione dati e arrayList
				String risposta = "", rispostaRestart = "", rispostaRicarica = "";
				float bilancio, punteggio = 0, punteggioBanco = 0, puntata;
				ArrayList<Integer> carte = new ArrayList<Integer>();
				HashSet<Integer> carteEstratte = new HashSet<Integer>();
				int randomCard;
				String seme;
				boolean giocVin = false;

				// generazione mazzo 
				for (int i = 0; i < 40; i++) {
					carte.add(i);
				}
				System.out.println("Benvenuto al tavolo di 7 e 1/2");
				// richiesta inserimento saldo attuale
				do {
					System.out.println("Inserisci il tuo bilancio:");
					bilancio = sc.nextFloat();
					if (bilancio <= 0)
						System.out.println("Impossibile sedersi al tavolo con un bilancio negativo");
				} while (bilancio <= 0);
				do {
					if (bilancio == 0) {
						System.out.println("non hai soldi, vuoi ricaricare il bilancio attuale?");
						rispostaRicarica = sc.nextLine();
						if (rispostaRicarica.equalsIgnoreCase("si") || rispostaRicarica.equalsIgnoreCase("sì")) {
							System.out.println("inserisci quanto vuoi ricaricare:");
							bilancio = sc.nextFloat();
							sc.nextLine();
						}
					}
					do {
						// richiesta puntata
						System.out.println("Inserisci la tua puntata:");
						puntata = sc.nextFloat();
						sc.nextLine();
						if (puntata < 0)
							System.out.println("Impossibile puntare una somma di denaro pari o inferiore a zero");
						else if (puntata > bilancio)
							System.out.println("Impossibile puntare una somma di denaro maggiore di quella che possiedi già");
					} while (puntata < 0 || puntata > bilancio);
					// estrazione carta giocatore
					do {
						do {
							// estrazione carta e attribuzione seme
							randomCard = r.nextInt(40); // generazione numerj da 0 a 39
						} while (carteEstratte.contains(randomCard));
						carteEstratte.add(randomCard);
						if (randomCard <= 9) {
							randomCard = randomCard + 1;
							seme = "bastoni";
							if (randomCard == 0) {
								randomCard = 10;
							}
						} else if (randomCard <= 19) {
							randomCard = (randomCard + 1) % 10;
							seme = "denari";
							if (randomCard == 0) {
								randomCard = 10;
							}
						} else if (randomCard <= 29) {
							randomCard = (randomCard + 1) % 10;
							seme = "spade";
							if (randomCard == 0) {
								randomCard = 10;
							}
						} else {
							randomCard = (randomCard + 1) % 10;
							seme = "coppe";
							if (randomCard == 0) {
								randomCard = 10;
							}
						}
						System.out.println("la tua carta è " + carte.get(randomCard) + " di " + seme);

						// punteggio
						if (randomCard <= 7) {
							punteggio = punteggio + randomCard;
						} else if (randomCard > 7) {
							punteggio = punteggio + 0.5f;
						}
						System.out.println("il tuo punteggio attuale è " + punteggio);
						if (punteggio > 7.5f) {
							System.out.println("Hai sballato!");
							break;
						} else if (punteggio < 7.5f) {
							System.out.println("vuoi un'altra carta?");
							risposta = sc.nextLine();
						} else if (punteggio == 7.5f) {
							System.out.println("hai raggiunto il punteggio massimo");
							break;
						}
					} while (risposta.equalsIgnoreCase("si") || risposta.equalsIgnoreCase("sì"));

					// banco
					do {
						do {
							// estrazione carta e attribuzione seme banco
							randomCard = r.nextInt(40); // generazione num da 0 a 39
						} while (carteEstratte.contains(randomCard));
						carteEstratte.add(randomCard);
						if (randomCard <= 9) {
							randomCard = randomCard + 1;
							seme = "bastoni";
							if (randomCard == 0) {
								randomCard = 10;
							}
						} else if (randomCard <= 19) {
							randomCard = (randomCard + 1) % 10;
							seme = "denari";
							if (randomCard == 0) {
								randomCard = 10;
							}
						} else if (randomCard <= 29) {
							randomCard = (randomCard + 1) % 10;
							seme = "spade";
							if (randomCard == 0) {
								randomCard = 10;
							}
						} else {
							randomCard = (randomCard + 1) % 10;
							seme = "coppe";
							if (randomCard == 0) {
								randomCard = 10;
							}
						}
						System.out.println("la carta del banco è " + carte.get(randomCard) + " di " + seme);

						// punteggio banco
						if (randomCard <= 7) {
							punteggioBanco = punteggioBanco + randomCard;
						} else if (randomCard > 7) {
							punteggioBanco = punteggioBanco + 0.5f;
						}
						System.out.println("il punteggio attuale del banco è " + punteggioBanco);
						if (punteggioBanco > 7.5f) {
							System.out.println("il banco ha sballato!");
							System.out.println("il giocatore ha vinto");
							giocVin = true;
							break;
						} else if (punteggio > 7.5) {
							System.out.println("Il banco ha vinto");
							break;
						} else if (((punteggioBanco < 7.5f && punteggio <= 7.5f))
								|| (punteggioBanco < punteggio && punteggio <= 7.5f)) {
							System.out.println("il banco chiede un'altra carta");
						}
					} while ((punteggioBanco < punteggio) && (punteggioBanco <= 7.5f));
					if (giocVin == true) {
						bilancio = bilancio + puntata;
					} else {
						bilancio = bilancio - puntata;
					}
					punteggio = 0;
					punteggioBanco = 0;
					System.out.println("il tuo bilancio attuale " + bilancio);
					// restart
					System.out.println("vuoi continuare a giocare?");
					rispostaRestart = sc.nextLine();
					carteEstratte.clear();
				} while (rispostaRestart.equalsIgnoreCase("si") || rispostaRestart.equalsIgnoreCase("sì"));
				System.out.println("Grazie, arrivederci");
			}// fine main

		}