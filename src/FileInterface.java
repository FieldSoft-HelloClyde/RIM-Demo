import java.rmi.*;

public interface FileInterface extends Remote{
	public byte[] DownloadFile(String FileName,int startpos) throws RemoteException;
	public String[] ListFile(String DirName) throws RemoteException;
}
