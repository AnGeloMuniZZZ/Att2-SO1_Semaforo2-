package controller;

import java.util.concurrent.Semaphore;

public class ThreadPorta extends Thread {

	private int id;
	Semaphore semaforo;
	private int andado = 0;

	public ThreadPorta(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		andar();
		try {
			semaforo.acquire();
			porta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void porta() {
		System.out.println("A " + id + "ª pessoa abriu a porta");
		try {
			sleep((int) (Math.random() + 1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A " + id + "ª pessoa cruzou a porta");
	}

	private void andar() {
		System.out.println("A " + id + "ª pessoa inicia o percurso");
		while (andado < 200) {
			andado = andado + (int) ((Math.random() * 2) + 4);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("A " + id + "ª pessoa chegou na porta");
	}

}
