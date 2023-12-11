package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientAddition {

	public static void main(String[] args) throws Exception {
		ClientAddition clientTCP = new ClientAddition();
		clientTCP.execute();
	}

	private void execute() throws IOException {
		System.out.println("Demarrage du client ...");

		Socket socket = new Socket();
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
		socket.connect(adrDest);

		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		byte[] bufR = new byte[2048];
		StringBuilder responseBuilder = new StringBuilder();
		String backup = "";
		Integer op1, op2, answer;
		String[] toSave;

		while (true) {
			int lenBufR = is.read(bufR);
			//String toSave = "";
			if (lenBufR != -1) {
				
				String receivedData = new String(bufR, 0, lenBufR);
				//if(!backup.equals("") && backup.contains("?")) {
				//	System.out.println("Oh no here I come: " + backup);
				//	responseBuilder.append(backup).append(receivedData);
				//}
				//else {
				//	responseBuilder.append(receivedData);
				//}

				String response = responseBuilder.toString();
				System.out.println("Response received: " + response);

				//int size = response.length();
				//int indice = response.indexOf('?');
				//String reponseComplete = response.substring(0, indice);
				//System.out.println("Response complete received: " + reponseComplete);
				//toSave = response.substring(indice+1, size);
				//System.out.println("to Save received: " + toSave);
				toSave = receivedData.split("\\?");
				for (String operation : toSave) {
					if (operation.contains("=")) {
						
						backup = backup+ operation;
						op1 = Integer.parseInt(backup.trim().substring(0, backup.indexOf('+')));
						op2 = Integer.parseInt(backup.trim().substring(backup.indexOf('+') + 1, backup.indexOf('=')));
						System.out.println(op1 + "+" + op2 + "=");
						answer = op1 + op2;
						byte[] bufM = new String(answer.toString() + ";").getBytes();
						os.write(bufM);
						System.out.println("Message sent: " + answer.toString() + ";");
						backup = "";
					} else {
						backup = backup+operation;
					}
				}
				//String pattern = "(\\d{1,2})\\+(\\d{1,2})=";
				//Pattern regex = Pattern.compile(pattern);
				//Matcher matcher = regex.matcher(reponseComplete);
				//if (matcher.matches()) {
				//	op1 = Integer.parseInt(matcher.group(1));
				//	op2 = Integer.parseInt(matcher.group(2));
				//	answer = op1 + op2;
				//	byte[] bufM = new String(answer.toString() + ";").getBytes();
				//	os.write(bufM);
				//	System.out.println("Message sent: " + answer.toString() + ";");
				//}
				//if (toSave.equals("")) {
				//	responseBuilder = new StringBuilder();
				//}
				//else {
				//	responseBuilder = new StringBuilder(toSave);
				//}

			}
			//if(!toSave.equals(""))
			//{
			//	backup = toSave;
			//	System.out.println("backup: " + backup);
			//}
		}
	}
}