import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class FileImplementer extends UnicastRemoteObject implements FileInterface {

	long FileSize = 1024*1024;
	
	protected FileImplementer() throws RemoteException {
		super();
	}

	@Override
	public byte[] DownloadFile(String FileName,int startpos) throws RemoteException {
		try {
			File file = new File(FileName);
			if (startpos >= file.length()){
				return null;
			}
			int Size = (int)(file.length() < this.FileSize ? file.length() : this.FileSize);
			byte buffer[] = new byte[Size];
			FileInputStream input = new FileInputStream(FileName);
			input.skip(startpos);
			input.read(buffer);
			input.close();
			return buffer;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String[] ListFile(String DirName) throws RemoteException {
		File file = new File(DirName);
		File[] files = file.listFiles();
		String[] strings = new String[files.length];
		for (int i = 0;i < strings.length;i ++){
			strings[i] = files[i].getAbsolutePath(); 
		}
		return strings;
	}
}
