package exo3;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrackImpl implements Crack {
	
	private String hash(String str, MessageDigest digest) throws NoSuchAlgorithmException {
		byte[] hash = digest.digest(str.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : hash) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}

	@Override
	public String getPassword(String hash, long start, long end) throws RemoteException, NoSuchAlgorithmException {
		String mdp = "";
		for (long i = start; i < end; i++)
		{
			mdp = "p2025esisar" + String.format("%09d", i);
			if (hash.equals(hash(mdp, MessageDigest.getInstance("MD5")))) {
				return mdp;
			}
		}
		return null;
	}
	

}
