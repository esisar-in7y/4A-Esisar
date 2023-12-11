package tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Exo4 {
	//TODO : les .bmp ne fonctionnent pas
	public static void main(String[] args) throws Exception {
		Exo4 clientTCP = new Exo4();
		clientTCP.execute();
	}

	private void execute() throws IOException {
		//
		System.out.println("Demarrage du client ...");

		Socket socket = new Socket();

		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 8000);
		socket.connect(adrDest);
		Boolean endCommunication = false;

		int compteur = 1;
		while (!endCommunication) {
			String file = "/home/userir/image" + compteur + ".bmp";
			OutputStream destination = new FileOutputStream(file);
			receive(socket, destination);
			compteur++;
		}

		if (endCommunication) {
			socket.close();
			System.out.println("Arret du client .");
		}
	}

	private String receive(Socket socket, OutputStream destination) throws IOException {
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);
		String str = "";
		Boolean endFile = false;
		while (lenBufR != -1 && !endFile) {
			destination.write(bufR, 0, lenBufR);
			str = str + new String(bufR, 0, lenBufR);
			if (str.contains("\r\n")) {
				endFile = true;
			}
			lenBufR = is.read(bufR);
		}
		return str;
	}
}
